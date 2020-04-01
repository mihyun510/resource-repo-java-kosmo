package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class Proc_emp_update2 extends JFrame{
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null; //물리적으로 떨어져 있는 오라클 서버에 연결통로 만듬
	CallableStatement cstmt = null; //저장 프로시저를 요청할 때 사용
	public void empUpdate2() {
		String sdeptno = JOptionPane.showInputDialog("부서번호를 입력하세요.");
		int deptno = 0;
		int result = 0;
		//DB연동은 반드시 예외처리/ 네트워크 소켓 통신도 반드시 예외처리
		try {
			con = dbMgr.getConnection();
			con.setAutoCommit(false); //자동 커밋 해제
			cstmt = con.prepareCall("{call Proc_emp_update2(?)}");
			deptno = Integer.parseInt(sdeptno);
			cstmt.setInt(1, deptno);
			result = cstmt.executeUpdate();
			System.out.println("result:"+result);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} finally {
			dbMgr.freeConnection(con, cstmt);
		}
	}
	
	public static void main(String[] args) {
		new Proc_emp_update2().empUpdate2();
	}

}
