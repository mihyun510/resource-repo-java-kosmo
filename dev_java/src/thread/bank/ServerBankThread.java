package thread.bank;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import design.book.TimeServerThread;

public class ServerBankThread extends Thread {
	ServerBank sb = null;
	//소켓안에 장착된다고 생각하기 - socket필요
	ObjectInputStream ois = null;//듣다가 끊길 수 있으니까 예외처리
	ObjectOutputStream oos = null;//말하다가 멈출 수 있으니까 try...catch
	String nickname = null;
	//생성자의 파라미터 자리에 있는 클래스와 ┌>(의존관계)에 있다.
	public ServerBankThread(ServerBank sb) { //접수된 클라이언트의 정보를 가진 서버주소를 넘겨받으며  스레드를 생성하자.
		this.sb = sb;
		try {
			oos = new ObjectOutputStream(sb.socket.getOutputStream()); //sb.socket.getOutputStream() - 서버안에 소켓(sb.socket)을 사용하는 이유는 방금 전달받으며
																	   //생성된 클라이언트의 정보를 가진 ServerBank의 소켓의 Thread을 생성한것이니 이 Thread안에서는 접속한 
																	   //client의 socket을 사용하여 이 client가 말하는 것을 듣고 말해주어야 하기 때문이다.
			ois = new ObjectInputStream(sb.socket.getInputStream());
			//서버측에서 클라이언트가 보낸 메세지 청취 완료
			String msg = (String)ois.readObject();
			sb.jta_log.append(msg+"\n");
			StringTokenizer st = null;
			//msg에 들어오는 이름이 100#닉네임1, 100#닉네임2, 100#닉네임3 즉, 닉네임은 전역변수로 선언해야지.
			//Thread마다 다른 사람을 관리한다. 그래서 모두 구분을 해야한는데 스레드를 통해서 구분한다.
			/*
			for(ServerBankThread sbt:sb.globalList) {
				sbt.nickName?
			}
			*/
			if(msg != null) {
				st = new StringTokenizer(msg, "#");
			}
			st.nextToken();//100
			//닉네임을 잘라서 변수에 담아야 유지된다.
			nickname = st.nextToken();//클라이언트의 닉네임 ex)닉네임1, 닉네임2, 닉네임3
			login(nickname); //로그인 할때 닉네임 즉, id를 매개변수로 전달받
			
			
/*			//로그인이 완료된 후 실행되어야 되는 코드 - 로그인이 완료되었다면 실행
			for (ServerBankThread sbt : sb.globalList) {// 현재 접속한 클라이언트에게 자신이 접속하기 전에 접속한 클라이언트들의
				String currentName = sbt.nickname;		// 정보를 알려주자. - 만약이 부분이 없다면 뒤에 들어온 클라이언트들은 자신보다 먼저 들어온 사람들의 정보를 알 수 없다. 
				this.send(100+"#"+currentName);			// 즉, 만약 이부분이 없다면 자신보다 늦게 들어온 사람의 정보만 알 수 있다.
			}
			sb.globalList.add(this);//this = ServerBankThread 인데 여기서 this는 접속한 클라이언트의 정보를 가지고 있는 스레드
			this.broadCasting(msg);//this는 클라이언트의 스레드 주소 그리고  그 주소의 메세지를 모두 전송해주자. 이번에는 전에 입장한 클라이언트와 나까지 포함해서 메세지 전달
*/		
			
			} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		}
	}//////////////end ServerBankThread 생성자.
	//로그인 처리 구현
	public void login(String mem_id) {
		Map<String, Object> rMap = null;
		rMap = sb.cDao.login(mem_id); //serverBank(sb)안의 인스턴스화된 cDao를 사용해보자.
		if(rMap != null) {//로그인이 성공했을 때
			String currentName = null;
			String msg2 = null;
			//로그인이 완료된 후 실행되어야 되는 코드 - 로그인이 완료되었다면 실행
			//첫번째 로그인을 성공했을때 100#땡떙떙이 찍히지 않는 이유는 밑의 for문은 
			//벡터에 정보가 추가되어 있어야 실행되는 코드이다.
			//그러니 첫번째에는 따로 메세지를 넣어주자.
			for (ServerBankThread sbt : sb.globalList) {// 현재 접속한 클라이언트에게 자신이 접속하기 전에 접속한 클라이언트들의
				//전에 로그인한 정보가 닉네임으로 들어가서 행에 추가되어야한다.
				currentName = sbt.nickname;		// 정보를 알려주자. - 만약이 부분이 없다면 뒤에 들어온 클라이언트들은 자신보다 먼저 들어온 사람들의 정보를 알 수 없다. 
//				this.send(100+"#"+currentName);			// 즉, 만약 이부분이 없다면 자신보다 늦게 들어온 사람의 정보만 알 수 있다.
				msg2 = 100+"#"+currentName;
				this.send(msg2); //방금 들어온 클라이언트한테 전에 들어온 클라이언트의 정보를 넘김
			}
			Vector<String> v = new Vector<String>();
			v.add(setTimer());
			v.add(mem_id);
			v.add(100+"#"+mem_id); //전에 들어와있던 아이들을 백터에 에드하자. 그리고 
			v.add("로그인");
			sb.dtm_history.addRow(v);
			sb.globalList.add(this);//this = ServerBankThread 인데 여기서 this는 접속한 클라이언트의 정보를 가지고 있는 스레드
			//첫번째는 여기다가 따로 넣어주는게 좋겟다
			broadCasting(100+"#"+mem_id);//this는 클라이언트의 스레드 주소 그리고  그 주소의 메세지를 모두 전송해주자. 이번에는 전에 입장한 클라이언트와 나까지 포함해서 메세지 전달
		}
		else {//로그인이 실패했을 때
			for (ServerBankThread sbt : sb.globalList) {
				this.send(110+"#"+mem_id+"님은 로그인 실패 입니다.");			
			}
			//실패했을 땐 벡터에 추가를 해주지 말자. 그러면 실패하고 로그인이 성공했을때 행이 반복해서 같은 이름으로 추가되는 것을 막을 수 있다.
//			sb.globalList.add(this);//this = ServerBankThread 
			this.broadCasting(110+"#"+mem_id+"님은 로그인 실패 입니다.");
		}
	}///////////////end login
	
	//현재 서버에 접속한 사용자에게 시간 정보 방송하기 구현
	public void broadCasting(String msg) {
		//현재 서버에 몇사람이 있는지 출력하기
		sb.jta_log.append("현재 인원수:"+sb.globalList.size()+"\n");
		synchronized (this) {//다른 스레드가 인터셉트 하는 것을 방어하기 위해 동기화처리함
			//방금 도착한 클라이언트의 정보를 전에 입장한 클라이언트들에게 모두 전달해야된다. 
			//ex)100#닉네임3이 입장한 것을 100#닉네임1, 100#닉네임2에게 모두 메세지 전달.
			for (ServerBankThread sbt: sb.globalList) {//담긴 클라이언트(정보)에게 모두 
				sbt.send(msg); 							//메세지를 보내자.
				//여기서 sbt.send(msg) 여기서 sbt인 이유는 this가 아닌
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
		
	//서버는 클라이언트와는 달리 들은 내용을 즉시(입장해 있는 사람들에게) 전달해야 하므로
	//run()메소드에서 듣기(ois)와 말하기(oos)를 동시에 진행함. 같이
	public void run() {
		sb.jta_log.append(sb.socket+"님의 정보를 관리해야 함.-생성자에서 추가");
	}
	//현재시간을 반환하는 메소드.
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
			   (day < 10 ? "0" + day: "" + day)+" "+
			   (hour < 10 ? "0"+hour : ""+hour)+":"+
			   (min < 10 ? "0"+min : ""+min)+":"+
			   (sec < 10 ? "0"+sec: ""+sec);
	}////////////end setTimer()
	
}
