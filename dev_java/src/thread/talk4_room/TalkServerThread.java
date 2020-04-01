package thread.talk4_room;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class TalkServerThread extends Thread {
	public TalkServer ts = null;
	Socket client = null; //클라이언트의 정보를 가지고 있는 소켓
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null; //현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	String g_title = null; //현재 그 사람의 대화방명
	int g_current = 0; //현재 인원수 담기
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
			StringTokenizer st = null;
			if(msg != null) { //사용자가 보낸 메세지가 도착한 거야?
				st = new StringTokenizer(msg, Protocol.SEPERATOR); //(메시지, #)
			}
			if(st.hasMoreTokens()) {
				st.nextToken();//100
				chatName = st.nextToken();//닉네임
				g_title = st.nextToken(); //대기
//				ts.jta_log.append(chatName+"님이 입장하였습니다.\n");
			}
			for(TalkServerThread tst:ts.globalList) {
				//이전에 입장해 있는 친구들 정보 받아내기 - 닉네임을 받는다.
				String currentName = tst.chatName; //이전에 들어온 닉네임을 변수로 받아보자.
				String currentState = tst.g_title; 
				//프로토콜번호,구분자,현재들어와있는 사람의 이름, 구분자, 현재들어와있는 방이름.
				//여기에 보내지는 msg는 this의 메세지가 아닌 현재 메세지가 아닌 이전에 들어온 사람의 닉네임를 보내는 것이다.
				this.send(Protocol.WAIT+Protocol.SEPERATOR+currentName+Protocol.SEPERATOR+currentState); 
			}	
			//새로운 친구가 로그인을 하면 기존에 생성된 단톡을 공유하자.(목록갱신)
			//방목록은 어디에 저장되고 있는 거지? List<Room>
			for(int i=0; i<ts.roomList.size(); i++) {
				Room room = ts.roomList.get(i);
				String title = room.title;
				g_title = title; //사람마다 방이름이 바뀐다. 전역변수에 반드시 초기화할 것(초기화 완결편)
				int current = 0;
				if(room.userList!=null&&room.userList.size()!=0) {
					current = room.userList.size();
				}
				g_current = current;
				//내가 오기전에 만들어진 방의 정보를 받아서 나에게 표시해주자.
				this.send(Protocol.ROOM_LIST+Protocol.SEPERATOR+g_title+Protocol.SEPERATOR+g_current); 
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		synchronized (this) { //동기화 블록을 씌운다. 먼저 선점한 클래스가 
			//해당 스레드에 대한 lock flag값을 가지게 되고 해당 스레드가 안전하게 진행/종료
			//될때까지 인터셉트된다.
			for (TalkServerThread tst : ts.globalList) {
				tst.send(msg);
			}
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
		try {
			run_start:
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
					
					case Protocol.ROOM_CREATE:{
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						Room room = new Room(roomTitle, Integer.parseInt(currentNum));
						ts.roomList.add(room);
						this.broadCasting(Protocol.ROOM_CREATE+Protocol.SEPERATOR+roomTitle+Protocol.SEPERATOR+currentNum);
					} break;
					//130#하늘공원#나신입
					case Protocol.ROOM_IN:{
						String roomTitle = st.nextToken();
						String nickName = st.nextToken();
						for(int i = 0; i< ts.roomList.size(); i++) {
							Room room = ts.roomList.get(i);
							if(roomTitle.equals(room.title)) { //단톡명이 같니?
								g_title = roomTitle;
								g_current = room.current+1;
								//현재 인원수로 업데이트 처리
								room.setCurrent(g_current);
								room.userList.add(this);
								room.nameList.add(nickName);
							}
						} //방정보에 대한 업데이트
						//방에 있는 친구들에게 메시지를 전송 -> 
						for(int i =0; i<ts.roomList.size(); i++) {
							Room room = ts.roomList.get(i);
							String title = room.title;
							g_title = title;
							int current = 0;
							if(room.userList != null && room.userList.size() !=0) {
								//현재 대화방에 들어온 사용자 스레드의 갯수가 현재 인원 수가 됨.
								current = room.userList.size();
							}
							//유재석과 다른 이름 중에서 방이름이 같은 경우 클라이언트로 전송함.
							for(int j=0; j<room.nameList.size(); j++) { //방의 갯수만큼 반복
								if(!nickName.equals(room.nameList.get(j))) { //닉네임과 방에 있는 사람의 이름이 다르면
									if(roomTitle.equals(room.title)) { //이 사람이 있는 방의 이름과 입력한 방의 이름이 같으면
										TalkServerThread tst = room.userList.get(i); //방안에 그사람의 정보를 넣는다. - 메세지를 보낸다.
										tst.send(Protocol.ROOM_INLIST+Protocol.SEPERATOR+g_title+Protocol.SEPERATOR+g_current+Protocol.SEPERATOR+nickName);
									}
								}
							}
						}
						//현재 방에 있는 친구들 목록 뿌려주기
						broadCasting(Protocol.ROOM_IN
									+Protocol.SEPERATOR+g_title
									+Protocol.SEPERATOR+g_current
									+Protocol.SEPERATOR+chatName);
					}
					case Protocol.EXIT:{ //여기를 들어온 사람은 나임. 그러니 나를 삭제해야 하니 this을 뺴야된다. 500
						//여기 들어오는 닉네임은 나갈려고 버튼을 누른 사람의 닉네임이다.
						String nickName = st.nextToken(); //들은 닉네임을 읽어와서
						ts.globalList.remove(this); //나 자신을 정보를 모아놓은 리스트에서 삭제하자.
						broadCasting(Protocol.EXIT+"#"+nickName); //모든 사람들에게 말해주자.
					} break run_start;
				}////////////end switch
			}////////end while
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}///////end run()
	
}//////end TalkServrThread.class
