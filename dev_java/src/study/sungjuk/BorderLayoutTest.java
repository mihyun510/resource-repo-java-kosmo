package study.sungjuk;

import javax.swing.JButton;
import javax.swing.JFrame;
//버튼을 사용해서 창에 붙혀보자

public class BorderLayoutTest { 
	JFrame jf = new JFrame(); //디폴트 레이아웃이 BorderLayout[동, 서, 남, 북, 중앙] 방향으로 원하는 위치에 배치가능
	JButton jbtn_north 	= new JButton("북쪽");
	JButton jbtn_south 	= new JButton("남쪽");
	JButton jbtn_west 	= new JButton("서쪽");
	JButton jbtn_east 	= new JButton("동쪽");
	JButton jbtn_center = new JButton("중앙");
	public BorderLayoutTest() { //생성자안에 그냥 처리한 것임
		jf.add("North",jbtn_north); //창에 버튼을 추가해보자
		jf.add("South",jbtn_south);
		jf.add("West",jbtn_west);
		jf.add("East",jbtn_east);
		jf.add("Center",jbtn_center);
		jf.setSize(500, 400); //창의 크기
		//화면에 JFrame을 출력해주세요.
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new BorderLayoutTest();
	}
}
