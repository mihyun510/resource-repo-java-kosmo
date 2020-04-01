package com.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;

import com.util.DBConnectionMgr;
//프로시저를 자바로 사용하기.
public class PLSQLTest {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	CallableStatement cstmt = null;
	
	public  void empSalUpdate() {
		int result = 0;
		try {
			con = dbMgr.getConnection();
			con.setAutoCommit(false); //java에서는 default값이 자동커밋이다. 그러니 false값을 넣어서 자동커밋이 되지 않게 해주자.
			cstmt = con.prepareCall("{call proc_emp_salupdate(?,?)}");
			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			System.out.println("사원번호를 입력하세요.");
			String v_empno = br.readLine();
			cstmt.setInt(1, Integer.parseInt(v_empno)); //사원번호 입력받아서 ?에 넣기.
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR); //자바에서 제공해주는 오라클타입/nvarchar 1바이트로 표현
			result = cstmt.executeUpdate();
//			con.commit(); //커넥션이 지원하는 커밋
//			con.rollback(); //커넥션이 지원하는 롤백
			//허용되지 않은 작업: Ordinal binding and Named binding cannot be combined! 
			//프로시저의 결과를 받아올때는 변수의 명을 넣으면 않된다. 즉, 숫자를 넣어주어야된다. 몇번째 자리의 물음표의 값을 가져올 것인가.
//			System.out.println("result: "+cstmt.getString("msg")); //프로시저의 out타입의 변수의 값을 받아온다.
			System.out.println("result: "+cstmt.getString(2)); //프로시저의 out타입의 변수의 값을 받아온다.
		} catch (Exception e) {
			System.out.println("Exception : "+e.toString());
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		PLSQLTest pl = new PLSQLTest();
		pl.empSalUpdate();
	}
}
