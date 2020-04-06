package exam0407;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.hamcrest.core.SubstringMatcher;

public class MyCalcView extends JFrame implements ActionListener{
	//선언부
	JPanel jp_north = new JPanel();
	JMenuBar jmb_menu = new JMenuBar();
	JMenu jm_menu = new JMenu("도움말");
	JMenuItem jmi_people = new JMenuItem("만든사람들.");
	JSeparator jst_menu = new JSeparator();
	JMenuItem jmi_exit = new JMenuItem("종료");
	
	//JFrame - BorderLayout
	JTextField jtf_account = new JTextField(20); //North
	
	JPanel jp_center = new JPanel();
	JButton jbtn_num0 = new JButton("0");
	JButton jbtn_num1 = new JButton("1");
	JButton jbtn_num2 = new JButton("2");
	JButton jbtn_num3 = new JButton("3");
	JButton jbtn_num4 = new JButton("4");
	JButton jbtn_num5 = new JButton("5");
	JButton jbtn_num6 = new JButton("6");
	JButton jbtn_num7 = new JButton("7");
	JButton jbtn_num8 = new JButton("8");
	JButton jbtn_num9 = new JButton("9");
	
	JButton jbtn_plus = new JButton("+");
	JButton jbtn_mius = new JButton("-");
	JButton jbtn_sub = new JButton("×");
	JButton jbtn_div = new JButton("÷");
	JButton jbtn_guam = new JButton(".");
	JButton jbtn_elqaul = new JButton("=");
	
	JButton jbtn_ac = new JButton("AC");
	JButton jbtn_back = new JButton("←");
	JButton jbtn1 = new JButton("");
	JButton jbtn2 = new JButton("");
	
	int dap = 0;
	
	//생성자
	public MyCalcView() {
		initDisplay();
	}
	public int MethodA(int a, int b) {
		int result =0;
		System.out.println("a:"+a+", b:"+b);
		if(a==b) result = 1;
		return result;
	}
	//화면처리부
	public boolean initDisplay() {
		System.out.println("initDisplay호출 성공");
		jtf_account.setHorizontalAlignment(JTextField.RIGHT);
		jbtn_ac.addActionListener(this);
		jbtn_back.addActionListener(this);
		jbtn_num0.addActionListener(this);
		jbtn_num1.addActionListener(this);
		jbtn_num2.addActionListener(this);
		jbtn_num3.addActionListener(this);
		jbtn_num4.addActionListener(this);
		jbtn_num5.addActionListener(this);
		jbtn_num6.addActionListener(this);
		jbtn_num7.addActionListener(this);
		jbtn_num8.addActionListener(this);
		jbtn_num9.addActionListener(this);
		boolean isOk = true;
		jtf_account.setEditable(false); //입력하지 못하게 한다.
		jp_north.setLayout(new GridLayout(2,1));
		jm_menu.add(jmi_people);
		jm_menu.add(jst_menu);
		jm_menu.add(jmi_exit);
		jmb_menu.add(jm_menu);
		jp_north.add(jmb_menu);
		jp_north.add(jtf_account);
		this.add("North", jp_north);
		
		jp_center.setLayout(new GridLayout(5,4,2,2));
		jp_center.add(jbtn1);
		jp_center.add(jbtn2);
		jp_center.add(jbtn_ac);
		jp_center.add(jbtn_back);
		jp_center.add(jbtn_num7);
		jp_center.add(jbtn_num8);
		jp_center.add(jbtn_num9);
		jp_center.add(jbtn_plus);
		jp_center.add(jbtn_num4);
		jp_center.add(jbtn_num5);
		jp_center.add(jbtn_num6);
		jp_center.add(jbtn_mius);
		jp_center.add(jbtn_num1);
		jp_center.add(jbtn_num2);
		jp_center.add(jbtn_num3);
		jp_center.add(jbtn_sub);
		jp_center.add(jbtn_num0);
		jp_center.add(jbtn_guam);
		jp_center.add(jbtn_elqaul);
		jp_center.add(jbtn_div);
		this.add("Center", jp_center);
		this.pack();
		this.setTitle("전자계산기");
		this.setSize(300,300);
		this.setVisible(true);
		if(isOk) {
			System.out.println("정상적으로 처리가 되었다.");
		} else {
			System.out.println("화면처리 실패!!!.");
		}
		return isOk;
	}
	//메인메소드
	public static void main(String[] args) {
		new MyCalcView();
	}
	
	public void hap(String strs) {
		StringTokenizer st = new StringTokenizer(strs, "+");
		String num1 = st.nextToken();
		String num2 = st.nextToken();
		System.out.println(num1);
		System.out.println(num2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_ac) {
			jtf_account.setText("");
		}
		else if(obj == jbtn_back) {
			String imsi = jtf_account.getText();
			imsi = imsi.substring(0,imsi.length()-1);
			jtf_account.setText(imsi);
		}
		else if(obj == jbtn_num0) {
			jtf_account.setText(jtf_account.getText()+"0");
		}
		else if(obj == jbtn_num1) {
			jtf_account.setText(jtf_account.getText()+"1");
			
		}
		else if(obj == jbtn_num2) {
			jtf_account.setText(jtf_account.getText()+"2");
			
		}
		else if(obj == jbtn_num3) {
			jtf_account.setText(jtf_account.getText()+"3");
			
		}
		else if(obj == jbtn_num4) {
			jtf_account.setText(jtf_account.getText()+"4");
			
		}
		else if(obj == jbtn_num5) {
			jtf_account.setText(jtf_account.getText()+"5");
		}
		else if(obj == jbtn_num6) {
			jtf_account.setText(jtf_account.getText()+"6");
		}
		else if(obj == jbtn_num7) {
			jtf_account.setText(jtf_account.getText()+"7");
			
		}
		else if(obj == jbtn_num8) {
			jtf_account.setText(jtf_account.getText()+"8");
			
		}
		else if(obj == jbtn_num9) {
			jtf_account.setText(jtf_account.getText()+"9");
		}
		else if(obj == jbtn_plus) {
			jtf_account.setText(jtf_account.getText()+"+");
			hap(jtf_account.getText());
		}
		else if(obj == jbtn_mius) {
			jtf_account.setText(jtf_account.getText()+"-");
		}
		else if(obj == jbtn_sub) {
			jtf_account.setText(jtf_account.getText()+"×");
		}
		else if(obj == jbtn_div) {
			jtf_account.setText(jtf_account.getText()+"÷");
		}
		else if(obj == jbtn_elqaul) {
			
		}
		else if(obj == jbtn_guam) {
			jtf_account.setText(jtf_account.getText()+".");
		}
	}
}