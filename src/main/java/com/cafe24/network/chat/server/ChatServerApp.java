package com.cafe24.network.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ChatServerApp {
	private final static String IP_ADDRESS = "0.0.0.0";
	private final static int PORT = 6000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		HashMap<String, PrintWriter> mapWriters = new HashMap<String, PrintWriter>();

		try {
			serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(IP_ADDRESS, PORT));

			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, mapWriters).start();
			}

		} catch (SocketException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
