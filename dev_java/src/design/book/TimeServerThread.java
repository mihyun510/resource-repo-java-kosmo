package design.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//여러개의 Thread를 관리가능하다.
public class TimeServerThread extends Thread {
	TimeServer ts = null;
	ObjectInputStream ois = null; //읽기 - 듣기, 메신저 클래스에서도 사용
	ObjectOutputStream oos = null;//쓰기 - 말하기
	String time = null;
	public TimeServerThread(TimeServer ts) {
		this.ts = ts;
		try {
			 //소켓을 활용하여 PrintWriter객체를 생성하므로 NullPointerException이
			 //발생하지 않도록 생성자를 통해서 초기화 해준다
			 //생성자의 기본역할이 전변의 초기화.
			 //서버에서 클라이언트로 말하기 구현
			 //네트워크를 통해서 읽기와 쓰기를 지원하는 패키지 java.io.*
			 //반드시 접속을 해온 클라이언트 측에 소켓객체를 통해서 인스턴스화 할 것.
			 oos = new ObjectOutputStream(ts.socket.getOutputStream());
			 //TimeServer에서 정의한 setTimer메소드에서 현재 장치에 시간정보 가져오기.
			 time = ts.setTimer();
			 //클라이언트가 말한 내용을 듣기.
			 ois = new ObjectInputStream(ts.socket.getInputStream()); //소켓 자체는 server에 있고 지금은 thread클래스 안에 잇으니 객체로 접근하자.
			 oos.writeObject(time); //나 이전에 들어온 사람에게 메세지 전달을 위해서..
			 //내가 입장하기 전에 있던 친구들에게 전송하기.
			 for (TimeServerThread tst: ts.globalList) {//for문의 반복횟수는 클라이언트의 갯수
				this.send(time); //send라는 메소드를 만들어서 메세지를 보내는 것을 하자.
			}
			 ts.globalList.add(this); //this = 현재 들어와있는 thread의 주소. 즉 사용자가 접속할때마다 주소가 다름을 알 수 있다. 한개가 아니라 n개이다.
			 this.broadCasting(time); //나 입장 후 메세지 전달. 나
		} catch (Exception e) {
			System.out.println("TimeServerThread:"+e.toString());
		}
	}//////end 생성자 
	
	//현재 서버에 접속한 사용자에게 시간 정보 방송하기 구현
	public void broadCasting(String msg) {
		//현재 서버에 몇사람이 있는지 출력하기
		ts.jta_log.append("현재 인원수:"+ts.globalList.size()+"\n");
		synchronized (this) {//다른 스레드가 인터셉트 하는 것을 방어하기 위해 동기화처리함
			for (TimeServerThread tst: ts.globalList) {//담긴 클라이언트(정보)에게 모두 
				tst.send(msg); 							//메세지를 보내자.
			}
		}
	}/////////end broadCasting
	
	//서버에서 클라이언트에게 전송하는 메세지 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg); //클라이언트의 ois로 메세지를 전송하자.
		} catch (Exception e) {
			e.printStackTrace(); //예외 발생시 에러메세지 history출력을 해주어야 에러를 잡기 좋아요.
		}
	}//////end send
	public void run() { //스레드 구현의 핵심
		while(true) {
			try {//thread안에 있는 함수는 try-catch안에 넣어주자 -원칙-
				//TimeServer에서 정의한 setTimer메소드에서 현재 장치에 시간정보 가져오기.
				time = ts.setTimer();
				oos.writeObject(time); //1초마다 보내기.
				//슬립은 생성자 안에 있는 것이 옳지않음.
				sleep(1000); //1초 동안 지연 시키기 - 이런 지연처리를 하기 위해서는 반드시 thread를 구현해주어야된다.-> sleep() : 프로그램의 기다림을 실행시켜줌.
			} catch(IOException ie) {
				System.out.println(ie.toString());
			} catch (InterruptedException e) {
				System.out.println("다른 스레드가 새치기를 했을 때");
			} 
			//스레드 클래스에 따로 구현하였으니 필요가 없어졋어요.
//				finally {
//				System.out.println("client disconnected...\n");
//				try {
//					//사용한 자원은 무조건 반납하기.
//					//그렇지 않으면 해킹이나 다른 사람이 정보를 훔쳐볼수 있으니까.
//					//소켓을 가지고 있다면 말하기와 듣기가 가능하다.
//					ts.socket.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		}////////////end of while
	}////////////////end of run
}

