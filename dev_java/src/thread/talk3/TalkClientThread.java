package thread.talk3;

import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TalkClientThread extends Thread{
	
	TalkClient tc = null;
	
	public TalkClientThread(TalkClient tc) {
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
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = ""; //100#apple 메세지는 그때 마다 달라지니 지역변수로 한다.
				msg = (String)tc.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0;//100:입장|200:1대1|201:단톡방|202:대화명변경|500:나가기
				if(msg!=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch (protocol) {
					case Protocol.LOGIN: { //입장
						String nickName = st.nextToken();
						try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다.
							tc.sd_display.insertString(tc.sd_display.getLength(), nickName+"님이 입장하였습니다.\n", new SimpleAttributeSet());
						} catch (Exception e) {
							e.printStackTrace();
						}
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
						Vector<String> v = new Vector<>();
						v.add(nickName);
						tc.dtm.addRow(v);
					} break;
					
					case Protocol.ONE: {//1대1
						String nickName = st.nextToken();
						String otherName = st.nextToken();
						String message = st.nextToken();
						try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다.
							tc.sd_display.insertString(tc.sd_display.getLength(), nickName+"님이"+otherName+"님에게 "+message+"\n", new SimpleAttributeSet());
						} catch (Exception e) {
							e.printStackTrace();
						}
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
					} break;
					
					case Protocol.MULTI: {//단톡방
						String nickName = st.nextToken();
						String message = st.nextToken();
						String fontColor = st.nextToken();
						String imgChoice = st.nextToken();
						MutableAttributeSet attr = new SimpleAttributeSet();
						//글자색 변경은 이모티콘이 아닐때 처리해야됨 그러니 구분해보자.
						if(!imgChoice.equals("default")) { //true:이모티콘 메세지
							int i=0;
							for(i = 0;i<tc.emov.imgFile.length;i++) {
								if(tc.emov.imgFile[i].equals(imgChoice)) {
									StyleConstants.setIcon(attr, new ImageIcon("src\\thread\\emoticon\\"+tc.emov.imgFile[i]));
									try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다. 문자열의 시작위치      메세지						    스타일 입히는 객체 
										tc.sd_display.insertString(tc.sd_display.getLength(), "["+nickName+"]"+message+"\n", attr); //만약 세명이 색상을 주면 각각 색상이 달라야 된다. 한거번에 처리가 아닌 각각 처리가 가능하다.
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}////////////////////////end emoticon 일때
						if(!message.equals("이모티콘")){
							//메소드 호출하면 객체가 주입됨. 매개변수로 받은 아이가 주입이 되는 것이다.
							SimpleAttributeSet sas = makeAttribute(fontColor);
							try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다. 문자열의 시작위치      메세지						    스타일 입히는 객체 
								tc.sd_display.insertString(tc.sd_display.getLength(), "["+nickName+"]"+message+"\n", sas); //만약 세명이 색상을 주면 각각 색상이 달라야 된다. 한거번에 처리가 아닌 각각 처리가 가능하다.
							} catch (Exception e) {
								e.printStackTrace();
							}
						}///////////////////end of 이모티콘 일반 메세지 일때
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
					} break;
					
					case Protocol.CHANGE: {//대화명 변경
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
						//					┌>tc에 nickName이 사라졌다 그러니 tc.loginForm으로 nickName에 접근하자.
						if(nickName.equals(tc.loginForm.nickName)) {
							tc.setTitle(afterName+"님의 대화창");
							tc.loginForm.nickName = afterName;
						}
						try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다.
							tc.sd_display.insertString(tc.sd_display.getLength(), message+"\n", new SimpleAttributeSet());
						} catch (Exception e) {
							e.printStackTrace();
						}
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
					} break;
					
					case Protocol.EXIT: {//나가기
						//나가기로 한 사람의 닉네임이 들어온다.
						String nickName = st.nextToken();
						try { //예외처리 필수 jtextpane		┌> 길이를 잡아준다.
							tc.sd_display.insertString(tc.sd_display.getLength(), nickName+"님이 퇴장하셨습니다.\n", new SimpleAttributeSet());
						} catch (Exception e) {
							e.printStackTrace();
						}
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
						//					┌> 추가되어져 잇는 행의 크기만큼 반복한다
						for (int i = 0; i < tc.dtm.getRowCount(); i++) {
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
