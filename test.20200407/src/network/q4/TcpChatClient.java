package network.q4;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpChatClient {
	  public static void main(String[] args) {
	    try {
	    //채팅서버에 접속한다. 
	      String serverIp = "127.0.0.1";
	      System.out.println("서버에 연결중입니다. 서버IP :" + serverIp);
	      Socket socket = new Socket(serverIp, 8888); 


	      /*** 알맞은 코드를 넣어 완성하시오 ***/
	      //소켓의 입력스트림을 얻는다. 
	      InputStream inputStream = socket.getInputStream();
	      /*②*/
	     DataInputStream dataInputStream = new DataInputStream(inputStream);
	      //소켓을 통해 서버로부터 받은 메시지를 콘솔에 출력한다. 
	      System.out.println("서버로부터 받은 메시지 :"+dataInputStream.readUTF());
	      System.out.println("연결을 종료합니다.");

	      //스트림과 소켓을 닫는다.
	      dataInputStream.close();
	      socket.close();
	      System.out.println("연결이 종료되었습니다.");
	    } catch(ConnectException ce) {
	      ce.printStackTrace();
	    } catch(IOException ie) {
	      ie.printStackTrace();
	    } catch(Exception e) {
	      e.printStackTrace();  
	    }  
	  }
	}
