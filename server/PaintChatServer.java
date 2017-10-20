package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class PaintChatServer {

	public static final int PORT = 7777; // ���� ��Ʈ
	private static ArrayList<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	public static void main(String args[]) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT); // ���� ���� ����
			while (true) {
				Socket socket = serverSocket.accept(); // Ŭ���̾�Ʈ�� ����� ������ ���
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()); // Object
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());																			// �Է�
																							// ��Ʈ��
																							// ����								
			list.add(objectOutputStream);
	         new EchoThread(list,objectInputStream,objectOutputStream).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}