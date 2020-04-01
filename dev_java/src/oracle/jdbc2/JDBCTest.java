package oracle.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/*
 * 변수 이름 앞에 final이 붙으면 상수가 됨.
 * 상수는 다른 값으로 재정의 불가함
 */
public class JDBCTest {
	//이 클래스를 읽어야 오라클 제품인것을 확인가능함
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";//oracle.jdbc.driver패키지이름
	//물리적으로 떨어져 있는 오라클 서버에 URL정보 추가 //여기 컴퓨터가 켜져있어야 접근가능,orcl11 sd아이디?
	public static final String _URL = "jdbc:oracle:thin:@192.168.0.15:1521:orcl11"; 
	//계정이 있어야 게임서버에 접속가능, 사원이 퇴사하면 바꿔야 되니 final은 아님
	public static String _USER = "scott";
	public static String _PW = "tiger"; //오라클 회사에서 만들어서 제공해주는 driver임. 오라클 서버에 접근하기 위한 사전준비 코드
	//물리적으로 떨어져 있는 오라클 서버와 연결통로를 만들 때 사용하는 클래스 
	Connection con = null;
	java.sql.PreparedStatement pstmt = null; //패키지 이름 java.sql.
	//오라클에 살고있는 커서를 조작하는 클래스를 제공함.
	//커서 위치에 로우가 존재하면 true, 조회된 결과가 없으면 false리턴 - if사용 sql를 사용하면 결과가 하나여서
	//java.lang폴더를 제외하고는 모두 다 import해주어야 JVM이 그 클래스를 찾음.
	java.sql.ResultSet rs = null;
	public String currentTime() throws Exception{//23번 예외처리를 위해 throws Exception 추가
		Class.forName(_DRIVER);//오라클 제조사 정보를 가지고 있어요.
		String sql = "select to_char(sysdate,'HH24:MI:SS') from dual"; //스트링 안에다가 오라클의 선언문을 작성할 수 도 있다. 변수처럼
		//아래 메소드가 호출되면 오라클 서버와 연결통로를 갖게됨.
		//이 연결통로를 통해서 select문을 전령 클래스가 가지고 들어가야 함. 외워쓰자 절대 변하지 않음.
		con = DriverManager.getConnection(_URL, _USER, _PW); //만약 ip주소를 틀리면 런타임에러가 날수있음 그래서 예외가 날수잇음을 알림 //static
		pstmt = con.prepareStatement(sql); //non-static
		rs = pstmt.executeQuery();//오라클 서버에게 처리를 요청함
		if (rs.next()) {
			return rs.getString(1);
		}
		return "15:00:49";
	}
	public static void main(String[] args) throws Exception{
		//java.lang패키지에 클래스는 모두 찾지만 그 외 패키지는 찾을 수 없다.
		//Scanner scanner = new Scanner(System.in);
		JDBCTest jt = new JDBCTest();
		String ctime = jt.currentTime();
		System.out.printf("현재 시간은 %s 입니다.\n", ctime);
	}
}






