package view.q1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class CalView extends JFrame {
	
	//선언부
	ImageIcon bicon = new ImageIcon("img\\cal.png");
	
	JPanel jp_north = new JPanel();
	JMenuBar jmb_menu = new JMenuBar();
	JMenu jm_menu1 = new JMenu("보기(V)");
	JMenu jm_menu2 = new JMenu("편집(E)");
	JMenu jm_menu3 = new JMenu("도움말(H)");
	
	JTextField jtf_cal = new JTextField("0"); 
	
	JPanel jp_center = new JPanel();
	JButton jbtn_mc = new JButton("MC");
	JButton jbtn_mr = new JButton("MR");
	JButton jbtn_ms = new JButton("MS");
	JButton jbtn_mPlus = new JButton("M+");
	JButton jbtn_mMius = new JButton("M-");
	JButton jbtn_back = new JButton("←");
	JButton jbtn_ce = new JButton("CE");
	JButton jbtn_c = new JButton("C");
	JButton jbtn_pm = new JButton("±");
	JButton jbtn_root = new JButton("√");
	JButton jbtn_num7 = new JButton("7");
	JButton jbtn_num8 = new JButton("8");
	JButton jbtn_num9 = new JButton("9");
	JButton jbtn_div = new JButton("/");
	JButton jbtn_nam = new JButton("%");
	JButton jbtn_num4 = new JButton("4");
	JButton jbtn_num5 = new JButton("5");
	JButton jbtn_num6 = new JButton("6");
	JButton jbtn_sub = new JButton("*");
	JButton jbtn_x = new JButton("1/χ");
	
	JPanel jp_center_two = new JPanel();
	JButton jbtn_num1 = new JButton("1");
	JButton jbtn_num2 = new JButton("2");
	JButton jbtn_num3 = new JButton("3");
	JButton jbtn_mius = new JButton("-");
	
	JButton jbtn_num0 = new JButton("0");
	JButton jbtn_juam = new JButton(".");
	JButton jbtn_plus = new JButton("+");
	JButton jbtn_elqaul = new JButton("=");
	
	//생성자
	public CalView() {
		initDisplat();
	}
	//화면설계부
	public void initDisplat() {
		this.setLayout(null);
		jp_north.setLayout(new BorderLayout());
		jm_menu1.setMnemonic('V'); 
		jm_menu2.setMnemonic('E');
		jm_menu3.setMnemonic('H');
		jmb_menu.add(jm_menu1);
		jmb_menu.add(jm_menu2);
		jmb_menu.add(jm_menu3);
		jp_north.add(jmb_menu);
		jp_north.setBounds(0, 0, 350, 30);
		this.add(jp_north);
		
		jtf_cal.setHorizontalAlignment(JTextField.RIGHT);
		jtf_cal.setEditable(false); 
		jtf_cal.setBounds(23, 35, 300, 50);
		Font font = new Font("돋음체", Font.BOLD, 22);
		jtf_cal.setFont(font);
		this.add(jtf_cal);
		
		jp_center.setLayout(new GridLayout(4,5,3,3));
		jp_center.add(jbtn_mc);
		jp_center.add(jbtn_mr);
		jp_center.add(jbtn_ms);
		jp_center.add(jbtn_mPlus);
		jp_center.add(jbtn_mMius);
		jp_center.add(jbtn_back);
		jp_center.add(jbtn_ce);
		jp_center.add(jbtn_c);
		jp_center.add(jbtn_pm);
		jp_center.add(jbtn_root);
		jp_center.add(jbtn_num7);
		jp_center.add(jbtn_num8);
		jp_center.add(jbtn_num9);
		jp_center.add(jbtn_div);
		jp_center.add(jbtn_nam);
		jp_center.add(jbtn_num4);
		jp_center.add(jbtn_num5);
		jp_center.add(jbtn_num6);
		jp_center.add(jbtn_sub);
		jp_center.add(jbtn_x);
		jp_center.setBounds(23, 90, 300, 180);
		this.add(jp_center);
		
		jp_center_two.setLayout(new GridLayout(1,4,3,3));
		jp_center_two.add(jbtn_num1);
		jp_center_two.add(jbtn_num2);
		jp_center_two.add(jbtn_num3);
		jp_center_two.add(jbtn_mius);
		jp_center_two.setBounds(23, 272, 240, 44);
		this.add(jp_center_two);
		
		jbtn_num0.setBounds(24, 320, 117, 40);
		this.add(jbtn_num0);
		
		jbtn_juam.setBounds(144, 320, 57, 40);
		this.add(jbtn_juam);
		
		jbtn_plus.setBounds(204, 320, 57, 40);
		this.add(jbtn_plus);
		
		jbtn_elqaul.setBounds(264, 272, 57, 88);
		this.add(jbtn_elqaul);
		this.setIconImage(bicon.getImage());
		this.setSize(350,400);
		this.setTitle("계산기");
		this.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new CalView();
	}
}
