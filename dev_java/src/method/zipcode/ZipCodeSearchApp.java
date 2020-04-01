package method.zipcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;

import oracle.jdbc2.JDBCTest;
import oracle.jdbc2.ZipCodeVO;

public class ZipCodeSearchApp implements ActionListener//Lisnetener가 붙는것은 이벤트를 불러올때 , FocusListener는 텍스트 필드에 마우스를 크릭하는 순간 문구가 사라짐
{ 
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
//	DBConnectionMgr dbMgr2 = DBConnectionMgr; 싱글톤이라서 [불가능]
	String zdos[] = null;
	JComboBox jcb_zdo = null;
	//이벤트와 관련된 일을 하는 인터페이스. 이 인터페이스가 어떤 메소드를 가지고 있고  이 메소드가 이벤트를 처리함.
	//인터페이스는 무조건 재정의해야됨. 오버라이딩 무조건 해야됨.!!
	
	//선언부 - 전역변수는 초기화를 생성자가 해준다. 즉, 초기화을 안해도 된다.
	Connection 			con 	= null;//전역변수 선언하기 - 클래스 전역(ZipCodeSearchApp)에서 사용가능하다.
	//오라클 서버에 쿼리문을 전달하고 너가 좀 처리해 줄래?
	PreparedStatement 	pstmt 	= null;
	//오라클에는 일꾼이 살고 있는데 이름은 옵티마이저라고 함. 데이터를 찾아주는 역할 함. (=JVM 같은 개념)
	//데이터를 찾을 떄는 커서를 움직이면서 조회결과가 존재하는지 확인하고 그 로우에 있는 값들을
	//RAM메모리 영역에 올린다. 커서를 조작하면서 해당 로우에 있는 값을 꺼낼 수 있다.
	ResultSet		rs			= null;
//	 															ㄴ 디비연동에 필요
/////////////////////////////////////////////////////////////////ㄱ 화면에 필요
	JTextField		jtf_dong	= new JTextField();
	JButton			jbtn_search	= new JButton("조회");
	//오라클에서 조회한 결과를 담을 클래스 선언 및 생성하기
	//테이블의 헤더 설정하기
	String			cols[]		= {"주소","우편번호"};
	String			data[][]	= new String[0][2]; //
	//생성자에는 파라미터를 받을 수 있다.
	//첫번째 파라미터는 데이터 영역을 표시하는 주소번지
	//두번째 파라미터는 테이블 헤더영역에 해당하는 주소번지
	//파라미터의 갯수에 따라서 서로 다른 생성자를 선언하는 것도 가능하다는 것인가?
	DefaultTableModel dtm_zip	= new DefaultTableModel(data, cols);
	//테이블 양식 그려줌.
	JTable			jt_zip		= new JTable(dtm_zip);
	JScrollPane		jsp_zip		= new JScrollPane(jt_zip);
	//JTableHeader 색상, 글꼴, 간격 등 조절가능
	JTableHeader	jth_zip		= new JTableHeader();
	JFrame			jf_zip		= new JFrame(); // 운영체제 위에 창을 띄워주는 클래스
	JPanel			jp_north 	= new JPanel(); // 속지를 만들어주는 클래스 ex.하얀 도화지
	
	//생성자 - 리턴타입이 없다. 클래스이름과 동일하다. ※리턴타입이 있다면 메소드이다.
	public ZipCodeSearchApp() { //default 생성자
		//인스턴스화를 할때마다 생성자도 같은 횟수 만큼 호출이 일어난다.
		//new A()같이 했을때 객체가 RAM에 로딩(상주:기억)되면서 동시에 생성자가 호출함.
		System.out.println("나는 파라미터가 없는 디폴트 생성자라고 해.");
		System.out.println("나는 인스턴스화 하는 순간 자동으로 호출되는 거야.");//JVM이 자동으로 실행해줌.
	}
	//생성자 - 매개변수 2개 
	public ZipCodeSearchApp(String str, int i) {
	}
	

	//물리적으로 떨어져 있는 오라클 서버와 연결통로
	public Connection getConnection() {
		System.out.println("getConnention호출 성공");
		//오라클 회사정보를 수정함.
		try {
			Class.forName(JDBCTest._DRIVER); //오라클 제조사 제품정보 드라이버 이름이 런타임오류가 날수있으니 예외처리 해주어야됨.
			con = DriverManager.getConnection(JDBCTest._URL, JDBCTest._USER, JDBCTest._PW);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 이름을 찾을 수 없어요"); //정상적으로 진행이 안되면 잡음 잡힌다면 출력
		} catch (Exception e) {
			System.out.println("예외가 발생했음. 정상적으로 처리가 안됨.");
		}
		return con;
	}
	
	
	//새로고침 기능을 구현해 보자 - SELECT
	public void refreshData(String myDong) {
		getConnection();
		con = dbMgr.getConnection();
		System.out.println("refreshData호출 성공"+myDong);
		String sql = "";
		sql+="SELECT address, zipcode";
	    sql+="  FROM zipcode_t";
	    if(myDong!=null || myDong.length()>0) {
	    	sql+=" WHERE dong LIKE '%'||?||'%'"; //'?'는 안됨
	    }
	    try {
	    	pstmt = con.prepareStatement(sql); //쿼리문 처리
	    	pstmt.setString(1, myDong);//?들어갈 동이름이 결정됨.
	    	rs = pstmt.executeQuery();///오라클 서버에게 처리를 요청함
	    	Vector<ZipCodeVO> v = new Vector<>();
	    	ZipCodeVO zcVOS[] = null;
	    	ZipCodeVO zcVO = null;
	    	while(rs.next()) {//커서 이동 1번, 커서이동 3번 데이터를 가지고 있을때 인스턴스화 하기 
//			System.out.println("while문 : "+rs.next()); //커서이동 2번, rs.next():현재 커서가 위치하는 곳에 데이터가 있습니까? 네true 아니오false
	    		zcVO = new ZipCodeVO();
	    		zcVO.setAddress(rs.getString("address"));
//			zcVO.setAddress(rs.getString(1)); 1이 address을 의미하지만 직관적이지 않으므로 이렇게 쓰지 않는다.
	    		zcVO.setZipcode(rs.getInt("zipcode"));
	    		v.add(zcVO);
	    	}
	    	zcVOS = new ZipCodeVO[v.size()];
	    	v.copyInto(zcVOS);//벡터 자료구조에 들어있는 정보를 복사하기 
			System.out.println("v.size():"+v.size()+", "+zcVOS.length);
			if(v.size()>0) {//조회된 결과가 있늬?
				//조회된 결과가 있다면 데이터를 DefaultTableModel에 담아주어야합니다.
				//그래야 JTable에서 그리드에 출력된 결과를 볼 수 있기 때문입니다.
				//그런데 컬럼을 하나씩 각각 개발자가 일일이 초기화 해주는 건 너무 불편합니다.
				for (int x = 0; x < v.size(); x++) {
				//그래서 for문 안에서 벡터를 하나 더 생성했어요.
				//addRow라는 메소드가 있는데 이 파라미터에 Vector를 넣으면 한개로우씩
				//추가 해준다고 합니다.
					Vector oneRow = new Vector();
					oneRow.add(0, zcVOS[x].getAddress());
					oneRow.add(1, zcVOS[x].getZipcode());
					
					dtm_zip.addRow(oneRow);
				}
			}
		} catch (SQLException se) {
			//테이블이 존재하지 않습니다. -테이블을 만들지 않았네
			//혹은 부적합한 식별자 - 컬럼명이 맞지 않습니다.
			System.out.println("[[query]]"+sql);
		} catch(Exception e) {//그 밖에 문제가 발생할 경우 잡아준다.
			System.out.println("[[Exception]]"+e);
		}
	}
	
	//화면 그리기 
	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
		
		//테이블 헤더 영역의 배경색 바꿔볼까?
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(new Color(22,22,100)); //헤더 배경색 변경
		jth_zip.setForeground(Color.white); //헤더 글자색 변경
		jth_zip.setFont(new Font("맑은 고딕",Font.BOLD,20)); //글씨 설정을 바꾸는 것 맑은고딕체, 굵은체, 20크기 설정
		jt_zip.setGridColor(Color.blue); //틀 색상 변경 그리드 색상
		//그리드의 높이 변경하기
		jt_zip.setRowHeight(20);
		//컬럼의 넓이 조정하기
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(350); 
		//선택한 로우의 배경색이나 글자색 변경하기
		jt_zip.setSelectionBackground(new Color(186,186,241));
		jt_zip.setSelectionForeground(new Color(186,186,241));
		//이벤트가 일어난 소스와 이벤트를 처리하는 클래스(actionPefromed메소드)를 연결해준다. this 
		//jp_north속지에는 중앙에 jtf_dong을 붙히고 동쪽에는 jbtn_search를 붙힌다.
		//이렇게 동,서,남,북 중앙에 버튼을 배치하고 싶으면 BorderLayout사용함.
		jp_north.setLayout(new BorderLayout()); //북쪽 속지를 북쪽에다가 붙히고 자름 / 센터 동 서 남 북 을 배치
		jp_north.add("Center",jtf_dong); //센터에다가 동 검색창을 붙힘
		jp_north.add("East",jbtn_search);//동쪽에 조회라는 버튼을 붙힘.
		jp_north.add(jtf_dong); //센터 등.. 위치 선정 필요없음.
		jp_north.add(jbtn_search);
		jp_north.setBackground(Color.red); //북쪽 속지 배경색 설정. %%%%%%%%%%%%%%%%%%%%%%%%적용이 안됨...
		jbtn_search.addActionListener(this);
		jtf_dong.addActionListener(this);
		jf_zip.setTitle("우편번호 검색"); //Frame이라는 클래스를 상속받음 즉, 자식클래스가 되는 JFrame은 따로 메소드를 정의하지 않아도 가져다 쓰기 가능.
		//JFrame판넬 위에 북쪽에 jp_north속지를 붙이자.
		//속지 안에 버튼과 텍스트 필드가 붙어 있으니까 같이 따라온다.
		jf_zip.add("North",jp_north); //북쪽에다가 jp_north를 붙힘
		jf_zip.add("Center",jsp_zip);//양식을 붙힘. JFrame Scrollpane을 붙히면 그 위에 붙어있는거 다 붙혀짐
		jf_zip.setSize(600, 500); // 화면의 보여줄 창의 크기 설정
		jf_zip.setVisible(true); //화면에 보여준다.
	}

	//메인메소드
	public static void main(String[] args) throws Exception{
		ZipCodeSearchApp zipApp = new ZipCodeSearchApp();//생성하는 순간 생성자 실행.
		zipApp.initDisplay();
	}////////end of main
	
	//implements ActionListener 인터페이스를 사용하므로서 재정의.
	//오버로딩 - 함수이름은 같고 매개변수의 갯수가 다름, 오버라이딩 - 함수의 기능을 재정의, 함수의 값을 재정의
	//
	@Override
	public void actionPerformed(ActionEvent ae) { //이벤트 수행할 때는 Event 붙음.
		//이벤트가 감지된 버튼의 주소번지를 읽어오는 메소드임.
		Object obj = ae.getSource();
		if((obj == jbtn_search)||(obj == jtf_dong)) {
			String myDong = jtf_dong.getText();
			//java에서는 같은 이름의 메소드를 재정의할 수 있다.
			//단, 파라미터의 갯수가 다르거나 파라미터 타입이 반드시 달라야 한다.
			refreshData(myDong);
		}
		
		/*if(obj == jbtn_search) { //너 조회버튼 누른거니? 너 우편번호 알고싶어? 일을 할때마다 다른 결과를 나오게 하기위해서는 주소번지를 구별해야됨. ex. 계산기 버튼.
			//조회를 눌렀을때 값을 보낼것인지 아닌지 결정 눌렀을때 오라클로 데이터를 보내기 위해서 이벤트 감지.
			//처리 내용이 달라지는 거다.
			System.out.println("조회 버튼 클릭 한거야?"+jtf_dong.getText()); //이벤트 감지되었음을 확인. 조회 버튼을 누르는 순간 실행.
			refreshData();
		}else if(obj == jtf_dong){
			System.out.println("엔터 친거야?"+jtf_dong.getText());
			refreshData();
		}*/
	}
}/////////end of ZipCodeSearchApp
