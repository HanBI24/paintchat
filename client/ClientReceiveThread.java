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
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream()); //Object 입력 스트림 생성   //입력스트림 생성
            
            while(true){
                Object object=objectInputStream.readObject();   //입력스트림으로 부터 데이터 수신
                
                // instanceof 연산자를 이용하여 데이터 구분 후 처리
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
             socket.close(); //소켓 종료
         }catch (Exception e) {
             e.printStackTrace();
         }
     }
}