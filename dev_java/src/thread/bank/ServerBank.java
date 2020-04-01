package thread.bank;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
//인터페이스를 추가하면 반드시 구현체 클래스가 되기 위해서는 추상메소드를 재정의 해야한다. <- 규칙
//run()메소드를 반드시 오버라이딩 해야한다.
//이 메소드 안에서는 무엇을 하지? - 기다리기 [Thread.sleep(밀리터초)], 
//						      듣기[ois.readObject()]/말하기[oos.writeObject("매세지")]
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import design.book.TimeServerThread;
//ServerBank - view와 client의 응대역할만 하는 class
public class ServerBank extends JFrame implements Runnable {
	//전역변수
	int port = 3000; //대문
	ServerSocket server = null; //서버 소켓 - 응대 
	Socket socket = null; //클라이언트 소켓받기
	
	JTextArea jta_log = new JTextArea(12,30); 
	JScrollPane jsp_log = new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	
	String[] cols = {"접속시간","접속자","메세지","상태"};
	String[][] data = new String[0][4];
	DefaultTableModel dtm_history = new DefaultTableModel(data,cols);
	JTable jtb_history = new JTable(dtm_history);
	JScrollPane jsp_history = new JScrollPane(jtb_history,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	//클라이언트에서 접속해온 사용자에 대한 스레드를 담기 위한 List선언 - 서버한개에 여러 클라이언트를 관리해야됨
	//일단 선언만 해두었다가 서버소켓이 개설되기 직전에 인스턴스화 함.
	//List는 인터페이스 이므로 단독으로 인스턴스화 불가하니까 구현체 클래스 중에서
	//여러 사람을 손실없이 관리할 수 있는 Vector객체를 생성할 것
	//클라이언트가 접속을 했을 때 스레드를 가동 시킴.
	//ServerBankThread가 서버측에서 생성한 클래스이지만 그 안에 담긴 정보는 클라이언트에 
	//대한 정보를 담고 있다.
	//클라이언트가 접속 성공하면 일단 소켓에게 서버소켓이 수집한 정보를 넘김.
	//이 정보를 넘겨 받으면 그 안에 클라이언트 정보가 담김.
	//스레가 생성되었을 때 그때 Vector안에 add처리 할것 - 그래야 그 사람 정보를 유지 가능 - 유지하기 위해서는 담자.
	//담는 작업은 스레드가 생성 되었을때 거의 동시에 일어나는 사건이므로 생성자 안에서 처리함.
	
	//스레드를 여러개 관리하겠다. -> 멀티스레드를 구현한 것이다.
	List<ServerBankThread> globalList = null; //접속해오는 client의 정보 담기
	ServerBankThread sbt = null;
	CustomerDao cDao = new CustomerDao(); //파라미터로 ex)this를 넘지지 마라. 왜냐하면 코드코드마다 의존관계 높을수록 좋지않다.
	@Override
	public void run() {
		globalList = new Vector<ServerBankThread>(); //이제 정보를 담아보자.
		JOptionPane.showMessageDialog(this, "run호출 성공 - 스레드 가동 중");
		 try {
			 server = new ServerSocket(port);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 jta_log.append("SreverBank started successfully...\n"); 
		 while(true) {
			 try {
				 
				socket = server.accept(); //클라이언트에 대한 정보가  접수
				jta_log.append("New socket connected...."+socket.toString()+"\n"); 
				sbt = new ServerBankThread(this); //여기서 this는 serverBank자신(방금 접속한 client에 대한 정보를 가진 주소)-원본-생성자호출
												  //-접수가 된 다음에 스레드를 생성하자. - 왜냐하면 그 사람의 정보에 대한 스레드를 생성해야됨
				sbt.start(); //스레드에 구현된 run()메소드 호출
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
	}////end run()
	
	//main메소드는 entry point이다.
	//메인 스레드라고도 한다. - 경합 벌어진다.
	//화면처리와 서버 개통하기
	//스레드 클래스의 run()메소드는 어떻게 호출하지?
	public static void main(String[] args) {
		ServerBank sb = new ServerBank();
		//sb.start(); -첫번째. ServerBank를 인스턴스화 해서 start()를 호출할 수 없다. 
					//왜냐하면 Thread를 상속받지 않았으니까. - 즉, 나는 Thread가 아니야.
					//어떻게 해결하지? - 일단 Thread를 인스턴스화 하고 생성자에 구현체클래스를 넣어주자.
		sb.initDisplay();
		Thread th = new Thread(sb);
		th.start();//run()메소드 호출하기. ※ run()메소드 호출전에만 화면을 호출하면 됨.※
	}//////end main
	
	public void initDisplay() { 
		//윈도우 창에서 X버튼 클릭했을 때 이벤트 처리
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {//사용 자원 반납하기
					server.close();
					socket.close();
					System.exit(0);
				} catch (Exception e) {
					
				}
			}
		});
		this.setTitle("ServerBank 로그창");
		this.add("West",jsp_log);
		this.add("Center",jsp_history);
		this.setSize(900,400);
		this.setVisible(true);
	}/////end initDisplay - 화면
}
