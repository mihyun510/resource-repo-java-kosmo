package book.chap05;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelBook {//객체배열을 사용하지 않은 코드

	//선언부
	int width = 600;
	int height = 500;
	String title = "전화번호부";
	JPanel jp_north = new JPanel();
	JButton jbtn = new JButton("전화번호 출력할 영역");
	//insert here
	JButton jbtn_sel = new JButton("조회");
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
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
		jp_north.add(jbtn_sel);
		jp_north.add(jbtn_ins);
		jp_north.add(jbtn_upd);
		jp_north.add(jbtn_del);
		//화면의 크기를 정해주세요. 600, 500
		jf_tel.setSize(width, height);
		jf_tel.setTitle(title);
		jf_tel.add("North",jp_north);//북쪽에 속지를 넣겠다.
		jf_tel.add("Center",jbtn); //중앙에 버튼을 놓겠다
		jf_tel.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		TelBook tb = new TelBook();
		tb.display();
	}
}
