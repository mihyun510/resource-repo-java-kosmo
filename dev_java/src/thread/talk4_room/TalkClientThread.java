package thread.talk4_room;

import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TalkClientThread extends Thread{
	
	TalkClientVer2 tc = null;
	
	public TalkClientThread(TalkClientVer2 tc) {
		this.tc = tc;
	}//end 생성자(TalkClient tc)
	
	public SimpleAttributeSet makeAttribute(String fcolor) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		sas.addAttribute(StyleConstants.ColorConstants.Foreground, new Color(Integer.parseInt(fcolor)));
		return sas;
	}
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	@Override
	public void run() {
		String msg = null;
		boolean isStop = false;
		while(!isStop) {
			try {
				msg = (String)tc.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0;
				if(msg != null) {
					System.out.println("Client:"+msg);
					st = new StringTokenizer(msg, Protocol.SEPERATOR);
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
					case Protocol.WAIT:{
						System.out.println("대기:"+protocol);
						String nickName = st.nextToken();
						String state = st.nextToken(); //상태 , 대기/단톡방 입장
						Vector<String> v_nick = new Vector<String>();
						v_nick.add(nickName); //나신입 - 입장한 사람의 이름
						v_nick.add(state); //대기 - 입장한 사람의 상태값
						tc.wr.dtm_wait.addRow(v_nick); //그리고 나서 클라이언트의 정보를 대기방 테이블목록안에 추가해주자.
					} break;
					case Protocol.ROOM_CREATE:{
						String roomtitle = st.nextToken();
						String currentNum = st.nextToken();
						Vector<String> v_room = new Vector<String>();
						v_room.add(roomtitle);
						v_room.add(currentNum);
						tc.wr.dtm_room.addRow(v_room);
					} break;
					case Protocol.ROOM_LIST:{ //나중에 들어온 사람들에게 업데이트 처리를 하자.
						String roomtitle = st.nextToken();
						String currentNum = st.nextToken(); //인원수
						Vector<String> v_room = new Vector<String>(); //방정보
						v_room.add(roomtitle);
						v_room.add(currentNum);
						tc.wr.dtm_room.addRow(v_room);
					} break;
					case Protocol.ROOM_IN:{
						String roomTitle = st.nextToken();
						String current = st.nextToken();
						String nickName = st.nextToken();
						//방정보 인원 갱신
						for(int i=0; i<tc.wr.jtb_room.getRowCount(); i++) {
							if(roomTitle.equals(tc.wr.dtm_room.getValueAt(i, 0))) {
								tc.wr.dtm_room.setValueAt(current, i, 1);
								break;
							}
						}
						//대기실의 위치 갱신 - 대기>하늘소
						for(int i =0; i<tc.wr.jtb_wait.getRowCount(); i++) {
							if(nickName.equals((String)tc.wr.dtm_wait.getValueAt(i, 0))) {
								tc.wr.dtm_wait.setValueAt(roomTitle, i, 1);
							}
						}
						//대화방으로 이동하기 - messageroom화면으로 이동
						//방입장하기 버튼을 누른 사람만 화면 이동처리
						if(tc.nickName.equals(nickName)) {
							tc.jtp.setSelectedIndex(1); //0이면 waitroom 1이면 messageroom이다..
							for(int x=0; x<tc.wr.jtb_wait.getRowCount();x++) {
								if(roomTitle.equals(tc.wr.dtm_wait.getValueAt(x, 1))) { //타이틀을 비교해서 일지하면
									String imsi[] = {(String)tc.wr.dtm_wait.getValueAt(x, 0)}; //일치할때의 1:방이름
									tc.mr.dtm_nick.addRow(imsi);
								}//////단톡이름을 비교
							}
						}////////이름을 비교
					}break;
					case Protocol.ROOM_INLIST:{ //단톡방안에 들어온사람에게 미리 들어와있는 사람을 보여주자. 
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						String nickNmae = st.nextToken();
						Vector<String> v_room = new Vector<String>();
						v_room.add(nickNmae);
						tc.mr.dtm_nick.addRow(v_room);
					} break;
				}//////////////////////////end switch
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}////////////////////end while
	}//////////////////end run()
}
