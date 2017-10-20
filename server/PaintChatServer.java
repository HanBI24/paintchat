package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class PaintChatServer {

	public static final int PORT = 7777; // 서버 포트
	private static ArrayList<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	public static void main(String args[]) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT); // 서버 소켓 생성
			while (true) {
				Socket socket = serverSocket.accept(); // 클라이언트가 연결될 때까지 대기
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream()); // Object
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());																			// 입력
																							// 스트림
																							// 생성								
			list.add(objectOutputStream);
	         new EchoThread(list,objectInputStream,objectOutputStream).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}