package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {

	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			//��� ��Ʈ��
			
			//������Ʈ��1
			// byte|byte|byte --> char
			InputStreamReader isr = new InputStreamReader(System.in,"utf-8");
			
			//������Ʈ��2
			// char|char|char|\n  -> char+char+char == string
			br = new BufferedReader(isr);
			
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					if("exit".equals(line)) {
						break;
					}
					System.out.println(">> "+line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			try {
				if( br!= null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
