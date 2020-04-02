package exam0407;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TcpChatServer extends Thread {
	
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	ServerSocket server =null;
	Socket client = null;
	
	
	@Override
	public void run() {
		try {
			//서버소켓을 생성한다.
			server = new ServerSocket(5005);
			System.out.println("클라이언트를 기다린다...");
			client = server.accept();
			System.out.println("클라이언트 접속완료"+client.getInetAddress());
//			OutputStream os = client.getOutputStream(); 최상위 클래스로 듣고 말하기, but 그러나 기능은 하위클래스로 사용하는 것이 기능이 더 많다.
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject("서버에서 보내는 메세지 입니다."); //듣지않고 말만하는 중.. ois가 현재 없어요..
			System.out.println("메세지를 전송했습니다.");
		} catch (Exception e) {
			System.out.println(e.toString()); //예외메세지 출력
			//예외상황을 관리하는 stack메모리 영역에 쌓여 있는 메시지를 순차적으로 보여줌. - 라인번호, 클래스이름 등.. 히스토리를 관찰가능
			e.printStackTrace();
		} finally {
			try {
				//자원반납하기
				if(oos!=null) oos.close();
				if(client!=null) client.close();
			} catch (Exception e2) {
				e2.printStackTrace(); //암기하자.
			}
		}
		while(true) {
			//클라이언트가 접속할 때까지 기다린다. 
			try {
				client = server.accept(); //손님이 입장
				System.out.println(client.getInetAddress()); //네트워크 문제 getInetAddress을 했을때 어떤정보를 알수 잇나?
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		TcpChatServer tcs = new TcpChatServer();
		tcs.start();
	}

}
