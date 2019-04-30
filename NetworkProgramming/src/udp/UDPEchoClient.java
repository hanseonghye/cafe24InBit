package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class UDPEchoClient {

	private static final String SERVER_IP = "192.168.1.4";
	private static final int SERVER_PORT = 9000;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		Scanner scanner = null;
		
		try {
			//1. 소켓 생성
			socket = new DatagramSocket();
			scanner = new Scanner(new InputStreamReader(System.in, "utf-8"));
			//2. 소켓 연결
			socket.connect(new InetSocketAddress(SERVER_IP,UDPEchoServer.PORT));
			log("connected");
		
			
			while(true) {
				//3. 키보드 입력 받기
				System.out.print(">> ");
				String line= scanner.nextLine();
				if("quit".equals(line)) {
					break;
				}
				
				//4. 데이터 쓰기
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP,SERVER_PORT));
				socket.send(sendPacket);
				
				//5. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE], UDPEchoServer.BUFFER_SIZE);
				socket.receive(receivePacket);
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data, 0, length, "UTF-8");
				
				//6.콘솔 출력
				System.out.println("<< "+message);
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				if(scanner != null) {
					scanner.close();
				}
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			}
	}
	
	public static void log(String log) {
		System.out.println("[client] " + log);
	}
}
