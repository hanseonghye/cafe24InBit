package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TimeClient {

	private static final String SERVER_IP = "192.168.1.4";

	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			socket = new DatagramSocket();
			socket.connect(new InetSocketAddress(SERVER_IP, TimeServer.PORT));

			String line = "";

			byte[] sendData = line.getBytes("utf-8");
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
					new InetSocketAddress(SERVER_IP, TimeServer.PORT));
			socket.send(sendPacket);
			System.out.println("[ client ] Request Server Time ");

			DatagramPacket receivePacket = new DatagramPacket(new byte[TimeServer.BUFFER_SIZE], TimeServer.BUFFER_SIZE);
			socket.receive(receivePacket);

			byte[] data = receivePacket.getData();
			int length = receivePacket.getLength();
			String message = new String(data, 0, length, "UTF-8");

			System.out.println("<< " + message);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
