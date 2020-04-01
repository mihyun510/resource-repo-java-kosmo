package design.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * 자바에서는 단일 상속만 가능함.
 * 다중 상속이 필요할 땐 인터페이스로 대신한다.
 * - 즉 thread를 상속받는 것  대신에 인터페이스의 Runnable을 사용해서 thread 구현
 * 여기서 처럼 JFrame을 이미 상속받은 경우 Thread를 또 상속 받을 수 없다.
 * 이런 경우를 지원하기 위해서 Runnable이라는 인터페이스를 활용함.
 */
public class TimeServer extends JFrame implements Runnable {
	Socket socket = null; //인스턴스화x 왜냐하면 복사본이 아니라 원본을 사용해야 하니까 반드시 null로 초기화.
	int port = 3000; //한번 결정된 문은 바꾸지 못함. - 서버의 포트를 결정 / 서버에서는 포트만 알면 된다. 클라이언트에서는 아이피와 포트번호가 필요
	//서버소켓은 사용자가 접속해 오기를 기다립니다. 언제까지 기다려야 할지 알 수 없죠..
	ServerSocket server = null; //여러사람을 받아준다. - 접속을 받아준다
	//소켓을 받아쓰면 되니 필요없어 졋다.
//	//손님이 접속해 오면 그 손님의 소켓 정보를 저장합니다.
//	Socket client = null; //한사람 한사람을 구분한다. - 클라이언트 한사람 한사람을 받아준다. 즉, 여러개의 채널을 만들수있다.
	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
																//└> 수직은 필요할때 제공                                          └> 수평을 제공하지 않는다.
	List<TimeServerThread> globalList = null; //여러사람의 정보를 담아서 관리하자.
	TimeServerThread tst = null;
	public TimeServer() {
	
	}
	public String setTimer() { //현재시간을 반환하는 메소드.
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//1~24 //상수로 받아올 수 있는 값은 거의 static 그래서 클래스로 접근가능.
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		//hour가 한자리 일때는 0을 붙혀서 시간을 나타내고 한자리가 아니라면 ""에 hour을 붙혀서 나타낸다.
		return (hour < 10 ? "0"+hour : ""+hour)+":"+
			   (min < 10 ? "0"+min : ""+min)+":"+
			   (sec < 10 ? "0"+sec: ""+sec);
	}
	 public void run() {//지연처리가능, 들은정보를 내보내기 가능, 1초에 한번씩 시간정보를 내보내야 된다.
		 globalList = new Vector<TimeServerThread>(); //경합이 벌어지니 vector가 좋을듯. 멀티스레드에 강하다. - 스레드가 일어난 후 정보를 담을 것이니 런메소드안에서 생성자를 호출하자.
		 System.out.println("run call...."); //런메소드를 확인하자.
		 try {
			 server = new ServerSocket(port);//가게문 열었습니다. 안내하기 즉, 가게문 열고 기다리는 중... 손님이 올 때까지..(ip - 주소, port - 대문)
		 } catch (Exception e) {
			 e.printStackTrace();
		 }//////////////////////end of try
		 jta_log.append("TimeServer started successfully...\n"); //콘솔창이 아닌 JTextArea의 화면을 통해서 결과를 확인하고 싶다.
		 while(true) {//무한루프 -> while탈출 불가 - 무한루프에 빠지는 위험한 코드...변수를 사용하자.
			 try {//네트워크쪽이라서 항상 예외가 일어날 수 있다.
				 //클라이언트측에서 접속해온 정보를 client소켓에 넘김.
				socket = server.accept(); //손님이 올때까지 대기 - 손님이 도착함. 그럼 socket을 부여
				jta_log.append("New socket connected...."+socket.toString()+"\n"); //접속해온 클라이언트의 정보를 출력하자
				tst = new TimeServerThread(this); //부모클래스에 대한 정보가 필요하니 this을 넘기자.
				tst.start();
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
	 }
	 /*
	  * main메소드 안에서 만들어 진것을 run메소드에서 사용하려면 생성자를 통해서
	  * 초기화를 해주어야 한다.
	  * 복사본을 사용하는 것이 아니라 메인에서 접속한 클라이언트의 소켓 원본을 사용해야한다.
	  */
	public static void main(String[] args) {
		//while 안에 있을 필요가 없다. 한번만 실행되면 되기 때문이다. 와일에있으면 반복실행됨.
//		TimeServer ts = new TimeServer();//클라이언트의 정보를 담고있는 client를 넘기자. 
//		ts.initDisplay(); //화면을 먼저 그리고 난 뒤 스레드 대기를 타도록 해야됨.
//		Thread th = new Thread(ts); //스레드를 따로 선언하자.
//		th.start();//스레드안의 run메소드를 호출하는 메소드 
	}//////////////////end main
	public void initDisplay() {
		this.setTitle("TimeServer 로그");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
}
