package divistion.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestView extends JFrame {
	
	//선언부
	TestSouth ts = new TestSouth();
//	TestSouth ts2 = new TestSouth(this);  //1번과 2번의 생성자를 쓰는 경우는 달라요.
										  //뭐가 다르냐면 1번은 TestSouth의 디폴트 생성자를 호출하는 것이고
										  //2번은 TestSouth를 생성함과 동시에 TestSouth클래스에서 TestView의 주소를 받을 수 있도록 한는 코드다.
										  //즉, TestSouth에서 TestView의 주소가 필요할 때 인스턴스화를 하지 않고 TestView의 클래스로 접근이 가능해요.
										  //생성자로 인해서 서로 핑퐁치는 경우를 해결할 수 있죠.
	//ts를 넘기면 TestSouth만 누릴 수 있지만 this을 넘기면 TestView와 TestSouth
	//모두를 누릴 수 있다. 왜냐면 TestView는 TestSouth를 인스턴스화 했기 때문이지
	TestEvent te = new TestEvent(this);
	
	JPanel jp_north = new JPanel();
	JButton jbtn_change = new JButton("변경");
	
	//생성자
	public TestView() {
		initDisplay();
	}
	
	//화면구현부
	public void initDisplay() {
		//코드의 가독성이 좋아짐 - 여기서 addActionListener을 처리하는 경우 South가 아닌
		ts.jtf_msg.addActionListener(te);
		jbtn_change.addActionListener(te);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add(jbtn_change);
		this.add("North",jp_north);
		this.add("South",ts);
		this.setTitle("화면");
		this.setSize(500,600);
		this.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		new TestView();
	}
}
