package thread.talk2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class TalkClient extends JFrame implements ActionListener{
	//////////////////////통신과 관련한 전역변수 추가 시작///////////////////
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null; //말 하고 싶을 때
	ObjectInputStream 	ois 	= null; //듣고 할 때
//	String 				nickName= null; //사용자의 닉네임 - LoginForm에서 닉네임을 생성자로 받아올것이니 없어도 된다.
	//////////////////////통신과 관련한 전역변수 추가 끝  ///////////////////
	JPanel 		jp_first 		= new JPanel();
	JPanel 		jp_first_south	= new JPanel();
	//배경이미지를 받아서 배경에 이미지 붙히기
	JTextArea 	jta_display 	= null;
	JScrollPane jsp_display 	= null;
	//배경이미지에 사용될 객체 선언 - JTextArea에 패인팅 하는 용도로 사용
	Image		back			= null;
	
	JTextField 	jtf_msg			= new JTextField(20); //south속지 center
	JButton 	jbtn_send 		= new JButton("전송"); //south속지 east

	JPanel 		jp_second		= new JPanel();
	JPanel 		jp_second_south = new JPanel();
	String 		cols[] 			= {"대화명"};
	String 		data[][] 		= new String[0][1];
	DefaultTableModel dtm 		= new DefaultTableModel(data,cols);
	JTable 		jtb 			= new JTable(dtm);
	JScrollPane jsp 			= new JScrollPane(jtb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JButton 	jbtn_one 		= new JButton("1:1");
	JButton 	jbtn_change 	= new JButton("대화명변경");
	JButton 	jbtn_font 		= new JButton("글자색");
	JButton 	jbtn_exit 		= new JButton("나가기");
	
	public String fontColor = null;
	LoginForm loginForm = null; //닉네임이 로그인폼에 있다. 그러니 전역변수로 선언하고 사용하자. 전역변수만이 인스턴스.해당자원 가능하다.
								//로그인폼의 원본을 사용해야하기 때문에 생성자로 주소를 넘겨받아서 사용하자.
	//생성자
	public TalkClient() {}
	//loginform이 가지고 있는 닉네임을 전달받기  위해서 생성자로 loginform의 주소를 넘겨받아서 원본의 loginform의 주소를 사용하자.
	
	public TalkClient(LoginForm loginForm) {
		this.loginForm = loginForm;
		jbtn_one.addActionListener(this);
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_font.addActionListener(this);
		initDisplay();
		init();
	}

	//화면
	public void initDisplay() {
		//사용자의 닉네임 받기
//		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요"); 로그인 화면에서 닉네임 받아오기
		this.setLayout(new GridLayout(1,2));
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		//글을 쓸 때 옆으로 넘어가는 것을 없앤다.
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		
		back = getToolkit().getImage("src\\thread\\img\\back.png");
		
		jta_display = new JTextArea() {
			private static final long serialVersionUID = 1L; //시리얼 아이디 값을 주면 경고가 사라진다.
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);// this : 관찰자는 jframe
				Point p = jsp_display.getViewport().getViewPosition(); //화면이 글씨를 작성하면 밀리지않게 하자.
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);//투명도. 뒤에있는 배경이미지를 투명하게 만들지 말자 - 배경이미지를 보고 싶다.
		Font font = new Font("돋음", Font.BOLD,25);
		jta_display.setFont(font);
		
		jsp_display = new JScrollPane(jta_display);
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(loginForm.nickName+"의 대화창"); //생성자로 loginform의 주소를 넘겨받아서 전역변수로 초기화를 하였기 때문에 닉네임을 사용이가능하다.
		this.setSize(800, 550);
		this.setVisible(true);
	}
	//메인메소드 - TalkClient 안에서가 아닌 LoginForm에서 인스턴스화할것이다. 생성자에서 함수를 호출하자.
//	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		TalkClient tc = new TalkClient();
//		tc.initDisplay();
//		tc.init();
//	}

	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.15",3000);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 후 init()가 호출 되므로
			//서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(Protocol.LOGIN+"#"+loginForm.nickName);
			//서버에 말을 한 후 들을 준비를 한다.
			TalkClientThread tct = new TalkClientThread(this); //말하기는 talkclient가 하고 듣기는 talkclientthread에서 진행하니 this 말하는 클래스의 원본 주소를 넘기자.
			tct.start();
		} catch (Exception e) {
			//예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString()); //직접적인 Exception이름을 확인
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();
		if(jbtn_font == obj) {
			JDialog jdl_color = new JDialog();
			jdl_color.setSize(600, 500);
			JColorChooser jcc = new JColorChooser(); //색갈을 선택할 수 있음.
			ColorSelectionModel csm = jcc.getSelectionModel();
			ChangeListener cl = new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					Color color = jcc.getColor();
					fontColor = String.valueOf(color.getRGB());
					jta_display.setForeground(new Color(Integer.parseInt(fontColor)));
				}
			};
			csm.addChangeListener(cl);
//			jta_display.setForeground(fontColor); 색갈타입의 매개변수만 받아요 String 타입의 fontColor을 가공해서 넣어봅시다.
			jdl_color.add(jcc);
			jdl_color.setVisible(true);
		}
		if(jbtn_one == obj) {
			int row = jtb.getSelectedRow();//테이블에서 상대를 선택하자.
			if(row == -1) { //-1 end of file : 더이상 찾았는데 없다.
				JOptionPane.showMessageDialog(this, "상대를 선택하세요.");
				return; //actionperfomed를 탈출 : 더이상진행x
			} else {//상대가 다른 사람이 아닌 나 자신일 때는 배제한다.
				//				┌>dtm의 리턴타입이 object이니 string으로 변경
				String name = (String)dtm.getValueAt(row, 0); //테이블의 컬럼이 하나이기 떄문에 모든 열이 0일것이다.
				if(loginForm.nickName.equals(name)) {
					JOptionPane.showMessageDialog(this, "자기자신을 선택했어요. 다른 사람을 선택해주세요.");
					return;//자기자신을 선택한 경우는 진행되면 안되니 빠져나가자.
				}
				//메세지 입력받기
				String msg1 = JOptionPane.showInputDialog(name+"님에게 보낼 메세지를 입력하세요.");
				try {
					oos.writeObject(200+"#"+loginForm.nickName+"#"+name+"#"+msg1); //나의 닉네임이 상대의 네임에게 메세지를 보낸다.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}////////////////////////////end of else
			//선택한 대화 상대 초기화
			jtb.clearSelection();
		}////////////////////////////////end of if
		else if (jtf_msg==obj) {
			try {
				oos.writeObject(201+"#"+loginForm.nickName+"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		else if(jbtn_exit == obj) {
			try {//						┌>나 자신의 닉네임을 말하자.
				oos.writeObject(500+"#"+this.loginForm.nickName);
				//자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		else if(jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요");
			if(afterName == null || afterName.trim().length()<1) {//        아이콘 이미지를 무엇으로 가져갈 것이냐. INFO면 에러메세지?
				JOptionPane.showMessageDialog(this, "변경할 대화명을 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				//				 ┌>구분하겠다.
				oos.writeObject(202+"#"+loginForm.nickName+"#"+afterName+"#"+loginForm.nickName+"의 대화명이"+afterName+"으로 변경되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
