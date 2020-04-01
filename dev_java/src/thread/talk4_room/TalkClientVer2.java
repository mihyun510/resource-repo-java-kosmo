package thread.talk4_room;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

//나는 oos와 ois를 어느 클래스에 생성해야 하는지 전혀 감이 없다.
//Login처리는 LoginForm에서 진행되므로 TalkClientVer2에서 생성하면 될 것이다.
public class TalkClientVer2 extends JFrame {
	JTabbedPane jtp = new JTabbedPane(); //여러개의 탭이 붙는다.
//	JPanel 		wr 	= new JPanel(); //WaitRoom.java  이것 대신에 WaitRoom을 인스턴스화 하면된다. <== JPanel을 상속받은 class이다.
	WaitRoom	wr	= new WaitRoom(this); //oos와 ois을 WaitRoom에서 사용하기 위해서 this을 넘기자.
//	JPanel		mr	= new JPanel(); //MessageRoom.java 이것 대신에 MessageRoom을 인스턴스화 하면된다. <==JPanel을 상속받은 class이다.
	MessageRoom mr  = new MessageRoom(this); //oosdhk ois을 MessageRoom에서 사용하기 위해서 this을 넘기자.
	SettingRoom sr  = new SettingRoom(this);
	Socket				mySocket 	  = null;
	ObjectInputStream 	ois 	  	  = null;
	ObjectOutputStream 	oos 	  	  = null;
	final static String _IP   		  = "192.168.0.15"; //"127.0.0.1"
	final static int    _PORT 		  = 3000; //0~65575사이에 포트사용가능 1020까지는 운영체제가 사용. 사용x
	//대화명을 담는 변수
	String nickName = null;
	LoginForm loginForm = null;
	
	public TalkClientVer2() {}
	public TalkClientVer2(LoginForm loginForm) {
		this.loginForm = loginForm;
		this.nickName = loginForm.nickName; //로그인 화면에서 결정된 대화명으로 동기화
		initDisplay();
		//화면 처리 후 서버소켓 접속하기 
		connect_process();
	}

	public void connect_process() {
		this.setTitle(nickName+"님의 대화창");
		//소켓통신은 예외처리 해야된다.
		try {
			mySocket = new Socket(_IP,_PORT);
			//말하고 듣기을 위한 준비
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
			//준비가 끝났으니 서버에게 말하자.
			oos.writeObject(Protocol.WAIT+Protocol.SEPERATOR+nickName+Protocol.SEPERATOR+"대기");
			//말하고 듣기
			//내가 한 말도 서버를 경우하고 듣는다[Thread - run()] - 꼭 기억할 것.
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();
			
		} catch (Exception e) {
			System.out.println("Exception: "+e.toString());
			e.printStackTrace();
		}
	}
	
	public void initDisplay() {
		this.getContentPane().setLayout(null); // default값은 borderlayout
		jtp.addTab("대기실", wr);
		jtp.addTab("단톡방", mr);
		jtp.addTab("설정", sr);
//		wr.setBackground(Color.orange); WaitRoom에서 붙힐 것이다.
//		mr.setBackground(Color.red); MessageRoom에서 붙힐 것이다.
		this.getContentPane().setBackground(new Color(158,217,164)); //배경의 색상을 정해보자.
		jtp.setBounds(5,4,620,530);
		this.getContentPane().add(jtp); //컨테이너 위에 JTabbedPane를 붙히자.
		this.setSize(650, 580);
		this.setVisible(true);
		jtp.setSelectedIndex(0); //프로그램을 실행할때 인덱스의 번호가 default로 나타남.
	}
	
	public static void main(String[] args) {
		TalkClientVer2 tc = new TalkClientVer2(new LoginForm());
	}
}
