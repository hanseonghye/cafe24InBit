package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TcpClient {
	private static final String SERVER_IP = "192.168.1.4";
	private static final int SERVER_PORT = 5000;
	
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			//1. ���� ����
			socket = new Socket();
			
			//1-1. ���� ���� ������ Ȯ��
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println(receiveBufferSize + " : " + sendBufferSize);
			
			//1-2. ���� ���� ������ ����
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);
			receiveBufferSize = socket.getReceiveBufferSize();
			sendBufferSize = socket.getSendBufferSize();
			System.out.println(receiveBufferSize + " : " + sendBufferSize);
			
			//1-3. So_NODELAY(Nagle Algorithm off)
			socket.setTcpNoDelay(true);
			
			//1-4. SO_TIMEOUT 
			socket.setSoTimeout(1000);
			
			//2. ���� ����F
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("[client] connected");
			
			//3. iostream �޾ƿ���
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			//4. ����
			String data = "hello world\n";
			os.write(data.getBytes("utf-8"));
			
			//5. �б�
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			if( readByteCount == -1 ) {
				System.out.println("[client] closed by server");
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received " + data);
			
		}catch(SocketTimeoutException e) {
			System.out.println("[client] Time Out");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
}