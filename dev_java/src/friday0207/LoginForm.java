package friday0207;
//화면을 이용해서 리턴타입과 파라미터 복습하기

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.util.DBConnectionMgr;

public class LoginForm implements ActionListener{ //이벤트 처리를 지원해주는 인터페이스
	//선언부
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance(); //friendly 상태는 서로 다른 패키지는 접근 불가.
	JFrame jf_login 	= new JFrame();	// 창 생성
	JPanel jp_first 	= new JPanel(); // 속지 선언
	//jp_first속지안에 서쪽에 jlb_id, 중앙에 jtf_id
	JLabel jlb_id		= new JLabel(" 아  이  디 ");
	JTextField jtf_id	= new JTextField();
	JPanel jp_second 	= new JPanel();
	JLabel jlb_pw		= new JLabel(" 비 밀 번 호 ");
	JTextField jtf_pw	= new JTextField();
	JPanel jp_third 	= new JPanel();
	JButton jbtn_login	= new JButton("로 그 인");
	//생성자
	public LoginForm() {
		initDisplay();
	}
	//로그인 처리 메소드 구현
	public String[] login(String u_id, String u_pw) { //리턴타입이 String에서 String[]로 바꿈 와이? 리턴을 두개 리턴 시켜야됨
		String mem_name = null;
		String[] result = new String[2];
		String db_id = null; //디비에서 가져온 아이디이다.
		String status = null;
//		String sql = ""; //초기화를 null로 해주면 모르는 값에 모르는 값이라고 인지하고 에러  -- sb로 사용하자.!!
		StringBuilder sb = new StringBuilder("");
		StringBuilder sb2 = new StringBuilder(""); //아이디와 비밀번호가 같은 경우 를 비교
		try {//사원이 퇴사하거나(퇴사하면 제거해주어야됨) 인터넷의 문제 등 변경되는 경우
			sb.append("select                         "); //아이디가 존재하면 1, 존재하지 않으면 -1를 반환한다.
		    sb.append("      nvl((select 1 from member");          
		    sb.append("    		where mem_id = ?)     ");
		    sb.append("         ,-1) status           ");
		    sb.append(" from dual                     ");
			
//			sb.append("SELECT mem_name  "); // 쿼리문을 변경 아이디중복조회추가
//		    sb.append("  FROM member    ");
//		    sb.append(" WHERE mem_id =?"); 
//		    sb.append("  AND mem_pw =?");
		    con = dbMgr.getConnection();
		    pstmt = con.prepareStatement(sb.toString());
		    pstmt.setString(1, u_id);
//		    pstmt.setString(2, u_pw); // ?가 하나만 존재하게 되었으니 필요없게 됨
		    rs = pstmt.executeQuery();
		    if(rs.next()) {//조회결고가 있는 거야?
		    	status = rs.getString("status"); //status로 값을 받아옴 아이디가 있으면 1를 없으면 -1를 mem_name(status)에 반환.
		    }
		    System.out.println("mem_name:"+status); // 지금 아이디 판별 상태를 출력해보자.
		    if("1".equals(status)) {//아이디가 존재하니? id와 pw를 입력받아서 같다면 로그인 완료
		    	sb2.append ("select                                            ");
		        sb2.append ("		NVL((select mem_name                      "); //신뢰도를 위해서 디비에서 거내온 mem_id를 사용하자 다른 변수로 사용하기 보다..but nvl함수는 파라미터가 2개로 정해져 있기 떄문에 mem_name, mem_id 2개를 모두 정의할 수 없다.
		        sb2.append ("				from   member                     "); //nvl을 쓰지 않았다면 mem_name, mem_id까지 사용가능.. 
		        sb2.append ("			   where   mem_id=?                   ");
		        sb2.append ("				and     mem_pw=?),0) mem_name     ");
		        sb2.append ("  from dual                                       ");
		        
		        pstmt = null; //전에 쎃던 데이터를 모두 제거
		        pstmt = con.prepareStatement(sb2.toString());
		        pstmt.setString(1, u_id);
		        pstmt.setString(2, u_pw);
		        rs = null; //실무에서는 허락을 맡고 디비를 다시사용가능..? 정책.?
		        rs = pstmt.executeQuery();
		        if(rs.next()) {
		        	mem_name = rs.getString("mem_name"); //여기서 가져오는 값은 0or-1 rs.getString("mem_id")//db에서 값을 가져온다.
		        	db_id = u_id; //입력받은 아이디를 받는다. getSring(컬럼명)
		        	result[0] = mem_name; //첫번째 방에 이름을 담음. 리턴시킬 값을 배열에 담아서 리턴시킬예정임
		        	result[1] = db_id;	// 두번째 방에 사용자가 입력한 아이디
		        }
		        //아래에서 비밀번호가 맞지 않습니다. 메시지는 result배열에 담겨야 합니다.
		        //왜냐하면 리턴타입을 String에서 String[]로 바꾸었고 그 바뀐 정보를 if문에서 비교
		        //해야 하니까 더이상 mem_name에 값은 if문에서 역할이 없어진 것이죠.
		        //비밀번호가 맞지 않습니다.를 result[0]에 담아주어야 합니다.
		        if(result[0].equals("0")) {
		        	result[0] = "비밀번호가 맞지 않습니다.";
		        }
		    }else if("-1".equals(status)){//아이디가 없는건가?
		    	result[0] = "아이디가 존재하지 않습니다.";
		    }
		} catch (Exception e) {
			//에러를 해결하기 위해서는 최대한 많은 힌트를 끌어 모아야 합니다.
			//에러메세지를 JVM이 스텍영역에 관리하는데 그 이력과 라인번호를 같이 출력해줌.
			e.printStackTrace();// 디버깅을 위한 함수 기억
			System.out.println("sql"+sb.toString());//예외가 발생되면 [문제해결의 힌트]를 얻을 수 있어요. 
			System.out.println(e.toString());//예외가 발생되면 [문제해결의 힌트]를 얻을 수 있어요. 

		}
		return result;//id와 name 같이 넘기기

	}
	//화면처리부
	public void initDisplay() {
		//로그인 버튼을 눌렀을 때 이벤트 감지가 되면 actionperformed메소드를 호출하는데
		//이 메소드를 구현하는 클래스와 연결하기
		jbtn_login.addActionListener(this);
		jf_login.setLayout(new GridLayout(3,1));	//GridLayout을 사용해서 창 칸 나누기
		jp_first.setBackground(Color.green);	//속지 배경 지정하기
		jp_first.setLayout(new BorderLayout());
		jp_first.add("West",jlb_id);
		jp_first.add("Center",jtf_id);
		jp_second.setBackground(Color.yellow);
		jp_second.setLayout(new BorderLayout());
		jp_second.add("West",jlb_pw);
		jp_second.add("Center",jtf_pw);
		jp_third.setBackground(Color.red);
		jp_third.add(jbtn_login);
		jf_login.add(jp_first);		//창에다가 속지 붙히기
		jf_login.add(jp_second);
		jf_login.add(jp_third);
		jf_login.setSize(255,185);
		jf_login.setVisible(true);
	}
	
	//메인메소드
	public static void main(String[] args) {
		new LoginForm();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//로그인 버튼을 누른거야?
		Object obj = e.getSource(); //어떤 타입이 올 지 모르니 Object로 받음
		if(obj == jbtn_login) { //로그인 버튼이 눌리면
			String u_id = jtf_id.getText(); //텍스트 필드에 입력한 아이디
			String u_pw = jtf_pw.getText(); //텍스트 필드에 입력한 비번
			String result[] = login(u_id, u_pw); //
			System.out.println("mem_name:"+result[0]);
			if(result[0].equals("비밀번호가 맞지 않습니다.")||result[0].equals("아이디가 존재하지 않습니다.")) {
				JOptionPane.showMessageDialog(jf_login, result[0]);
			//if문에서 return을 만나면 그 메소드를 탈출 할 수 있다.
				return;
			}
				     //if(mem_name!=null && !mem_name.equals("0")) { // 값이 반환된 것임 로그인 성공하면..
			else {
				new BaseBallGameView(result); //게임창 열리고
				jf_login.dispose(); // 로그인창이 닫히고 
			}
		}
		
	}
}
