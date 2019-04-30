package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static final String SERVER_IP = "192.168.1.4";
	private static final int SERVER_PORT = 7000;
	
	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			//1. 소켓 생성
			socket = new Socket();
			scanner = new Scanner(new InputStreamReader(System.in, "utf-8"));
		
//			//2-1. 소켓 버퍼 사이즈 확인
//			int receiveBufferSize = socket.getReceiveBufferSize();
//			int sendBufferSize = socket.getSendBufferSize();
//			System.out.println(receiveBufferSize + " : " + sendBufferSize);
//			
//			//2-2. 소켓 버퍼 사이즈 변경
//			socket.setReceiveBufferSize(1024*10);
//			socket.setSendBufferSize(1024*10);
//			receiveBufferSize = socket.getReceiveBufferSize();
//			sendBufferSize = socket.getSendBufferSize();
//			System.out.println(receiveBufferSize + " : " + sendBufferSize);
			
			//2. 소켓 연결
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			log("connected");
			
			//4.
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while(true) {
				//5. 키보드 입력 받기
				System.out.print(" >> ");
				String line= scanner.nextLine();
				if("quit".equals(line)) {
					break;
				}
				
				//6. 데이터 쓰기
				pr.println(line);
				
				//7. 데이터 읽기
				String data = br.readLine();
				
				if(data == null) {
					log("closed by server");
					break;
				}
				
				System.out.println(" << " + data);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(scanner != null) {
						scanner.close();
					}
					if(socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static void log(String log) {
		System.out.println("[client] " + log);
	}
}
