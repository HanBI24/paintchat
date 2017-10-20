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
            Scanner scanner =new Scanner(System.in); //���ɳ� ����
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream()); //Object �� ����� ��Ʈ�� ���� //��½�Ʈ�� ����
            System.out.print("��ȭ���� �Է����ּ���:");
            name=a.chatInputField.getText();
            
            objectOutputStream.writeObject(name);//��ȭ�� �������
           while(true){
                ChatData chatData=new ChatData("["+name+"]"+scanner.nextLine()); //���ɳʷ� �Էµ����� �޾� chatData��  ����
                objectOutputStream.writeObject(chatData);  // ChatData Object ����

               if(chatData.text=="/exit") break;
           }
            objectOutputStream.close();
            socket.close(); //���� ����
        }
        catch (IOException e) {
			e.printStackTrace();
		}
    }
}