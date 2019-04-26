package test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MsLookup {

	public static void main(String[] args) {

		String hostname = "www.naver.com";
		InetAddress[] inetAddresses;
		try {
			inetAddresses = Inet4Address.getAllByName(hostname);

			for (InetAddress addr : inetAddresses) {
				System.out.println(addr.getHostAddress());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
