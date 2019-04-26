package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//1. ���� ���� ����
			serverSocket = new ServerSocket();
			
			//2. ���ε�
			// : Socket�� SocketAddress(IPAddress + Port)�� ���ε� �Ѵ�.
			
			//����ӽ� ���ٸ� �� �۵��Ѵ�
//			InetAddress inetAddress = InetAddress.getLocalHost();
//			String localHost = inetAddress.getHostAddress();
//			serverSocket.bind(new InetSocketAddress(inetAddress, 5000));
//			serverSocket.bind(new InetSocketAddress(localHost, 5000));
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));
			
			//3. accept
			// : Ŭ���̾�Ʈ�� �����û�� ��ٸ���.
			Socket socket = serverSocket.accept(); //blocking
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			
			System.out.println("connected by client [ "+remoteHostAddress +" : "+remoteHostPort + " ]");
			
			try {
				//4. IO Stream �޾ƿ���.
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) {
					//5. ������ �б�
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);
					if(readByteCount == -1) {
						//Ŭ���̾�Ʈ ��������
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer,0, readByteCount, "utf-8");
					System.out.println("[server] received : "+ data);
					
					//6. ������ �б�
					os.write(data.getBytes("utf-8"));
				}
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try{
					if( socket != null && socket.isClosed() ) {
						socket.close();
					}
				}catch (IOException e) {
					e.getStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket != null &&
						serverSocket.isClosed() == false ) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
