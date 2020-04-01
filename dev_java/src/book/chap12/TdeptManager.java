package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class TdeptManager extends JFrame{
	//선언부
	String[] col 	= {"아이디", "사원명", "부서명"};
	String[][] data = new String[0][3];
	DefaultTableModel dtm = new DefaultTableModel(data,col);
	JTable jtb 		= new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jtb);
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	//생성자
	public TdeptManager() {}
	//arraylist - 데이터를 받아보자
	public ArrayList<TempVO> getDeptList(){ //emp_id, emp_name을 생각하면 TempVO가 제네릭에 오는 것이 맞지만 dept_name을 생각하면  DeptVO가 오는 것이 맞아요 .
											//이렇때는 어떡할까요?
		ArrayList<TempVO> al = new ArrayList<TempVO>(); 
		StringBuilder sb = new StringBuilder("");
		sb.append("select emp_id, emp_name, dept_name 		");
		sb.append(" from temp, tdept						");
        sb.append(" where temp.dept_code = tdept.dept_code  ");

        try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			TempVO tVO = null;
			while(rs.next()) {
				tVO = new TempVO();
				tVO.setEmp_id(rs.getInt("emp_id")); 
				tVO.setEmp_name(rs.getString("emp_name"));
				tVO.setDept_code(rs.getString("dept_name"));
				al.add(tVO);
			}
			
		} catch (SQLException se) { 
			System.out.println(se.toString()); 
		} catch (Exception e) { 
			System.out.println(e.toString());
		}
        return al; 
	}
	//화면구현부
	public void initDisplay() {
		this.setTitle("사원정보조회");
		this.add("Center", jsp);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	//메인메소드
	public static void main(String[] args) {
		TdeptManager tm = new TdeptManager();
		tm.initDisplay();
	}
}
