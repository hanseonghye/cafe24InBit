package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
	
	public static final int PORT= 9000;
	public static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket(PORT);
			
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data,0,length, "UTF-8");
				System.out.println("[ server ] received : "+ message);
				
				if("".equals(message)) {					
					SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a");
					String date = format.format(new Date());
					byte[] sendData = date.getBytes("utf-8");
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
					socket.send(sendPacket);
					System.out.println("[ server ] send : "+date);
				}
			}
		}catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if( socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}
