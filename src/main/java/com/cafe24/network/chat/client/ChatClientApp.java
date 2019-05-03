package com.cafe24.network.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {

	public static final String SERVER_IP = "192.168.1.4";
	public static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		String nickName = null;
		Scanner scanner = new Scanner(System.in);
		PrintWriter pr = null;
		Socket socket = new Socket();
		BufferedReader br = null;

		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			System.out.println("대화명을 입력하세요.");
			while (true) {

				System.out.print(">>> ");
				nickName = scanner.nextLine();

				if (nickName.isEmpty() == true) {
					System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
				} else {
					pr.println("CHEC:" + nickName);
					String msg = br.readLine();
					if (msg != null) {
						if ("NO".contentEquals(msg)) {
							System.out.println("이미 사용중인 유저가 있습니다. 다른 대화명을 입력해 주세요.");
						} else if ("YES".contentEquals(msg)) {
							break;
						}
					}
				}

			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scanner.close();

		new ChatWindow(nickName, socket);

	}
}
