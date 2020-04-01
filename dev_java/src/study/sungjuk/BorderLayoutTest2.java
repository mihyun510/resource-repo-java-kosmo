package study.sungjuk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//속지를 사용해서 창에 나타내보자.
import javax.swing.JTextField;
public class BorderLayoutTest2 {
	JFrame jf = new JFrame(); //디폴트 레이아웃이 BorderLayout[동, 서, 남, 북, 중앙] 방향으로 원하는 위치에 배치가능
	JPanel jp_north		= new JPanel(); //생성자를 받지 않음.
	JLabel jlb_su		= new JLabel("인원수");
	JLabel jlb_su2		= new JLabel("인원수");
	JLabel jlb_inwon		= new JLabel("명");
	JLabel jlb_inwon2		= new JLabel("명");
	JTextField jtf_inwon = new JTextField();
	JTextField jtf_inwon2 = new JTextField(15);
	JPanel jp_south		= new JPanel(); //디폴트 레이아웃이 FlowLayout으로 설정되어 있음. -> 센터에서 양쪽으로 펼치면서 배치를 함.
	JPanel jp_west		= new JPanel();
	JPanel jp_east		= new JPanel();
	JPanel jp_center	= new JPanel();
	
	public BorderLayoutTest2() { //생성자안에 그냥 처리한 것임
		//원래 FlowLayout이었는데 BorderLayout으로 변경하기
		jp_north.setLayout(new BorderLayout());
		jf.add("North",jp_north); //창에 속지를 추가해보자 . 창에다가 북쪽에 속지를 붙히자
		jp_north.add("West",jlb_su); //북쪽 속지에 수를 붙히기
		jp_north.add("East",jlb_inwon); //북쪽 속지에 수를 붙히기
		jp_north.add("Center",jtf_inwon); //북쪽 속지에 텍스창을 붙히기
		jp_north.setBackground(Color.red); ///북쪽 속지 색깔 빨강색
		
		jp_south.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬
		jf.add("South",jp_south);
		jp_south.add(jlb_su2);
		jp_south.add(jtf_inwon2);
		jp_south.add(jlb_inwon2);
		jp_south.setBackground(Color.yellow);
		
		
		jf.add("West",jp_west);
		jp_west.setBackground(Color.green);
		
		jf.add("East",jp_east);
		jp_east.setBackground(Color.black);
		
		jf.add("Center",jp_center);
		jp_center.setBackground(Color.pink);
		
		jf.setSize(500, 400); //창의 크기
		//화면에 JFrame을 출력해주세요.
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new BorderLayoutTest2();
	}
}
