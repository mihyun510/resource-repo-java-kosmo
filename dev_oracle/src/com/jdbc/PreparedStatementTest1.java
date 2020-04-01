package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatementTest1 {

	public static void main(String[] args) {
		//								┌> 아이피..?127.0.0.1??
		String url ="jdbc:oracle:thin:@192.168.0.15:1521:orcl11";
		String user = "scott";
		String pw = "tiger";
		Connection con = null; //java.sql.*
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Scanner sc = new Scanner(System.in);
		try { //밑의 실행문에서 파일을 찾을 수 없을 수도 있다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("con"+con);
			System.out.println("사원번호를 입력하세요.");
			int u_empno = sc.nextInt();
			StringBuilder sql = new StringBuilder();
			sql.append("select ename, job from emp");
			sql.append(" where empno = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, u_empno); 
			//insert here 처리
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("ename:"+rs.getString(1)+", jop"+rs.getString(2));
			} else {
				System.out.println("조회 결과가 없습니다.");
			}
			//드라이버클래스에 대한 예외처리
		} catch (ClassNotFoundException e) { //직접적인 에외 처리
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		} catch (Exception e) { //최상위 에외 처리
			//쿼리에 대한 예외를 잡을 예외처리 
			//java.util.InputMismatchException : 한글을 입력했을 경우 타입이 맞지 않는다는 예외처리 구문 발생, 
			//java.sql.SQLException: pstmt.setInt(1, u_empno); 구문이 누락되었을 경우 ===> 숫자를 입력했을 경우 SQL문에서 인덱스가 누락된 in또는 out이 있다는 1(갯수)예외처리 구문이 발생
			//java.sql.SQLSyntaxErrorException: ORA-00904: "JOP": 부적합한 식별자 - 쿼리의 컬럼명이 틀렸을 경우 부적합한 식별자라는 syntax오류 발생.
			e.printStackTrace();//stack영역에 쌓여 있는 에러메세지의 history를 출력하고 line번호까지 출력해줘요.
		} finally {
			System.out.println("정상적으로 처리가 되었을때도 처리가 실행됨.");
			//사용한 자원 반납하기
			//반납할 때는 생성된 역순으로 처리하기
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("여기");
	}
}
