package divistion.UI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
//JPanel은 단독으로 창을 띄울 수 없다.
//단독으로는 (혼자 힘으로는) 세상에 나올 수 없다.
//TestView클래스에  의존해야 화면에 나타낼 수 있다. - 의존성 주입
public class TestSouth extends JPanel {

	//선언부
	JTextField jtf_msg = new JTextField(20);
	
	//생성자
	public TestSouth() {
		initDisplay();
	}
	
	//화면 구현부
	public void initDisplay() {
		this.setLayout(new BorderLayout());
		this.add("Center",jtf_msg);
	}
	//메인메소드
	public static void main(String[] args) {
		new TestSouth();
	}
}
