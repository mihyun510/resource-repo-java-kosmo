package network.q4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	  public static void main(String[] args) {
	    ServerSocket serverSocket = null;
	    
	    try {
	      //포트8888로 서버소켓을 생성한다. 
	      serverSocket = new ServerSocket(8888);
	      System.out.println("서버가 준비되었습니다.");
	    } catch(IOException e) {
	      e.printStackTrace();
	    }
	    
	    while(true) {
	      try {
	        //연결요청이 올때까지 대기한다. 
	        System.out.println("연결요청을 기다립니다.");
	        Socket socket = serverSocket.accept();
	        System.out.println(socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
	        
	        /*** 알맞은 코드를 넣어 완성하시오 ***/
	        //소켓의 출력스트림을 얻는다. 
	        /*①*/
	        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
	        dataOutputStream.writeUTF("서버로부터 메세지가 도착했습니다. 귀하는 정상적으로 접속되었습니다.");
	        System.out.println("데이터를 전송했습니다.");
	        dataOutputStream.close();
	        socket.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	}
