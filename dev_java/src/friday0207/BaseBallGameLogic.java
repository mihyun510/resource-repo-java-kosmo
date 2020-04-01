package friday0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import com.util.DBConnectionMgr;

//ranCom메소드를 추가해봅시다.
public class BaseBallGameLogic {
	Connection con = null; //물리적으로 떨어져 있는 서버와 연결통로 만들기
	PreparedStatement pstmt = null; //동적 쿼리 작성하기 - ?
	//싱글톤 패턴을 활용하여 객체 주입받기 - 하나로 나누어 쓴다. 왜냐하면 인스턴스화를 하나만 할 수 있게 함수로 기능을 지정해놈
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	//컴퓨터가 채번한 숫자를 담는 배열
	int com[] = new int[3];
	//사용자가 입력한 숫자를 담는 배열
	int[] my = new int[3];
	public int history (BaseballVO bVO) {//게임에 대한 이력관리 - 데이터베이스로 정보를 저장(테이블 생성) 각각의 값을 디비로 저장 
//		String sql ="";
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into baseball(game_no, game_seq,game_date ");
        sb.append("        ,input, hint, dap,score, mem_id)         "); //멤버아이디는 로그인하면 자동으로 가져올 수 있게 해야함. 로그인화면이 필요
		sb.append("  values(seq_baseball.nextval                    ");
		sb.append("		 ,?,to_char(sysdate,'YYYY-MM-DD')        ");
		sb.append("		 ,?,?,?,?,?)		     ");	//회차 등 입력받을 것은 ?로 설정.
		int result = 0; // 1이면 입력 성공 0이면 입력 실패
		try {                                                          
			//insert here - DB연동을 해보자
			con =dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString()); 
			pstmt.setInt(1, bVO.getGame_seq()); //첫번재 물음표자리에 Game_seq의 값을 넣어주겠다. 
			pstmt.setString(2, bVO.getInput());
			pstmt.setString(3, bVO.getHint());
			pstmt.setString(4, bVO.getDap());
			pstmt.setInt(5, bVO.getScore());
			pstmt.setString(6, bVO.getMem_id());
			//1row inserted
			result = pstmt.executeUpdate(); //위에서 작성한 insert문을 처리해 주세요 - 오라클에게 요청
			if(result == 1) System.out.println("입력성공");
			else System.out.println("입력 실패");//결과에 대한 피드백 처리
		} catch (Exception e) {
			System.out.println("Exception"+e.toString());
		}
		return result;
	}
	/*********************************************************************************
	 * 세자리 입력한 숫자에 대한 힌트문 구현하기
	 * @param user 사용자가 입력한 값
	 * @return 힌트문 반환 예)1스 2볼
	 *********************************************************************************/
	public String account(String str) {
		int temp = Integer.parseInt(str);
		my[0] = temp/100;//123/100=1
		my[1] = (temp%100)/10;//2
		my[2] = temp%10;//3
//		for(int me:my) {
//			System.out.print("me:"+me);//0 0 0
//		}
		
		for (int i = 0; i < com.length; i++) {
			System.out.println(com[i]);
		}
		
		int strike = 0;
		int ball = 0;
		for(int i=0;i<com.length;i++) {
			for(int j=0;j<my.length;j++) {
				if(com[i]==my[j]) {//내가 입력한 숫자중에 컴터에 그 숫자가 있니?
					if(i==j) {//혹시 그 숫자가 자리도 일치하는거야?
						strike++;
					}//스트라이크 결정
					else {
						ball++;
					}
				}//////볼카운트 확보
			}//////////end of innert for
		}//////////////end of outter for
		if(strike==3) {
			return "정답입니다. 축하합니다.";
		}
		return strike+"스 "+ball+"볼";
	}	
	
	
	public void ranCom() {
		Random r = new Random();//0.0~
		com[0] = r.nextInt(10);//0.0~10.0
		do {
			com[1] = r.nextInt(10);//0.0~10.0
		}while(com[0]==com[1]);
		do {
			com[2] = r.nextInt(10);//0.0~10.0			
		}while((com[0]==com[2])||(com[1]==com[2]));
	}
	
	
}
