package test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = Inet4Address.getLocalHost();
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();

//			InetAddress[] inetAddresses = Inet4Address.getAllByName(hostname);
//			
//			for(InetAddress iAddress : inetAddresses) {
//				System.out.println(iAddress.getHostAddress());
//			}
			
			byte[] addresses = inetAddress.getAddress();
			for ( byte address : addresses) {
				System.out.println(address&0x000000ff);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
