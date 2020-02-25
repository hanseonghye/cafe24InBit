package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int PORT = 7000;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			//2. 바인딩
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("server starts... [ port : "+PORT+" ]");

			while (true) {
				// 3. accept
				// : 클라이언트의 연결요청을 기다린다.
				Socket socket = serverSocket.accept(); // blocking
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void log(String log) {
		System.out.println("[server# "+Thread.currentThread().getId()+" ] " + log);
	}

}
