package socket;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class PaintChatClient {
	public static final String SERVER_IP = "127.0.0.1"; // ������ ���� ������
	public static final int SERVER_PORT = 7777; // ������ ���� ��Ʈ
	UserInterface a = new UserInterface();
	public static void main(String args[]) {
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("�������Ӽ���!");
			// �������� ������ �����͸� �����ϴ� ������ ����
			new ClientReceiveThread(socket).start();
			// ������ �����͸� ������ ������ ����
			new ClientSendThread(socket).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}