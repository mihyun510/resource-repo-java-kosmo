package ocjp.basic;

import java.util.Scanner;

/*달의 중력이 지구의 무게에 17%으로 가정할 시 당신의 지구 몸무게를 입력하세요.
ex 내 몸무게 65kg - 변수로 처리
	달의 무게 65 * 17/100
	달의 몸무게는 얼마?

Scanner 사용
1단계 main 에서 처리
2단계 달의 몸무게를 처리하는 메서드 따로 생성

*/
public class WeightAccount {
	
	double dalKg(double d_ew) {
		double moon = 0.0;
		moon = d_ew * 17/100;
		return moon;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		WeightAccount muge = new WeightAccount();
		
		double d_ew=0.0;//지구의 몸무게 담을 변수 - 전역변수 사용 연습해봐요
		System.out.println("당신의 몸무게를 입력하세요.:");
		d_ew = sc.nextDouble();
		System.out.println("당신이 입력한 값은: "+d_ew);
		//달의 몸무게를 담기
		double d_mw=0;//달의 몸무게 담을 변수
		//17%
//		d_mw=(d_ew*17)/(double)100;
		d_mw=(d_ew*17)/100.0;
		System.out.println("main안에서 구한 달의 몸무게: "+ d_mw);
		System.out.println("==================================");
		
		double result = muge.dalKg(d_ew);
		System.out.println("메소드로 구한 달의 몸무게: "+result);
		
	}
}
