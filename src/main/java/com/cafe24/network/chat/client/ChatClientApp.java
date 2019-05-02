package com.cafe24.network.chat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {

	public static final String SERVER_IP = "192.168.1.4";
	public static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		String nickName = null;
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			nickName = scanner.nextLine();

			if (nickName.isEmpty() == false) {
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		scanner.close();

		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			new ChatWindow(nickName, socket).show();
			System.out.println("[chatclientapp]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
