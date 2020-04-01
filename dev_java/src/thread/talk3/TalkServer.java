package thread.talk3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sun.rmi.log.ReliableLog.LogFile;

public class TalkServer extends JFrame implements Runnable {

	TalkServerThread 		tst 		= null;
	List<TalkServerThread> 	globalList 	= null;
	int 					port 		= 3000;
	ServerSocket 			server 		= null;
	Socket 					socket		= null;
	
	JTextArea 				jta_log 	= new JTextArea(10,30);
	JScrollPane 			jsp_log 	= new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JPanel 					jp_north 	= new JPanel();
	JButton 				jbtn_log 	= new JButton("로그 저장"); //로그파일을 만드는 버튼. - 로그파일: 문제가 일어나거나 컴플라인이 들어온 내용이 저장되어져있다.
	String 					logpath 	= "src\\thread\\log\\";
	
	public void initDisplay() {
		jbtn_log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();
				if(obj == jbtn_log) { //	┌>현재날짜을 반환하는 메소드
					String fileName = "log_"+setTimer()+".txt"; //파일이름을 지정
					System.out.println(fileName);//log_2020-03-13.txt ※ 파일에 공백이 들어가지 않게 조심해야된다.
					try {
						//자바는 모든 기능 사물들을 클래스로 설계하도록 유도한다.
						//파일명을 클래스로 만들어 주는 API가 존재한다. = File
						File f = new File(logpath+fileName);
						//파일명만 생성될 뿐 내용까지 만들어주지는 않는다.
						//내용부분을 담는 별도의 클래스가 필요하다.
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f.getAbsolutePath()))); 
//															 └>필터클래스 - 카메라의 필터같은것(야경의 효과?같이 효과넣어줌.) 성능을 좋게해줌.
						//io패키지에는 단독으로 파일을 컨트롤 할 수 있는 클래스가 있고 
						//그 클래스에 연결해서 사용하는 필터 클래스가 존재함.(기능을 향상해줌)
						pw.write(jta_log.getText());
						pw.close();//사용한 입출력 클래스는 반드시 닫아준다. 열려있으면 누군가 열람이 가능하기 때문이다.
					} catch (Exception e) {
						//예외가 발생했을 때 출력함.
						//예외가 발생하지 않으면 실행이 안됨.
						System.out.println(e.toString());
					}
				}
				
			}
		});
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jta_log.setBackground(Color.orange);
		jp_north.add(jbtn_log);
		this.add("North",jp_north);
		this.add("Center",jsp_log);
		this.setTitle("서버 로그창");
		this.setSize(500,400);
		this.setVisible(true);
	}
	//서버소켓과 클라이언트측 소켓을 연결하기
	@Override
	public void run() {
		//서버에 접속해온 클라이언트 스레드 정보를 관리할 벡터 생성하기
		globalList = new Vector<TalkServerThread>(); //클라이언트의 정보(Thread)가 담길 예정이다.
		boolean isStop = false;
		try {
			server = new ServerSocket(port);
			jta_log.append("Server Ready..........\n"); //서버가 준비되었다면 출력
			while(!isStop) {
				socket = server.accept();
				jta_log.append("Client info:"+socket+"\n"); //클라이언트가 접속했다면 출력
				TalkServerThread tst = new TalkServerThread(this);
				tst.start(); //스레드를 기동을 시켜야 말하기와 듣기를 계속 진행가능
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//메인 메소드
	public static void main(String[] args) { //main 스레드 [Thread-1]
		JFrame.setDefaultLookAndFeelDecorated(true);
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		Thread th = new Thread(ts); //Runnable을 구현하기 위해서 인스턴스화한 스레드 [Thread-2]
		th.start();
	}
	
	/*******************************************************
	 * 시스템의 오늘 날짜 정보 가져오기
	 * @param 해당사항 없음.
	 * @return String 2020-03-13
	 *******************************************************/
	public String setTimer() { 
		Calendar cal = Calendar.getInstance();
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);//1~24 
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		return yyyy+"-"+
			   (mm < 10 ? "0" + mm: "" + mm)+"-"+
			   (day < 10 ? "0" + day: "" + day);
	}////////////end setTimer()
}
