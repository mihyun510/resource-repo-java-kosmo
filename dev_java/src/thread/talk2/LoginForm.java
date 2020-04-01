package thread.talk2;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
	String 		imgPath = "src\\thread\\img\\";
	ImageIcon 	ig 		= new ImageIcon(imgPath+"main.png");
	JLabel 		jlb_id 	= new JLabel("아이디");
	JTextField 	jtf_id 	= new JTextField("test");
	JLabel 		jlb_pw 	= new JLabel("비밀번호");
	JPasswordField 	jtf_pw 	= new JPasswordField("123"); //눈에 보이지 않는 텍스트 필드
	Font 		font 		= new Font("휴먼매직체",Font.BOLD,17); //<->구글에서 할경우는 포트가 제공되요.
	JButton		jbtn_join 	= 
				new JButton(new ImageIcon(imgPath+"confirm.png")); //이미지 버튼 아이콘 만들기
	JButton		jbtn_login	= new JButton(new ImageIcon(imgPath+"login.png"));
	
	TalkDao tDao = new TalkDao();
	
	String nickName = null; //닉네임을 여기서 받고 talkClient로 넘기자.
	public LoginForm() {

	}
	
	//내부 클래스로 배경 이미지 처리
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			//이미지를 그려주는 메소드
			//			┌> 이미지 객체를 가져오자.  ┌>상위 클래스
			g.drawImage(ig.getImage(), 0, 0, null);
			//							└>시작점
			//배경을 투명하게 만들지 말자
			setOpaque(false);
			super.paintComponent(g);
		}
	}
	public void initDisplay() {
		//처음 컨포넌트을 설정해서 생성할때 클래스로 생성
		jbtn_login.addActionListener(this);
		this.setContentPane(new MyPanel());
		this.setLayout(null);//디폴트 - BorderLayout
		jlb_id.setBounds(45, 200, 80, 40);
		jlb_id.setFont(font);
		jtf_id.setBounds(110, 200, 185, 40);
		this.add(jlb_id);
		this.add(jtf_id);
		
		jlb_pw.setBounds(45, 240, 80, 40);
		jlb_pw.setFont(font);
		jtf_pw.setBounds(110, 240, 185, 40);
		this.add(jlb_pw);
		this.add(jtf_pw);
		
		jbtn_login.setBounds(175, 285, 120, 40);
		this.add(jbtn_login);
		
		jbtn_join.setBounds(45, 285, 120, 40);
		this.add(jbtn_join);
		
		this.setTitle("자바채팅 Ver2.0");
		this.setLocation(500, 100);
		this.setSize(350, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		LoginForm login = new LoginForm();
		login.initDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(jbtn_login == obj) {
			if("".equals(jtf_id.getText())||"".equals(jtf_pw.getText())) {
				JOptionPane.showMessageDialog(this, "아이디와 비번을 확인하세요.");
				return; // 더 이상 진행할 필요없다.
			}
		}
		try {
			String mem_id = jtf_id.getText();
			String mem_pw = jtf_pw.getText();
			int result = 2;
//			result = Integer.valueOf(tDao.login(mem_id, mem_pw));
			String mem_name = tDao.login(mem_id, mem_pw);
			System.out.println("mem_name:"+mem_name);
			if("아이디가 존재하지 않습니다.".equals(mem_name)) {
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다."); //"아이디가 존재하지 않습니다."
				jtf_pw.setText("");
				return;
			}
			else if("비밀번호가 맞지 않습니다.".equals(mem_name)) {//비밀번호가 틀립니다도 나올수 있음.
				JOptionPane.showMessageDialog(this, "비밀번호가 맞지 않습니다.");//"비밀번호가 맞지 않습니다."
				jtf_pw.setText("");
				return;
			} else {
				nickName = mem_name; //dao에서 전달받은 이름을 닉네임에 넣어주므로써 초기화해주자.
				//로그인 화면은비활성화시킴
				this.setVisible(false); //로그인이 성공했다면 화면을 닫자.
				jtf_id.setText(""); //아이디 초기화
				jtf_pw.setText(""); //비밀번호 초기화
				TalkClient ts = new TalkClient(this); //생성자 호출
			}
		} catch (Exception e2) {
			System.out.println("Exception"+e2.toString());
			e2.printStackTrace();
		}
		
	}
}
