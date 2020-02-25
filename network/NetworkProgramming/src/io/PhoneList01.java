package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			// 보조스트림
			br = new BufferedReader(new InputStreamReader(new FileInputStream("phone.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");

				int index = 0;
				while (st.hasMoreElements()) {
					String token = st.nextToken();
					if (index == 0) {
						System.out.print(token + ":");
					} else if (index == 1 || index == 2) {
						System.out.print(token + "-");
					} else {
						System.out.println(token);
					}

					index++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
