package thread.talk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import thread.bank.Protocol;

public class TalkServerThread extends Thread {
	public TalkServer ts = null;
	Socket client = null; //클라이언트의 정보를 가지고 있는 소켓
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null; //현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
		this.client = ts.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			
			//ip주소 출력
			ts.jta_log.append("ip:"+client.getInetAddress().getHostAddress());
			//JTextArea에 현재 입장한 클라이언트 스레드 이름 출력하기
			//										┌> Thread-3이 들어오고 Thread-4가 들어왔다면 현재의 this는 Thread-4
			ts.jta_log.append(", this.getName():"+this.getName()+"\n"); //방금 접속한 클라이언트의 정보를 서버로그창에 출력하기
			
			String msg = (String)ois.readObject(); // 여기의 msg는 현재 들어온 this의 메세지이다.
			ts.jta_log.append(msg+"\n");
			StringTokenizer st = new StringTokenizer(msg,"#");
			st.nextToken();//100
			chatName = st.nextToken();//닉테임
			ts.jta_log.append(chatName+"님이 입장하였습니다.\n");
			
			for(TalkServerThread tst:ts.globalList) {
				//이전에 입장해 있는 친구들 정보 받아내기 - 닉네임을 받는다.
				String currentName = tst.chatName; //이전에 들어온 닉네임을 변수로 받아보자.
				this.send(100+"#"+currentName); //여기에 보내지는 msg는 this의 메세지가 아닌 현재 메세지가 아닌 이전에 들어온 사람의 닉네임를 보내는 것이다.
			}
			
////////////////////////////////////////////////////////////////////////////////////////////////			
//			//나 전에 입장한 사람이 한 명도 없을 때는 아래 for문 실행기회 없음
//			//내가 입장하기 전에 입장한 클라이언트 스레드 이름 출력하기
////								   ┌> tst는 이전의 Thread의 정보이니 이전에 들어온 Thread-3
//			for (TalkServerThread tst: ts.globalList) {
////										┌> 이전의 Thread의 정보를 가지고 있는 tst의 socket을 접근
//				ts.jta_log.append("ip:"+tst.client.getInetAddress().getHostAddress());
//				ts.jta_log.append(", tst.getName():"+tst.getName()+"\n"); // 이전에 접속한 클라이언트의 이름과 나까지 모두 서버로그창에 출력하기
//			}
///////////////////////////////////////////////////////////////////////////////////////////////			
			
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		for (TalkServerThread tst : ts.globalList) {
			tst.send(msg);
		}
	}
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String msg = null;
		boolean isStop = false;
//		while(true)  이렇게 쓰지말기 - 무한 루프에 빠질수 있는 위험한 코드
		//while문은 break로 전체로 빠져나가지 못한다. 그러니 JLAbel을 사용해서 탈출하자 
		run_start:
		try {
			while(!isStop) {
				msg = (String) ois.readObject();
				ts.jta_log.append(msg+"\n"); //여기로 들어온 메세지가 클라이언트에서 말한 메세지 인가?
				ts.jta_log.setCaretPosition(ts.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				int protocol = 0;//100:입장|200:1대1|201:단톡방|202:대화명변경|500:나가기
				if(msg!=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch (protocol) {
				case 200:{ //1:1채팅
					//보내는 사람
					String nickName = st.nextToken();
					//받는 사람
					String otherName = st.nextToken();
					//보내진 메세지
					String msg1 = st.nextToken();
					//클라이언트로 전송하기
					//스레드 중에서 상대 스레드에게만 메세지 전송할 것
					for(TalkServerThread tst:ts.globalList) {
						//현재 단톡방에서 내가 선택한 상대와 상대의 chatName이 같은지 비교해서 같으면 메세지를 선택한 상대에게 보내자.
						if(otherName.equals(tst.chatName)) { //내가 선택한 상대가 맞는 거야?
							tst.send(200+"#"+nickName+"#"+otherName+"#"+msg1);
						}
					}
					//그리고 나 자신에게도 전송해보자. - 서버를 경우에서 나도 받아보기
					send(200+"#"+nickName+"#"+otherName+"#"+msg1); //this.send()라고 안해도 나자신안에 있는 send()일테니 자기자신에게 보내질 거같은데?
				} break;
				case 201:{ //n:n채팅
					String nickName = st.nextToken();
					String message = st.nextToken();
					broadCasting(201+"#"+nickName+"#"+message);
				} break;
				case 202:{ //대화명 바꾸기
					String nickName = st.nextToken();
					String afterName = st.nextToken();
					String message = st.nextToken();
					this.chatName = afterName;
					//			 ┌>switch의 case 주의!!!!
					broadCasting(202+"#"+nickName+"#"+afterName+"#"+message);
				} break;
				case 500:{ //여기를 들어온 사람은 나임. 그러니 나를 삭제해야 하니 this을 뺴야된다.
					//여기 들어오는 닉네임은 나갈려고 버튼을 누른 사람의 닉네임이다.
					String nickName = st.nextToken(); //들은 닉네임을 읽어와서
					ts.globalList.remove(this); //나 자신을 정보를 모아놓은 리스트에서 삭제하자.
					broadCasting(500+"#"+nickName); //모든 사람들에게 말해주자.
				} break run_start;
				}////////////end switch
			}////////end while
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}///////end run()
	
}//////end TalkServrThread.class
