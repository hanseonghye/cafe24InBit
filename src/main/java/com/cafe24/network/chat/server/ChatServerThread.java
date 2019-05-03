package com.cafe24.network.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ChatServerThread extends Thread {

	private Socket socket;
	private HashMap<String, PrintWriter> mapWriters = new HashMap<String, PrintWriter>();

	public ChatServerThread(Socket socket, HashMap<String, PrintWriter> mapWriters) {
		this.socket = socket;
		this.mapWriters = mapWriters;
	}

	@Override
	public void run() {

//		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
//		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
//		int remoteHostPort = inetRemoteSocketAddress.getPort();
//		System.out.println("connected by client [ " + remoteHostAddress + " : " + remoteHostPort + " ]");

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			while (true) {
				String data = br.readLine();
				if (data != null) {
					String[] dataTokens = data.split(":");

					if ("JOIN".equals(dataTokens[0])) {
						doJoin(dataTokens[1], pr);
					} else if ("SEND".equals(dataTokens[0])) {
						if (dataTokens.length == 3) {
							doMSG(dataTokens[1], dataTokens[2]);
						}
					} else if ("QUIT".equals(dataTokens[0])) {
						doQuit(dataTokens[1]);
					}
				}
			}
		} catch (SocketException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doQuit(String nickName) throws IOException {
		removeWriter(nickName);
		String data = nickName + "님이 퇴장했습니다.";
		broadcast(data);
	}

	private void removeWriter(String nickName) {
		synchronized (mapWriters) {
			mapWriters.remove(nickName);
		}
	}

	private void doMSG(String nickName, String data) throws IOException {
		String msg = nickName + " : " + data;
		broadcast(msg);
	}

	private void doJoin(String nickName, PrintWriter writer) throws IOException {
		if (mapWriters.containsKey(nickName) == true){
			writer.println("NO");
			writer.flush();
		}else {
			broadcast(nickName + "님이 입장하였습니다.");
			addWriter(nickName, writer);
		}
	}

	// list인 writer pool에 파라미터로 받은 writer을 추가한다.
	// synchronized 키워드는 여러 스레드가 하나의 공유 객체에 접근할 때 동기화를 보장해 준다.
	private void addWriter(String nickName, PrintWriter writer) {

		synchronized (mapWriters) {
			mapWriters.put(nickName, writer);
		}
	}

	private void broadcast(String data) throws IOException {
		synchronized (mapWriters) {
			for (String name : mapWriters.keySet()) {
				mapWriters.get(name).println(data);
				mapWriters.get(name).flush();
			}
		}
	}

}
