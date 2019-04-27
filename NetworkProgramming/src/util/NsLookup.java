package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NsLookup {

	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");
			br = new BufferedReader(isr);
			String inputDomain = null;
			
			try {
				System.out.print("-->>");
				while ( (inputDomain = br.readLine()) != null ) {
					if("exit".equals(inputDomain)) {
						System.out.println("[exit]");
						break;
					}
					
					InetAddress[] inetAddresses = Inet4Address.getAllByName(inputDomain);
					
					for ( InetAddress addr : inetAddresses) {
						System.out.println(addr.getHostAddress());
					}
					
					System.out.print("-->>");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if( br != null ) {
					br.close();
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
