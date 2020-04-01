package book.chap05;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelBookVer2 {//객체배열을 사용한 코드

	//선언부
	int width = 600;
	int height = 500;
	String title = "전화번호부-객체배열적용";
	JPanel jp_north = new JPanel();
	JButton jbtn = new JButton("전화번호 출력할 영역");
	//insert here-선언부 영역에는 제어문이나 실행문을 적을 수 없다.
	JButton[] jbtns = new JButton[4];	
	String[] jbtn_label = {"조회","입력","수정","삭제"};
//	JButton jbtn_sel = new JButton("조회"); 객체배열로 받았으니 없어도 되는 부분
//	JButton jbtn_ins = new JButton("입력");
//	JButton jbtn_upd = new JButton("수정");
//	JButton jbtn_del = new JButton("삭제");
	GridLayout glay = new GridLayout(1,4);
	//생성자
	public String toString() {
		return "나는 TelBook클래스임.";
	}
	//화면처리부
	public void display() {
		System.out.println("display 호출 성공");
		//윈도우 화면에 창을 만들어주는 클래스 입니다. 가로세로 변경 가능, 제목도 바꿀수 있음
		JFrame jf_tel = new JFrame();
		//속지의 레이아웃을 GridLayout 1,4로우 한개 컬럼 4개로 n분할
		jp_north.setLayout(glay);
		for (int i = 0; i < jbtns.length; i++) {
			jbtns[i] = new JButton(jbtn_label[i]); //0번째 방(주소)에 조회, 1번째 방(주소)에 입력, 2번째 방(주소)에 수정, 3번째 방(주소)에 삭제
			jp_north.add(jbtns[i]);
		}
//		jp_north.add(jbtn_sel); 객체배열로 받으면 해결 변수명(주소)만 바뀐것을 알 수 있음
//		jp_north.add(jbtn_ins);
//		jp_north.add(jbtn_upd);
//		jp_north.add(jbtn_del);
		//화면의 크기를 정해주세요. 600, 500
		jf_tel.setSize(width, height);
		jf_tel.setTitle(title);
		jf_tel.add("North",jp_north);//북쪽에 속지를 넣겠다.
		jf_tel.add("Center",jbtn); //중앙에 버튼을 놓겠다
		jf_tel.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		TelBookVer2 tb = new TelBookVer2();
		tb.display();
	}
}
