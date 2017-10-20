package socket;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class PaintChatClient {
	public static final String SERVER_IP = "127.0.0.1"; // 접속할 서버 아이피
	public static final int SERVER_PORT = 7777; // 접속할 서버 포트
	UserInterface a = new UserInterface();
	public static void main(String args[]) {
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("서버접속성공!");
			// 서버에서 보내온 데이터를 수신하는 스레드 실행
			new ClientReceiveThread(socket).start();
			// 서버에 데이터를 보내는 스레드 실행
			new ClientSendThread(socket).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}