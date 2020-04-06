package exam0407;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class MyCalcViewThGB extends JFrame{
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
	GridBagLayout gBag = new GridBagLayout();
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
	
	//생성자
	public MyCalcViewThGB() {
		initDisplay();
	}
	//화면처리부
	public boolean initDisplay() {
		System.out.println("initDisplay호출 성공");
		boolean isOk = false;
		jtf_account.setEditable(false); //입력하지 못하게 한다.
		jp_north.setLayout(new GridLayout(2,1));
		jm_menu.add(jmi_people);
		jm_menu.add(jst_menu);
		jm_menu.add(jmi_exit);
		jmb_menu.add(jm_menu);
		jp_north.add(jmb_menu);
		jp_north.add(jtf_account);
		this.add("North", jp_north);
		
		jp_center.setLayout(gBag);
		gbinsert(jbtn1,30,30,30,30);
		gbinsert(jbtn2,60,30,30,30);
//		gbinsert(jbtn_ac);
//		gbinsert(jbtn_back);
//		gbinsert(jbtn_num7);
//		gbinsert(jbtn_num8);
//		gbinsert(jbtn_num9);
//		gbinsert(jbtn_plus);
//		gbinsert(jbtn_num4);
//		gbinsert(jbtn_num5);
//		gbinsert(jbtn_num6);
//		gbinsert(jbtn_mius);
//		gbinsert(jbtn_num1);
//		gbinsert(jbtn_num2);
//		gbinsert(jbtn_num3);
//		gbinsert(jbtn_sub);
//		gbinsert(jbtn_num0);
//		gbinsert(jbtn_guam);
//		gbinsert(jbtn_elqaul);
//		gbinsert(jbtn_div);
		
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
	
	 public void gbinsert(Component c, int x, int y, int w, int h){
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill= GridBagConstraints.BOTH;
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = w;
	        gbc.gridheight = h;
	        gBag.setConstraints(c,gbc);
	        jp_center.add(c);
	 }
	 
	//메인메소드
	public static void main(String[] args) {
		new MyCalcViewThGB();
	}
}
