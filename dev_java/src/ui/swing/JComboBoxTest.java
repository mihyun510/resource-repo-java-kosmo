package ui.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.util.DBConnectionMgr;

public class JComboBoxTest implements ItemListener{
	
	//선언부
	String[] data = null; //DB연동 전 스트링 배열 선언과 초기화하기, 데이터를 먼저 선언하고 콤보박스 선언 후 넣어주기
	JComboBox jcb_dept = null; //선언만 하고 뒤에서 적절히 생성헤주지않으면 널이 들어가므로 nullpointerException이 발생함
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	JFrame jf_dept = new JFrame();
	
	//생성자
	public JComboBoxTest() {
		//insert here
		data = getDepList(); //자신안에 있는 메소드 이므로 바로 호출 가능. 
		jcb_dept = new JComboBox(data); //나눠서 인스턴스화를 해야지 데이터를 받아서 그 데이터를  화면처리함수에 데이터를 전달하기 가능 
		initDisplay();			//데이터가 콤보박스에 들어감.
	}
	
	//화면구현부
	void initDisplay() {
		//
		jcb_dept.addItemListener(this);// [이벤트 핸들러]  자신의 클래스 안에 존재해서 this를 전달
//		jf_dept.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf_dept.add("North",jcb_dept);
		jf_dept.setSize(300,200);
		jf_dept.setVisible(true); 				//true을 변수에 담아서 창을 띄우는 여부를 조절하기.
	}
	/*
	 * 오라클 서버에서 dept테이블에 있는 정보를 조회하시오.
	 * 조회된 정보를 data배열에 초기화 하시오.
	 */
	public String[] getDepList() {
		String depts[] = null;
		StringBuilder sb = new StringBuilder("");
		sb.append("select dname	"); 
		sb.append("  from dept  ");
		try {
			//insert here - DB연동을 해보자
			con =dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery(); 
			Vector<String> v = new Vector<String>();
			while(rs.next()) {
				String dname = rs.getString("dname");
				//조회된 값을 벡터 클래스에 추가한다.
				v.add(dname);
			}
			depts = new String[v.size()];				
			v.copyInto(depts);							
		} catch (Exception e) {
			System.out.println("Exception"+e.toString());
		}
		return depts;
	}
	
	//메인메소드
	public static void main(String[] args) {
		new JComboBoxTest();
	}
	/*
	 * ItemListener의 공식명칭은 인터페이스 이다.
	 * 인터페이스는 추상메소드를 가지고 있으므로 반드시 구현해 주어야 한다.
	 * 이때 부모가 가진 메소드의 원형을 절대로 훼손해서는 안된다.
	 */
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object obj = ie.getSource();
		if(obj == jcb_dept) {
			if(ie.getStateChange() == ItemEvent.SELECTED) { 
				System.out.println(jcb_dept.getSelectedIndex()); //콤보 박스의 String[]배열의 정보에 인덱스를 붙여서 출력.
			}
		}
	}
}
