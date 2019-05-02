package com.cafe24.network.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private static TextField textField;
	private static TextArea textArea;

	///// 내가 추가
	private Socket socket;
	private  PrintWriter pr;
	private  String nickName;

	public ChatWindow(String name, Socket socket) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);

		nickName = name;
		this.socket = socket;
		socket = null;

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ChatClientApp.SERVER_IP, ChatClientApp.SERVER_PORT));
			pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sendJoin();

		new ChatClientReceiveThread(socket).start();
	}

	private void finish() {
		String request = "QUIT:" + this.nickName + "\r\n";
		pr.println(request);
		System.exit(0);
	}

	public void show() {

		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char KeyCode = e.getKeyChar();
				if (KeyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
//				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.pack();

		// thread 생성
	}

//	private void updateTextArea(String message) {
//		textArea.append(message);
//		textArea.append("\n");
//	}

	private void sendJoin() {
		String request = "JOIN:" + this.nickName + "\r\n";
		pr.println(request);
	}

	private void sendMessage() {

		try {
			pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			String message = textField.getText();

			if ("quit".equals(message)) {
				finish();
			}

			if ("QUIT".equals(message)) {
				finish();
			}

			String request = "SEND:" + this.nickName + ":" + message + "\r\n";
			pr.println(request);

			textField.setText("");
			textField.requestFocus();

//			updateTextArea(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void textAppend(String msg) {
		textArea.append(msg);
		textArea.append("\n");
	}
	
	public static void sameName() {
		textArea.append("같은 대화명 입니다. 창을 끄고 다시 실행해 주세요.");
		textArea.append("\n");
		textField.setEditable(false);
	}
	

}
