package thread.talk;

import java.util.StringTokenizer;
import java.util.Vector;

public class TalkClientThread extends Thread{
	TalkClient tc = null;
	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
	}///////////////end 생성자(TalkClient tc)
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	@Override
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = ""; //100#apple 메세지는 그때 마다 달라지니 지역변수로 한다.
				msg = (String)tc.ois.readObject();
				//┌>?????
				tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());//getDocument():문서 전체를 받아준다.
				StringTokenizer st = null;
				int protocol = 0;//100:입장|200:1대1|201:단톡방|202:대화명변경|500:나가기
				if(msg!=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch (protocol) {
					case 100: { //입장
						String nickName = st.nextToken();
						tc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						Vector<String> v = new Vector<>();
						v.add(nickName);
						tc.dtm.addRow(v);
					} break;
					case 200: {//1대1
						String nickName = st.nextToken();
						String otherName = st.nextToken();
						String message = st.nextToken();
						tc.jta_display.append(nickName+"님이"+otherName+"님에게 "+message+"\n");
						tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					} break;
					case 201: {//단톡방
						String nickName = st.nextToken();
						String message = st.nextToken();
						tc.jta_display.append("["+nickName+"]"+message+"\n");
					} break;
					case 202: {//대화명 변경
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message = st.nextToken();
						//테이블의 대화명 변경하기
						//dtm의 테이블 대화명
						for (int i = 0; i < tc.dtm.getRowCount(); i++) {
							String imsi = (String)tc.dtm.getValueAt(i, 0); // 임시로 대화명을 담을 변수
							if(nickName.equals(imsi)) {
								tc.dtm.setValueAt(afterName, i, 0); //값 , 행, 열
								break; //리턴하면 메소드를 탈출 그리고 return은 for문을 탈출
							}
						}
						//채팅창에 타이틀바에도 대화명을 변경처리 한다.
						if(nickName.equals(tc.nickName)) {
							tc.setTitle(afterName+"님의 대화창");
							tc.nickName = afterName;
						}
						tc.jta_display.append(message+"\n");
					} break;
					case 500: {//나가기
						//나가기로 한 사람의 닉네임이 들어온다.
						String nickName = st.nextToken();
						//					┌> 추가되어져 잇는 행의 크기만큼 반복한다
						for (int i = 0; i < tc.dtm.getRowCount(); i++) {
							tc.jta_display.append(nickName+"님이 퇴장하셨습니다.\n");
							String n = (String)tc.dtm.getValueAt(i, 0);//닉네임을 받아서
							if(n.equals(nickName)) { //토큰으로 받은 닉네임과 같다면 
								tc.dtm.removeRow(i);//해당 행을 삭제하자.
							}
						} 
					}break;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}////////////////////end while
	}//////////////////end run()
}
