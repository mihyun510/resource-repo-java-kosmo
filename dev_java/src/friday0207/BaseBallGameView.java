package friday0207;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseBallGameView extends JFrame{ //JFrame을 상속받음 즉 재상용하였으로 코드의 량이  줄었다.
		//화면과 관련된 코드 추가
//		JFrame 	 jf_bbgame 	= new JFrame(); //창을 띄워주는 역할 JFrame // JFrame을 상속받아서 더이상 필요x
		//JMenuBar는 JFrame안에 메뉴바를 추가하기
		JMenuBar jmb_bbgame = new JMenuBar();
		//JMenu는 JMenuBar안에 들어갈 메뉴 추가하기
		JMenu 	 jm_game 	= new JMenu("게임");
		//JMenuItem은 JMenu아래에 들어갈 메뉴내용들...
		JMenuItem jmi_next  = new JMenuItem("다음겜"); //게임 메뉴바 안에 메뉴 삽입
		JMenuItem jmi_clear = new JMenuItem("지우기");
		JMenuItem jmi_dap   = new JMenuItem("정답");
		JMenuItem jmi_oracle   = new JMenuItem("오라클테스트");
		JMenuItem jmi_exit  = new JMenuItem("나가기");
		JMenu	  jm_info 	= new JMenu("도움말");
		JTextArea jta_display   = new JTextArea("");
		JScrollPane jsp_display = new JScrollPane(jta_display);//스크롤이 젤 아래
		JTextField  jtf_input 	= new JTextField(); //검색창(입력창)
		JButton		jbtn_next 	= new JButton("다음겜"); //동쪽에 버튼4개
		JButton		jbtn_clear 	= new JButton("지우기");
		JButton		jbtn_dap 	= new JButton("정답");
		JButton		jbtn_exit 	= new JButton("나가기");
		//JTextArea와 JTextfiled를 붙힐 속지 추가하기
		JPanel 		jp_center 	= new JPanel();
		//버튼 4개를 붙힐 속지 추가하기
		JPanel 		jp_east 	= new JPanel();
		
		String[] result = new String[2];
		BaseBallGameLogic bbLogic = new BaseBallGameLogic();
		String mem_name = null;
		//전역변수를 선언하여 문제를 해결할 수 있어요.
		//생성자의 파라미터로 배열의 주소번지를 받게 되는데 이 값을 사용하는 곳이
		//생성자의 안에서가 아니라 initDisplay메소드 안에 setTiltle메소드에서
		//사용해야 하기 때문에 파라미터로 넘어온 값을 반드시 전변과 초기화 해야 합니다.
		//파라미터 자리는 변수를 선언하는 자리입니다.
		//초기화는 일어나지 않지요. - 생성하는 자리가 아닙니다. String result[] 이렇게만 있으면 선언만 한것임 추가로 매개변수를 받으면 result = new String[2]을 해줌으로써 초기화를 진행해야지 null값을 받아오지 않음.
		public BaseBallGameView(String[] result) {
			this.result = result; //this.result : 멤버변수(전역변수) , result : 파라미터(매개변수)
			if(this.result == null) {//여기서의 result는 전역변수이다.
				this.result = new String[2]; //전역변수를 생성해줌. this을 붙히지 않고 result만 선언했을 경우 파라미터로 JVM이 착가한다. 전역변수 선언해주고 싶다면 this로 붙혀서 전역변수임을 구별한다. 생성을 미리해준후 다음줄에서 초기화.
				this.result[0] = ""; //전역변수를 초기화해줌. result값이 null이면 initdisplay가 실행되지 않는다 그러니 if문을 사용하여 초기화를 해주면서 null값을 채워준다.
				this.result[1] = "";
			}
			System.out.println("로그인 정보"+this.result[0]+","+this.result[1]);
			//생성자 안에서 메소드를 호출하면 인스턴스화 없이도 호출이 간능하다.
				if(this.result[0]!=null) { //null이 아니기 위해서는 초기화가 진행이 되어야한다.
					initDisplay();
					bbLogic.ranCom();
				}
		}
		///////////////////////////////화면 처리 시작//////////////////////////////////////
		public void initDisplay() {
			//이벤트 소스와 이벤트 처리 클래스 매핑
	/* 이전 버전에서는 friday0131소스코드에서는 이벤트처리를 직접하였다. -ActionListenner했다.
	 * 	
	 */		//디폴트 생성자는 JVM이 만들어 줄 수 있어요. 왜냐하면 파라미터가 없기때문이죠.
			//파라미터가 있는 생성자는 내가 대신해 줄 수 없다. 왜냐면 네 생각을 난 알 수 없으니까....
			BaseBallGameEvent bbEvent = new BaseBallGameEvent(this); //this는 View의 주소.. 인스턴스화를 하면 생성자가 호출된다고 하였다. 그리고 여러개 선언이 되었다. String name을 썻었다. 생성자도 파라미터를 가질수 있어다. 
			jtf_input.addActionListener(bbEvent); //입력창에 값을 입력하고 엔터치면 텍스트창으로 올라가자 이벤트를 실행시키위한 BaseBallGameEvent타입의 bbEvent 넘겨줌
			jbtn_dap.addActionListener(bbEvent); //정답 버튼의 이벤트 처리함
			jbtn_next.addActionListener(bbEvent); //버튼을 누르면 기능이 발생하자 이벤트를 실행시키위한 BaseBallGameEvent타입의 bbEvent 넘겨줌
			jbtn_clear.addActionListener(bbEvent); //버튼을 누르면 기능이 발생하자 이벤트를 실행시키위한 BaseBallGameEvent타입의 bbEvent 넘겨줌
			jbtn_exit.addActionListener(bbEvent);//다른 쪽에서도 BaseBallGameEvent 가 필요한가..? 아니면 지역에 인스턴스 맞으면 전역에 인스턴스
			jmi_exit.addActionListener(bbEvent);
			jmi_oracle.addActionListener(bbEvent); //게임 메뉴안에 오라클 테스트 이벤트처리하는 클래스 처리하기 위한선언
//			System.out.println("화면 처리 시작");
			jp_center.setLayout(new BorderLayout()); //display부분과 버튼 부분의 속지를 나눔
			jp_center.add("Center", jsp_display);
			jp_center.add("South", jtf_input);
			jp_east.setLayout(new GridLayout(4,1));
			jbtn_next.setBackground(new Color(158,9,9)); //버튼 다음겜 - 배경색 삽입
			jbtn_next.setForeground(new Color(212,212,212));
			jbtn_clear.setBackground(new Color(7,84,170)); //버튼 지우기 - 배경색 삽입
			jbtn_clear.setForeground(new Color(212,212,212));
			jbtn_dap.setBackground(new Color(19,99,57)); //버튼 정답 - 배경색 삽입
			jbtn_dap.setForeground(new Color(212,212,212));
			jbtn_exit.setBackground(new Color(54,54,54)); //버튼 종료 - 배경색 삽입
			jbtn_exit.setForeground(new Color(212,212,212));
			jp_east.add(jbtn_next);
			jp_east.add(jbtn_clear);
			jp_east.add(jbtn_dap);
			jp_east.add(jbtn_exit);
			
			//jf_bbgame. 삭제가능
			add("Center",jp_center);
			add("East",jp_east);
			//////////////////메뉴바 추가 시작////////////////////
			jm_game.add(jmi_next);
			jm_game.add(jmi_clear);
			jm_game.add(jmi_dap);
			jm_game.add(jmi_oracle);     //jm_game 게임메뉴안에 메뉴아이템 추가
			jm_game.add(jmi_exit);
			//메뉴를 메뉴바에 붙여요
			jmb_bbgame.add(jm_game);
			jmb_bbgame.add(jm_info);
			
			//jf_bbgame. 삭제가능
			setJMenuBar(jmb_bbgame);
			//////////////////메뉴바 추가 끝/////////////////////
			setTitle("BaseBall NumberGame "+result[0]+"(id: "+result[1]+")");
			setSize(500,200);
			setVisible(true);
		}
		///////////////////////////////화면 처리 시작//////////////////////////////////////
		
	public static void main(String[] args) {
		new BaseBallGameView(null);
	}
}

//정답 버튼을 구현해 보세여

