package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT = "./webapp"; 
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
			
			// get IOStream
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
		
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				//브라우저가 연결을 끊을떄
				if( line == null ) {
					break;
				}
				
				if( "".equals(line)) {
					break;
				}
				
				//Header이 첫번째 라인만 처리
				if( request == null) {
					request = line;
				}
				
			}
			
			String[] tokens = request.split(" ");
			
			if( "GET".contentEquals(tokens[0])) {
				consoleLog("Request : " + tokens[1]);
				responseStaticResource(os, tokens[1], tokens[2]);
			}else { //post, put, delete, head, connect 와 같은 method는 무시.
				response400Error(os, tokens[2]);
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
//			os.write( "HTTP/1.1 200 OK\r\n".getBytes( "UTF-8" ) );
//			os.write( "Content-Type:text/html; charset=utf-8\r\n".getBytes( "UTF-8" ) );
//			os.write( "\r\n".getBytes() );
//			os.write( "<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes( "UTF-8" ) );

		} catch( Exception ex ) {
			consoleLog( "error:" + ex );
		} finally {
			// clean-up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
				
			} catch( IOException ex ) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	private void responseStaticResource(OutputStream os, String url, String protocol) throws IOException {
		if("/".contentEquals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		if(file.exists() == false) {
			response404Error(os,protocol);
			return ;
		}
		
		//nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		//응답
		os.write( ( protocol + " 200 OK\r\n").getBytes( "UTF-8" ) );
		os.write( ("Content-Type: "+contentType+"; charset=utf-8\r\n").getBytes( "UTF-8" ) );
		os.write( "\r\n".getBytes() );
		os.write( body );
	
	}

	private void response404Error(OutputStream os, String protocol) throws IOException {
		// 응답 예시
		/*
		 *  Http/1.1 404 file not found\r\n
		 *  Content-Type:text/html; charset=utf-8\r\n
		 *  \r\n
		 *  html  에러 문서 (./webapp/error/404.html)
		 */
		
		File errFile = new File(DOCUMENT_ROOT + "/error/404.html");
		byte[] body = Files.readAllBytes(errFile.toPath());
		String contentType = Files.probeContentType(errFile.toPath());
		
		//응답
		os.write( ( protocol + "404 file not found\r\n").getBytes( "UTF-8" ) );
		os.write( ("Content-Type: "+contentType+"; charset=utf-8\r\n").getBytes( "UTF-8" ) );
		os.write( "\r\n".getBytes() );
		os.write( body );
	}
	
	private void response400Error(OutputStream os, String protocol) throws IOException {
		File errFile = new File(DOCUMENT_ROOT + "/error/400.html");
		byte[] body = Files.readAllBytes(errFile.toPath());
		String contentType = Files.probeContentType(errFile.toPath());
		
		os.write( ( protocol + "400 Bad Request\r\n").getBytes("UTF-8") );
		os.write( ("Content-Type: "+contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		os.write( "\r\n".getBytes() );
		os.write(body);
	}

	public void consoleLog( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}
