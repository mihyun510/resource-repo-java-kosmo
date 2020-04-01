package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

//최근에 와서 RDBMS제품을 대신 할 수 있는 다른 제품들이 많이 나왔다. -무겁다, 고가, 
//경량 데이터베이스, NoSQL, MongoDB, MySQL
//빅데이터 혹은 클라우드시스템 - Mpp기반 열중심의 처리 최적화된 DB > 데이터 수집/분석
//오라클은 행중심의 처리
//ORM솔루션 제공(구글[오픈소스-쿼리문작성<-xml문서작성(디버깅편리)]=>Mybatis	, tomcat=>iBatis
//			  , Hibernate[미국/유럽-쿼리문이 없음, 우리나라 홀대-속도면에서 별로(DBA의 부정적 시선)]	, JDO,,,,,,)
//DB연동 코드의 약 30%이상이 줄어든다.
//반복되는 코드를 많이 줄여준다.
//실무에서 별로 사용x
public class ResultSetTest1 {
	public ResultSetTest1() {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		//interface는 단독으로 인스턴스화 불가능 - 구현체 클래스로 인스턴스화하자.
		Connection con = null; //Interface
		PreparedStatement pstmt = null; //Interface
		ResultSet rs =null; //interface 
		StringBuilder sql = new StringBuilder();
		try {
			//커서이동하면서 동시 작업가능
			con = dbMgr.getConnection();
			sql.append("SELECT empno, ename FROM emp");
			pstmt = con.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE //인센티브는 동기화가 안되는 것이다. 그래서 추가로 반영된것은 조회가 안되었다.
													   ,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			//데이터 추가하기
			rs.moveToInsertRow(); //추가 가능한 로우로 커서 이동
			rs.updateInt(1, 1001); //1번째 컬럼, 사원번호
			rs.updateString(2, "김유신"); //2번째 컬럼, 이름
			rs.insertRow();
			rs.moveToInsertRow(); //추가 가능한 로우로 커서 이동
			rs.updateInt(1, 1002); //1번째 컬럼, 사원번호
			rs.updateString(2, "이성계"); //2번째 컬럼, 이름
			rs.insertRow();
			while(rs.next()) {
				//동기화가 안되서 방금 추가된 것이 조회가되지않음
				System.out.println(rs.getInt("empno")+" ,"+rs.getString("ename"));
			}
			//	  ┌> 블리언 타입을 암시, 커서가 처음에 있지 않니?
			if(rs.isBeforeFirst()) {
				rs.beforeFirst();//first가 아니면 커서를 처음으로 옮겨주자.
			}
//			System.out.println("rs.next():"+rs.next()+", rs.isFirst():"+rs.isFirst());
			rs.isFirst();
			while(rs.next()) {
				System.out.println("===>"+rs.getInt("empno"));//7369
				if(rs.getInt("empno")==1001) { //따로 delete문장을 쓰지 않아도 내부적으로 커서를 옮겨서 원하는 행을 삭제해줄수 있다.
					rs.deleteRow();
				}
			}
		} catch (Exception e) { ;//java.sql.SQLException: 현재 행이 아닙니다 컬럼 추가를 안했다.
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ResultSetTest1();
	}
}
