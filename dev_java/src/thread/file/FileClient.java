package thread.file;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import thread.talk3.Protocol;
import thread.talk3.TalkClientThread;

public class FileClient extends JFrame {
	
	Socket 			socket	= null;
	JTextArea 		jta_log = new JTextArea(10,30);
	JScrollPane 	jsp_log = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
																 ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public void initDisplay() {
		this.setTitle("★★★★★클라이언트★★★★★");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
	
	public void init() {
		try {
			socket = new Socket("192.168.0.15",3002);
			FileClientThread tct = new FileClientThread(this); 
			tct.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		FileClient fc = new FileClient();
		fc.initDisplay();
		fc.init();
	}
}
