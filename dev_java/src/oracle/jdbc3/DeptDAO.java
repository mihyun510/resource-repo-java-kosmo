package oracle.jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.util.DBConnectionMgr;

/*****************************************************************
 * 자바로 오라클 서버 연동하기 4단계
 * 1. 드라이버 클래스를 로딩한다.
 * Class.forName("드라이버 클래스 명") - 내가 사용하는 DB의 드라이버 명
 * 2.물리적으로 떨어져 잇는 서버와 연결통로를 만든다.
 * Connection con = DriverManager.getConnection(URL,"scott","tiger") - 내가 사용하는 DB의 주소와 계정, 비번
 * 3.쿼리문을 작성항 오라클 서버에게 처리를 요청한다.
 * con.preparedStatement(sb.toString())
 * pstmt.executeQuery() - select문 / pstmt.executeUpdate() - Insert문, Update문, Delete문 
 * 4.조회의 경우 ResultSet으로 커서를 조작하여 조회결과를 자바변수에 담는다. *select인 경우에만 해당*
 *
 *****************************************************************/
public class DeptDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	//부서 집합에서 조회하는 메소드 선언하기
	//Select deptno, dname, loc from dept where deptno = 10; 
	public DeptVO[] deptList(int deptno) {
		//조회 결과가 n건일 수 있으므로 객제 배열로 
		DeptVO[] dvos = null;
		StringBuilder sb = new StringBuilder("");
		sb.append("Select deptno, dname, loc from dept where deptno = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			DeptVO dVO = null;
			Vector<DeptVO> v = new Vector<DeptVO>();
			while(rs.next()) {
				dVO.setDeptno(rs.getInt("deptno"));
				dVO.setDname(rs.getString("dname"));
				dVO.setLoc(rs.getString("loc"));
				v.add(dVO);
			}
			dvos = new DeptVO[v.size()];
			v.copyInto(dvos);
//			for (int i = 0; i < dvos.length; i++) {
//				System.out.println(dvos[i].getDeptno());
//				System.out.println(dvos[i].getDname());
//				System.out.println(dvos[i].getLoc());
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sb.toString());
		}finally { //이 안에서 에러가 발생하더라도 자원반납은 무조건 꼭 해주세요. - 에러가 발생해도 안의 실행문은 무조건 실행.
			//사용한 자원은 반납해주세요. - 서버다운을 방지하기 위해서 사용을 끝내면 각 연결을 끝는다.
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return dvos;
	}
	//select deptno, dename, loc, from dept where deptno > 10 and dname = ?;
	//메소드 오버로딩이라고 한다.
	//무조건 파라미터의 갯수가 다르거나 혹은 파라미터의 타입이 달라야 한다.
	public DeptVO[] deptList(String dname, int deptno) {
		int i = 1;
		DeptVO[] dvos = null;
		DeptVO vos = null;
		StringBuilder sb2 = new StringBuilder("");
		sb2.append("select deptno, dename, loc, from dept where deptno > ? and dname = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb2.toString());
			pstmt.setInt(i++, deptno);
			pstmt.setString(i++, dname);
			rs = pstmt.executeQuery();
			Vector<DeptVO> v = new Vector<DeptVO>();
			while(rs.next()) {
				vos.setDeptno(rs.getInt("deptno"));
				vos.setDname(rs.getString("dname"));
				v.add(vos);
			}
			dvos = new DeptVO[v.size()];
			v.copyInto(dvos);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sb2.toString());
		}finally { //이 안에서 에러가 발생하더라도 자원반납은 무조건 꼭 해주세요. - 에러가 발생해도 안의 실행문은 무조건 실행.
			//사용한 자원은 반납해주세요. - 서버다운을 방지하기 위해서 사용을 끝내면 각 연결을 끝는다.
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return dvos;
	}
	//insert into dept(deptno, dname, loc) values(?,?,?);
	//신규 부서 정보를 등록하는 메소드 선언하기
	public int deptInsert(int deptno,String dname, String loc) {
		int i = 1;
		int result = 0;
		StringBuilder sb3 = new StringBuilder("");
		sb3.append("insert into dept(deptno, dname, loc) values(?,?,?)");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb3.toString());
			pstmt.setInt(i++, deptno);
			pstmt.setString(i++, dname);
			pstmt.setString(i++, loc);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sb3.toString());
		}finally { //이 안에서 에러가 발생하더라도 자원반납은 무조건 꼭 해주세요. - 에러가 발생해도 안의 실행문은 무조건 실행.
			//사용한 자원은 반납해주세요. - 서버다운을 방지하기 위해서 사용을 끝내면 각 연결을 끝는다.
			dbMgr.freeConnection(con, pstmt);
		}
		return result;
	}
	//기존 부서 정보를 수정하는 메소드 선언하기
	//Update dept Set dname = ?, loc = ? Where deptno = ?
	public int deptUpdate( String dname, String loc, int deptno) {
		int i = 1;
		int result = 0;
		StringBuilder sb4 = new StringBuilder("");
		sb4.append("Update dept Set dname = ?, loc = ? Where deptno = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb4.toString());
			pstmt.setString(i++, dname);
			pstmt.setString(i++, loc);
			pstmt.setInt(i++, deptno);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sb4.toString());
		}finally { //이 안에서 에러가 발생하더라도 자원반납은 무조건 꼭 해주세요. - 에러가 발생해도 안의 실행문은 무조건 실행.
			//사용한 자원은 반납해주세요. - 서버다운을 방지하기 위해서 사용을 끝내면 각 연결을 끝는다.
			dbMgr.freeConnection(con, pstmt);
		}
		return result;
	}
	//사라진 부서 정보를 반영하는 메소드 선언하기
	//delete from dept where deptno = ?
	public int deptDelete(int deptno) {
		int i = 1;
		int result = 0;
		StringBuilder sb5 = new StringBuilder(""); //이것을 try-catch 밖에다가 놓아야지 catch문이나 try문 안에서 도 사용하고 밖에서도 사용이 가능하다.
		sb5.append("delete from dept where deptno = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb5.toString());
			pstmt.setInt(i++, deptno);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			//delete문에 에러가 발생했을대 delete문을 출력하는 문장을 작성할 수 있는데
			//이 때 변수 sb를 사용할 수 있다|없다.
			System.out.println(sb5.toString());
		}finally { //이 안에서 에러가 발생하더라도 자원반납은 무조건 꼭 해주세요. - 에러가 발생해도 안의 실행문은 무조건 실행.
			//사용한 자원은 반납해주세요. - 서버다운을 방지하기 위해서 사용을 끝내면 각 연결을 끝는다.
			dbMgr.freeConnection(con, pstmt);
		}
		return result;
	}

}
