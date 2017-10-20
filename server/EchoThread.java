package socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

class EchoThread extends Thread{
	 
	     private ArrayList<ObjectOutputStream> list; //
	    private ObjectInputStream objectInputStream;
	     private ObjectOutputStream objectOutputStream;
	     private String name;
	 
	     public EchoThread(ArrayList<ObjectOutputStream> list,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream){
	         this.list=list;
	         this.objectInputStream=objectInputStream;
	         this.objectOutputStream=objectOutputStream;

	     }
	 
	     public void run(){
	        try{
	             this.name=(String) objectInputStream.readObject();
	             ChatData chatData=new ChatData("["+name+"] ���� �����ϼ̽��ϴ�.");
	             SendObject(chatData); //����
	             while(true){
	                 Object object =objectInputStream.readObject(); //�Է½�Ʈ������ ���� ������ ����
	                 SendObject(object);//��ȭ ����
	             }
	         }catch (Exception e){
	             //if(object==null){
	                 ChatData chatData=new ChatData("["+name+"] ���� �����ϼ̽��ϴ�.");
	                 SendObject(chatData);//����
	                 list.remove(objectOutputStream);
	                 //objectInputStream.close();
	                 //objectOutputStream.close();
	 
	                 //break;
	            // }
	         }
	     }
	 
	     //��ȭ ���� Ŭ���̾�Ʈ�� ������
	     public void SendObject(Object object){
	         try {

	             for (int i = 0; i < list.size(); i++) {
	                 list.get(i).writeObject(object);  // ChatData Object ����;
	             }

	         }catch (Exception e){
	             e.printStackTrace();
	         }
	     }
	 }