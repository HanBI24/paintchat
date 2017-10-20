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
	             ChatData chatData=new ChatData("["+name+"] 님이 입장하셨습니다.");
	             SendObject(chatData); //입장
	             while(true){
	                 Object object =objectInputStream.readObject(); //입력스트림으로 부터 데이터 수신
	                 SendObject(object);//대화 내용
	             }
	         }catch (Exception e){
	             //if(object==null){
	                 ChatData chatData=new ChatData("["+name+"] 님이 퇴장하셨습니다.");
	                 SendObject(chatData);//퇴장
	                 list.remove(objectOutputStream);
	                 //objectInputStream.close();
	                 //objectOutputStream.close();
	 
	                 //break;
	            // }
	         }
	     }
	 
	     //대화 내용 클라이언트에 보내기
	     public void SendObject(Object object){
	         try {

	             for (int i = 0; i < list.size(); i++) {
	                 list.get(i).writeObject(object);  // ChatData Object 전송;
	             }

	         }catch (Exception e){
	             e.printStackTrace();
	         }
	     }
	 }