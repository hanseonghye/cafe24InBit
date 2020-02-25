package com.cafe24.network.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientReceiveThread extends Thread {
	Socket socket = null;

	ChatClientReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			while (true) {
				String msg = br.readLine();

				if (msg != null) {
					
					ChatWindow.textAppend(msg);
//					if ("NO".equals(msg)) {
//						ChatWindow.sameName();
//					} else {
//						ChatWindow.textAppend(msg);
//					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
