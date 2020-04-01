package thread.talk4_room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class MessageRoom extends JPanel implements ActionListener {
	TalkClientVer2 tc = null;
	//jp_first.add("Center", jta_display);
	//jp_first.add("South",jp_first_south);
	//JPanel은 원해(디폴트) FlowLayout이다.
	//그러나 센터에 jta_display붙여야 하고, 남쪽에 jp_first_south속지를 붙여야하니까
	//BorederLayout으로 변경하자
	JPanel jp_first 	  = new JPanel(); // <==> [HTML] - <Div id ="d_msg"> : id가 객체가 되고 d_msg가 주소번지가 된다.
	//남쪽에 들어갈 속지의 중앙에 jtf_msg를 배치하고 동쪽에 전송버튼을 배치하자.
	JPanel jp_first_south = new JPanel(); //BorderLayout적용
	JTextField jtf_msg    = new JTextField();
	JButton    jbtn_send  = new JButton("전송");
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext()); //스타일 적용하기
	JTextPane  jta_display	= new JTextPane(sd_display); //이모티콘 전송을 위해서 TextPhane을 사용하자.
	JScrollPane jsp_display = new JScrollPane(jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
														 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel jp_second	  = new JPanel(); // 중앙:jsp_nick(JscollPane), 남쪽:버튼 6개
	JPanel jp_second_south= new JPanel();
	//남쪽에 들어갈 버튼 6개 추가하기
	JButton jbtn_whisper  = new JButton("1:1");
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_fontColor= new JButton("글자색 변경");
	JButton jbtn_emoticon = new JButton("이모티콘");
	JButton jbtn_blank	  = new JButton("");
	JButton jbtn_exit	  = new JButton("나가기");
	
	String[] 		  cols 	   = {"대화명"};
	String[][] 		  data 	   = new String[0][1];
	DefaultTableModel dtm_nick = new DefaultTableModel(data, cols);
	JTable 			  jtb_nick = new JTable(dtm_nick);
	JScrollPane 	  jsp_nick = new JScrollPane(jtb_nick, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			 									   		 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public MessageRoom(TalkClientVer2 tc) {
		this.tc = tc;
		initDisplay();
	}
	
	public void initDisplay() {
		this.setLayout(new GridLayout(1,2));

		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center", jsp_nick);
		jp_second_south.setLayout(new GridLayout(3,2));
		jp_second_south.add(jbtn_whisper);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_fontColor);
		jp_second_south.add(jbtn_emoticon);
		jp_second_south.add(jbtn_blank);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South", jp_second_south);
		
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center", jsp_display);
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		jp_first.add("South",jp_first_south);

		this.setBackground(Color.blue); //배경색을 파랑으로 해줘.
		this.add(jp_first); //java라면 this = JFrame, <==> html라면 this = window [html]안에 윈도우라는 내장객체 존재
		this.add(jp_second);
		this.setSize(500,400); //가로 500, 세로 400만큼 그려줘
		this.setVisible(true); //화면에 보여줘
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
