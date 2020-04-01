package method.temperatrue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;
import oracle.jdbc2.ZipCodeVO;

//화면 부분만 처리한다.
public class SeoulTempView implements ActionListener { //이벤트를 처리하기 위한 인터페이스 
	//선언부
	JTextField		jtf_date	= new JTextField("날짜를 입력하세요.");	 //동이 아닌 날짜를 입력받을 예정임, 검색창 설계
	JButton			jbtn_search	= new JButton("조회");
	//오라클에서 조회한 결과를 담을 클래스 선언 및 생성하기
	//테이블의 헤더 설정하기
	String			cols[]		= {"날짜","최저온도","최고온도"};          //테이블의 헤더, 시트의 열
	String			data[][]	= new String[4][3]; 				  //[행 ,열] 열이 3개로 설정(날짜 | 최저온도| 최고온도)
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
	
	static DBConnectionMgr conMgr = DBConnectionMgr.getInstance();
	//생성자
	public SeoulTempView() {
		//생성자에서 메소드 호출 할 수 있다.
		initDisplay();
	}
	
	//화면처리부
	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
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
		jp_north.setLayout(new BorderLayout()); //북쪽 속지를 북쪽에다가 붙히고 자름
		jp_north.setBackground(Color.red); //북쪽 속지 배경색 설정. %%%%%%%%%%%%%%%%%%%%%%%%적용이 안됨...
		jp_north.add("Center",jtf_date); //센터에다가 동 검색창을 붙힘
		jp_north.add("East",jbtn_search);//동쪽에 조회라는 버튼을 붙힘.
		jbtn_search.addActionListener(this);
		jtf_date.addActionListener(this);
		jf_zip.setTitle("서울기후통계 검색"); //Frame이라는 클래스를 상속받음 즉, 자식클래스가 되는 JFrame은 따로 메소드를 정의하지 않아도 가져다 쓰기 가능.
		//JFrame판넬 위에 북쪽에 jp_north속지를 붙이자.
		//속지 안에 버튼과 텍스트 필드가 붙어 있으니까 같이 따라온다.
		jf_zip.add("North",jp_north); //북쪽에다가 jp_north를 붙힘
		jf_zip.add("Center",jsp_zip);//양식을 붙힘. JFrame Scrollpane을 붙히면 그 위에 붙어있는거 다 붙혀짐
		jf_zip.setSize(600, 500); // 화면의 보여줄 창의 크기 설정
		jf_zip.setVisible(true); //화면에 보여준다.
	}
	//전체 조회 혹은 조건검색 하기 구현
	//insert here
	public void refreshData(String myDate) {
		System.out.println("refreshData호출 성공"+myDate);
		String sql = "";
		sql+="SELECT sdate, mitemp, matemp";
		sql+="  FROM seoultemp";
		if(myDate!=null || myDate.length()>0) {
			sql+=" where sdate = ?"; //'?'는 안됨
		}
		try {
			conMgr.pstmt = conMgr.con.prepareStatement(sql); //쿼리문 처리
			conMgr.pstmt.setString(1, myDate);//?들어갈 동이름이 결정됨.
			conMgr.rs = conMgr.pstmt.executeQuery();///오라클 서버에게 처리를 요청함
			Vector<SeoulTempVO> v = new Vector<>();
			SeoulTempVO zcVOS[] = null;
			SeoulTempVO zcVO = null;
			while(conMgr.rs.next()) {//커서 이동 1번, 커서이동 3번 데이터를 가지고 있을때 인스턴스화 하기 
//			System.out.println("while문 : "+rs.next()); //커서이동 2번, rs.next():현재 커서가 위치하는 곳에 데이터가 있습니까? 네true 아니오false
				zcVO = new SeoulTempVO();
				zcVO.setSdate(conMgr.rs.getString("sdate"));
//			zcVO.setAddress(rs.getString(1)); 1이 address을 의미하지만 직관적이지 않으므로 이렇게 쓰지 않는다.
				zcVO.setMitemp(conMgr.rs.getInt("mitemp"));
				zcVO.setMatemp(conMgr.rs.getInt("matemp"));
				v.add(zcVO);
			}
			zcVOS = new SeoulTempVO[v.size()];
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
					oneRow.add(0, zcVOS[x].getSdate());
					oneRow.add(1, zcVOS[x].getMitemp());
					oneRow.add(2, zcVOS[x].getMatemp());
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

	//메인메소드
	public static void main(String[] args) throws Exception{
		//new SeoulTempView().initDisplay(); 원래 이렇게 사용했지만 이젠 생성자에서 호출하자.
		  conMgr.getConnection();
		  new SeoulTempView();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) { //이벤트를 처리하는 메소드 
		//이벤트가 감지된 버튼의 주소번지를 읽어오는 메소드임.
		Object obj = ae.getSource();
		if((obj == jbtn_search)||(obj == jtf_date)) {
			String mydate = jtf_date.getText();
			//java에서는 같은 이름의 메소드를 재정의할 수 있다.
			//단, 파라미터의 갯수가 다르거나 파라미터 타입이 반드시 달라야 한다.
			refreshData(mydate);
		}
	}
}
