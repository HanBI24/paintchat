package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

class ClientSendThread extends Thread {
	Socket socket;
	String name,na;

	public ClientSendThread(Socket socket) {
		this.socket = socket;
	}

	public void run(){
		UserInterface a = new UserInterface();
        try {
            Scanner scanner =new Scanner(System.in); //스케너 생성
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream()); //Object 를 출력할 스트림 생성 //출력스트림 생성
            System.out.print("대화명을 입력해주세요:");
            name=a.chatInputField.getText();
            
            objectOutputStream.writeObject(name);//대화명 보내기기
           while(true){
                ChatData chatData=new ChatData("["+name+"]"+scanner.nextLine()); //스케너로 입력데이터 받아 chatData에  넣음
                objectOutputStream.writeObject(chatData);  // ChatData Object 전송

               if(chatData.text=="/exit") break;
           }
            objectOutputStream.close();
            socket.close(); //소켓 종료
        }
        catch (IOException e) {
			e.printStackTrace();
		}
    }
}