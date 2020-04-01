package oracle.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Vector;

public class ZipCodeSearch {
	//선언부
	//드라이버 클래스가 필요하다. DB연동 할때 마다 필요함. - JDBCTest에서 꺼내 쓰자.
	//URL정보도 JDBCTest에서 꺼내 쓸 수 있다.
	//user와 pw는 생략할 수 있다.(선언하지 않아도 된다) -왜?static때문이다.[클래스명.변수(메소드명)]
	Connection 			con 	= null;
	PreparedStatement 	pstmt 	= null;
	ResultSet			rs		= null;
	//오라클 서버와 연결통로 만들기를 메소드로 꺼내보세요.
	//메소드 뒤에 Exception을 붙히는 건 드라이블 클래스를 잘못 작성하여 에러가 아닌
	//런타임에러 즉, ClassNotFoundException을 맞을 수 있기 때문에 선언하였다.
	
	
////////////////////////////////////////////////////////////////////////////////	
	public Connection getConnection() throws Exception{
		System.out.println("getConnention호출 성공");
		//오라클 회사정보를 수정함.
		Class.forName(JDBCTest._DRIVER); //오라클 제조사 제품정보
		con = DriverManager.getConnection(JDBCTest._URL, JDBCTest._USER, JDBCTest._PW); //물리적으로 떨어져있는 오라클 ip, port번호, id,pw를 적어줌으로써 커넥션 정보는 확보되었다.
		return con;
	}
////////////////////////////////////////////////////////////////////////////////	
	
	
	
	//main()-userInput[동이름결정]-getZipCodeList('당산동')
	public ZipCodeVO[] getZipCodeList(String userDong) throws Exception //예외가 발생하면 나를 호출한 곳에서 처리를 받으세요.
	//예외처리를 내가 하지않고 미룬다.
	{ //오라클 서버에게 select문을 전달하고 응답받기
		System.out.println("getZipCodeList 호출 성공"+userDong);
		ZipCodeVO zcVOS[] = null;
		ZipCodeVO zcVO = new ZipCodeVO();
		String sql = "";
		sql += "SELECT address , zipcode"; 
		sql += " FROM zipcode_t";
		sql += " WHERE dong LIKE '%'||?||'%'"; //조회결과가 3건일 경우 퀴리문 만듬
		//오라클 서버에 요청을 보내기
		getConnection();
		
		
		
//////////////////////////////////////////////////////////////////////////////////		ㄱ
		pstmt = con.prepareStatement(sql); //쿼리문 처리
		pstmt.setString(1, userDong);//?들어갈 동이름이 결정됨.
		rs = pstmt.executeQuery();///오라클 서버에게 처리를 요청함
		Vector<ZipCodeVO> v = new Vector<>();
		while(rs.next()) {//커서 이동 1번, 커서이동 3번 데이터를 가지고 있을때 인스턴스화 하기 
//			System.out.println("while문 : "+rs.next()); //커서이동 2번, rs.next():현재 커서가 위치하는 곳에 데이터가 있습니까? 네true 아니오false
			zcVO = new ZipCodeVO();
			zcVO.setAddress(rs.getString("address"));
//			zcVO.setAddress(rs.getString(1)); 1이 address을 의미하지만 직관적이지 않으므로 이렇게 쓰지 않는다.
			zcVO.setZipcode(rs.getInt("zipcode"));
			v.add(zcVO);
		}
		zcVOS = new ZipCodeVO[v.size()];
		v.copyInto(zcVOS);//벡터 자료구조에 들어있는 정보를 복사하기 
////////////////////////////////////////////////////////////////////////////////////	ㄴ	
		
		
		
//<불필요>System.out.println("while문 탈출");
//|		//서버에 요청 하기 전에 사용자로 부터 동이름을 먼저 입력 받아야 한다.
//|		//[문법에러] - private -왜? 웹이나 앱에서 동시사용자가 많을때 변조되면 안됨.
//|		//zcVO.uid_no =10; //uid_no는 static이다 접근이 가능하다 그러나 private이므로 접근이 불가능,다른 클래스에서 임의로 가져다 쓰거나 값을 바꾸는 것을 방지
//|		zcVO.setUid_no(10);
		printZipCode(zcVOS); //메소드 호출시에는 타입표시 안함.
		return zcVOS;
	}
	//조회된 우편번호 목록을 출력해보기 
	public void printZipCode(ZipCodeVO zcVOS[]) {
		for(ZipCodeVO zVO: zcVOS) {
			System.out.println(zVO.getAddress()+"   "+zVO.getZipcode());
		}
	}
	//사용자로 부터 동을 입력받는 메소드를 구현해 보세요.
	public String userInput() {
		//JDBCTest._USER="hr"; static만 있으니깐 계정이름 변경 가능함
		//JDBCTest._DRIVER=""; final이 있으니까 불가함.
		Scanner sc = new Scanner(System.in);
		String userDong = null;
		userDong = sc.nextLine();
		return userDong;
		//return "당산동";
	}
	//메인메소드 - main에는 거의 코딩X
	/*
	 * 25(가장먼저호출-entry pointer-main 스레드 : 우선순위가 가장 처음에 있어요.)-27(변수선언:지변)-28(인스턴스화)-
	 * 29(userInput()함수를 호출)-11(파라미터는 없다:리턴은 있다.)-12(scanner)-13(변수선언:지변)-14(입력받은것을 userDong에 대입)-
	 * 15(입력받은값확정)-29(리턴값받음)-30(리턴값으로 if문 조건문 실행)
	 * 
	 */
	public static void main(String[] args) throws Exception{//예외처리 한번  붙히면 계속 미뤄짐
		System.out.println("동을 입력하세요.");
		String userDong = "";
		ZipCodeSearch zs = new ZipCodeSearch();
		userDong = zs.userInput();//데드코드인 이유 함수호출 후 리턴을 받지 못함.
		if(userDong == null) {
			System.out.println("반드시 동을 입력해야 합니다.");
			return;//main를 탈출하고 끝
		}
		else {			
			System.out.println("당신은 "+ userDong +"을 입력하셨습니다.");
			ZipCodeVO zcVOS[] = zs.getZipCodeList(userDong);
		}
	}
}
















