package thread.file;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileServer extends JFrame implements Runnable{ //Runnable : 손님은 계속 온다.
	ServerSocket 	server 	= null; //손님이 계속온다. - 경합이 벌어짐.
	Socket 			socket	= null; //그 순간에는 하나.
	List<FileServerThread> globalList = null;
	//단톡방 관리하기 - 가족톡방, 동창,...
	Map<String, FileServerThread> map = new HashMap<String, FileServerThread>();
	JTextArea 		jta_log = new JTextArea(10,30);
	JScrollPane 	jsp_log = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
																 ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public void initDisplay() {
		this.setTitle("★★★★★서버 로그★★★★★");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		FileServer fs = new FileServer();
		fs.initDisplay();
		Thread th  = new Thread(fs);
		th.start();
	}
	
	@Override
	public void run() {
		//손님을 관리하기 위한 벡터
		globalList = new Vector<FileServerThread>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3002);
			jta_log.append("Server Ready.........\n");
			while(!isStop) {
				socket = server.accept();
				jta_log.append("client info:"+socket+"\n");
				//생성자 호출이 먼저야? run()메소드 호출이 먼저야?
				//생성자 호출이 먼저지.
				FileServerThread fsf = new FileServerThread(this);
				fsf.start(); //run()메소드를 호출해준다. [run() : 듣고/말하기]
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
