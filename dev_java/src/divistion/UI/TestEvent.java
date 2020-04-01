package divistion.UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TestEvent implements ActionListener {

	//선언부
	TestView tv = null;
	//생성자
	public TestEvent(TestView tv) {
		this.tv = tv;
	}
	//이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(tv.jbtn_change == obj) {//너 변경버튼 클릭한거야?
			Container cont = tv.getContentPane(); //컨테이너를 가져오자
			cont.remove(tv.ts); //컨테이너의 ts를 삭제하자
			cont.remove(tv.ts.jtf_msg); //컨테이너의 ts의 jtf_msg 컴포넌트를삭제하자
			JPanel jp = new JPanel(); //그리고 다시 판넬을 깔고 버튼을 붙힐거야
			JButton jbtn = new JButton("테스트");
			jp.add(jbtn);
			tv.add("South",jp); //그리고 다시 프레임위에 판넬을 붙힐거야
			cont.revalidate(); //새로고침
		}
		else if(tv.ts.jtf_msg == obj) {
			tv.ts.jtf_msg.setText("오늘 스터디 할까?"); //엔터치면 오늘 스터디 할까 ? 입력된다.
		}
	}//////////end actionPerformed()

}
