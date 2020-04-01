package ui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class JInternalFrameTest implements ActionListener{ //버튼에 대한 이벤트 처리 ActionListener
	JFrame jf = new JFrame();
	//이 아이를 중앙에 배치하고 JInternalFrame으로 내부에 띄워줄 창을 만들면 
	//JFrame창 안에 여러개의 내부창을 관리할 수 있다.
	JDesktopPane jdp = new JDesktopPane();
	JPanel jp_north = new JPanel();
	JButton jbtn_zip = new JButton("우편번호 찾기");
	public JInternalFrameTest() {
		initDisplay();
	}
	public void initDisplay() { //버튼 3개 정도 추가해보자.
		//내안에 actionperfomed메소드를 구현하였다.
		//이 코드가 있어야 자동으로 (누가? JVM)으로 actionPerformed메소드를 호출해줌.
		jbtn_zip.addActionListener(this); //this - 내안에 있기 때문에 내 주소를 넘겨야  된다.
//		jbtn_zip.addActionListener(this); //this - 내안에 있기 때문에 내 주소를 넘겨야  된다. 이게 여러개 있으면 한번 눌러도 여러번 호출
		jf.setTitle("윈도우 창에 내부 프레임 생성하기");
		//jp_north소지에 우편번호 찾기 버튼을 오른쪽에서 부터 붙힌다.
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add(jbtn_zip);
		//북쪽에다가 jp_north속지를 붙여줄까
		jf.add("North",jp_north);
		//jf의 중앙에 JDeskTopPane속지를 붙여줄래
		jf.add("Center",jdp);
		jf.setSize(700,500);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new JInternalFrameTest();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==jbtn_zip) {//버튼을 누른게 jbtn_zip 주소랑 같음?
//			System.out.println("우편번호 찾기 호출 성공");
			InnerFrame inn = new InnerFrame(jbtn_zip.getText(), true, true, true, true); //버튼의 이름으로 창의 제목으로 사용하고 싶음
			jdp.add(inn); //창안에다가 또하나의 창을 생성
		}
	}////////////////////end zctionperformed
}

//내부의 창을 그려주는 클래스 - 여기에다가 여러가지 붙히기 가능 
class InnerFrame extends JInternalFrame{//extends 상속 받을떄 사용 아들이 더 기능이 많음
	InnerFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable){
		//아빠 생성자 호출 문장 - 이걸 하면 아빠객체가 가진 기능들을 온전히 누릴 수 있다.
		super(title,resizable,closable,maximizable);
		this.setTitle(title);
		this.setSize(300, 200);
		this.setVisible(true);
	}
}