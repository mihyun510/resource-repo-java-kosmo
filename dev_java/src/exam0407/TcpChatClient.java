package exam0407;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TcpChatClient {
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	Socket socket = null;
	final String _IP = "192.168.0.15";
	final int _PORT = 5005;
	public TcpChatClient() {
		// TODO Auto-generated constructor stub
	}
	
	public void connect_process() {
		try {
			socket = new Socket(_IP,_PORT);
			System.out.println("client측: "+socket.getInetAddress());
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//서버로부터 전송된 메시지를 일겅보자.
			String msg = (String)ois.readObject();
			System.out.println("서버로 부터 받은 메시지: "+msg);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} finally {
			try {
				//자원반납하기
				if(oos!=null) oos.close();
				if(socket!=null) socket.close();
			} catch (Exception e2) {
				e2.printStackTrace(); //암기하자.
			}
			
		}
	}
	public static void main(String[] args) {
		TcpChatClient tcc = new TcpChatClient();
		tcc.connect_process();
	}
}
