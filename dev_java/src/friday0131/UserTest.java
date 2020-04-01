package friday0131;

import java.util.Scanner;
//같은숫자 존재 그러나 위치 다를때 ball증가
//같은 숫자 존재 자리도 같으면 스트라이크 증가
//스트라이크와 볼로 힌트 주기
//지변으로 할것인가 전벼으로 하 것인가.
//새로운 숫자를 계속 받고 변하므로 지변으로 하기
//메소드 이름 account return타입 스트링 
//함수 매개변수 String타입
public class UserTest {
	//사용자가 입력한 값을 받아오는 곳
	/**************************************************************************************
	 * 사용자가 입력한 값에 대한 힌트를 출력하는 메소드 입니다.
	 * @param user 사용자가 입력한 세자리 숫자입니다.
	 * @return 컴터가 채변한 숫자와 사용자가 입력한 숫자를 비교한 후 힌트문을 전달합니다.
	 * 버전
	 * 작성일
	 * 작성자: 이순신
	 **************************************************************************************/
	public String account(int[] my) { //왜 스트링을 리턴타입으로 해야하냐고.,.,. 내가 입력한값을 매개변수로 받음
										//왜 스트링이냐면 리턴을 "0스2볼"이런식으로 할 예정임..
		//int[] com = new int[3];
		RandomTest rt = new RandomTest();
		int strike = 0;  //0은 왜 스트링임.,..,.? 지역변수로 하는 이유 계속 변하는 값이기 때문
		int ball = 0;
		for (int i = 0; i < rt.com.length; i++) {
			for (int j = 0; j < my.length; j++) {
				if( my[j]== rt.com[j]) {//내가 입력한 숫자중에 컴터에 그 숫자가 있나?
					if(i == j) {//혹시 그 숫자가 자리도 일치하는거야?
						strike++;
					}//스트라이크 확보
					else {
						ball++;
					}
				}//볼카운트 확보
			}///end of in for
		}//end of out for
		if(strike==3) {
			return "정답입니다. 축하합니다.";
		}
		
		return strike+"스볼"+ball+"볼"; //스트링타입!!!!!!!!!!!!!!!!!!
	}
	public static void main(String[] args) {
		
		int[] my = new int[3];
		System.out.println("세 자리 숫자를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		int imsi = 0;//채번한 숫자를 담을 변수
		int cnt = 0; //입력 받은 횟수를 세는 변수
		while(cnt < 3) {
			imsi = sc.nextInt();
			System.out.println(cnt+": 사용자가 입력한 숫자는"+imsi+"입니다.");
			my[cnt] = imsi;
			cnt++;
		}
		
	}

}
