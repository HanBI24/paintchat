package socket;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

class ClientReceiveThread extends Thread{
    Socket socket;
    public ClientReceiveThread(Socket socket){
        this.socket=socket;
    }

    public void run(){
        try {
        	InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream()); //Object �Է� ��Ʈ�� ����   //�Է½�Ʈ�� ����
            
            while(true){
                Object object=objectInputStream.readObject();   //�Է½�Ʈ������ ���� ������ ����
                
                // instanceof �����ڸ� �̿��Ͽ� ������ ���� �� ó��
                if(object instanceof PaintData){
                    PaintData paintData=(PaintData)object;
                    System.out.println(paintData.x+" "+paintData.y+" "+paintData.w);
                }
                else if(object instanceof ChatData){
                    ChatData textData=(ChatData)object;
                    System.out.println(textData.text);
                }
                if(object==null) break;
            }
             objectInputStream.close();
             socket.close(); //���� ����
         }catch (Exception e) {
             e.printStackTrace();
         }
     }
}