package friday0131;

import java.util.Scanner;

public class TVSimulation {
	
	public static void main(String[] args) {
		System.out.println("TV를 보시겠습니까?");
		Scanner sc = new Scanner(System.in);
		boolean isOk = sc.nextBoolean();//"true"->true
		//너 전원 버튼 누른거니?
		if (isOk) {
			System.out.println("true를 입력하셨군요");
		}
		else {
			System.out.println("false를 입력하셨군요.");
		}
		TV tv = new TV();
		//tv.power();//false => !false => true 파라미터가 없으니깐 값이 안넘어감
		//파라미터가 있어야 내 의사를 표현할 수 있다. => 소통시작
		tv.power(isOk);//false => !false => true 
		//현재 TV는 전원이 겨진 상태입니까?
		System.out.println(tv.power);//true
		if(tv.power) {
			System.out.println("현재 TV를 시청중입니다.");
		}
		else {
			System.out.println("아무도 TV보고 있지 않아요.");
		}
	}
}
