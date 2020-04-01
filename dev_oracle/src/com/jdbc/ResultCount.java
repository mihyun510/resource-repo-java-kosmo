package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;
/*
 * java jdbc API 활용 연습
 * 
 */
public class ResultCount {
	
	public static void main(String[] args) {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		//interface는 단독으로 인스턴스화 불가능 - 구현체 클래스로 인스턴스화하자.
		Connection con = null; //Interface
		PreparedStatement pstmt = null; //Interface
		ResultSet rs =null; //interface 
		StringBuilder sql = new StringBuilder();
		try {
			con = dbMgr.getConnection();
			sql.append("SELECT empno FROM emp");
			pstmt = con.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_SENSITIVE //비순차적 커서 동작
													   ,ResultSet.CONCUR_UPDATABLE); //resultset을 이용해서 필요한 정보를 얻는데 필요한것?
			rs = pstmt.executeQuery(); // select문 요청시
			rs.last();//커서를 마지막으로 이동 first에 있는 커서를 맨마지막으로 이동
			int rowcount = rs.getRow();
			System.err.println("TOtal row :" + rowcount);
			rs.first(); //커서를 맨앞으로 이동.
			System.out.println("rs.next(): "+rs.next());
			rs.absolute(3); //커서를 임의의 인덱스 번호로 이동
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
