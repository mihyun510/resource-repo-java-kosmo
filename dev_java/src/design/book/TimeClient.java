package design.book;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeClient extends Thread {
	JFrame jf = new JFrame();
	JLabel jlb_time = null;
	JLabel jlb_time2 = new JLabel("현재시간",JLabel.CENTER);
	//run메소드 보다 TimeClient생성자가 반드시 먼저 실행되어야 한다. - 화면을 먼저 구현해야 되기 때문이지.
	public TimeClient(){ //클라이언트 화면에 시간을 붙혀보자. JLabel을 사용해보자.
		jf.add("North", jlb_time2);
		jf.setSize(500, 400);
		jf.setVisible(true);
	}
	/*
	 * 객체 주입관계는 BookApp.java에서 TimeClient(JFrame)생성자를 호출함.
	 */
	public TimeClient(JLabel jlb_time) {
		this.jlb_time = jlb_time;
	}
	//서버에서 청취한 현재 시간 정보를 담을 변수
	public void run() {
		String time = null;
		Socket socket = null;
		ObjectInputStream ois = null; //읽기 - 듣기, 메신저 클래스에서도 사용
		ObjectOutputStream oos = null;//쓰기 - 말하기
		try {
			socket = new Socket("192.168.0.15", 3000);
			oos = new ObjectOutputStream(socket.getOutputStream()); //일방적인 듣기만 할 예정 - 즉, out이 필요없음.
			//클라이언트가 말한 내용 듣기
			ois = new ObjectInputStream(socket.getInputStream());
			while(true) {
				time = (String)ois.readObject();
//				System.out.println(time);
				Font f = new Font("sans serif", Font.BOLD, 30);
				jlb_time.setFont(f); //라벨에다가 시간을 붙혀서 보여주자.
				jlb_time.setText(time); //라벨에다가 시간을 붙혀서 보여주자.
//				while(time != null) { //읽어온 정보를 변수에 담아서 null이 아니면
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("앗~~...");
					}
//				}
			}
		} catch (Exception e) { //인터럽터 에러에 잡히지 않앗다면 입출력 에러에서 잡자.
			System.out.println("타임 서버에 접속할 수 없습니다.");
		}
	}
	public static void main(String[] args) {
		//BookApp으로 입양가야된다.
//		TimeClient tc = new TimeClient();
//		tc.start();
	}
}
