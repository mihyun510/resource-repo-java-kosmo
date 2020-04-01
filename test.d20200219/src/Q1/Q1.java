package Q1;

import java.util.Calendar;

/*
 * 1. 다음의 요구사항을 충족하는 프로그램의 소스코드와 결과화면 
 * 스크린 샷을 제출하시오
  [요구사항] 
  가. 자신이 태어난 년도인 숫자를 저장 할 수 있는 변수 year를 선언하고
      자신이 태어난 년도를 대입한다.     
  나. 자신의 나이를 저장할 수 있는 변수 age를 선언하고, 
     year 변수를 사용하여 자신의 나이를 
     계산하여 대입한다(공식:현재년도-태어난 년도)
     단,현재 년도는 Calendar클래스를 사용하여 구해야 한다
     그리고 age 와 year를 출력하여라
 */
/***************************************************************************
 * 인스턴스화
 * 1. A a = new A(); 
 * 2. A a = null; 
 *    a = A(); 
 * Calender cal = Calender.getInstance();
 * 
 ******************************************************/
public class Q1 {
	
	public static void main(String[] args) {
		int year = 0; //년도를 담을 변수 선언 
		year = 1998;  //년도를 초기화 (1998은 정수형)
		int age = 0;  //나이 선언
		int cyear = 0;
		//			   ┌>getInstance()에 대한 소유주
		Calendar cal = Calendar.getInstance(); // 2번의 인스턴스화 + 싱글톤 패턴  [더 생성을 안하고 하나를 가지고 나누어 쓰자 .]
		//          ┌>get이라는 함수의 리턴타입은 int임. 그러니 int인 타입인 변수로 받아야됨. 여기서는 현재 년도를 받아야되므로 cyear에 리턴받음.
		//			│	┌>YEAR가 static int 타입이므로 접근은 인스턴스화한 객체로 접근하는거 대신 class명으로 접근한다. - static은 하나이기 때문이다. 다같이 사용한다.
		cyear = cal.get(Calendar.YEAR); 
		age = cyear - 1998; //현재 년도에서 내가 태어난 연도를 뺌 즉, 나이계산
		System.out.println("age===> "+age);
		System.out.println("cyear===> "+year);
	}
	
//	public int Calender(int year) {
//		int age = 0; //나이를 계산해서 리턴할 변수 선언
//		int currentYear = 0; // 현재 년도 선언 및 초기화
//		Calendar cal = Calendar.getInstance();
//		currentYear = cal.get(Calendar.YEAR);
//		System.out.println(currentYear);
//		age = currentYear - year; //나이 계산 식
//		return age; //나이를 리턴
//	}
//	public static void main(String[] args) {
//		Q1 q1 = new Q1();
//		//가.
//		int year = 0; //자신이 태어난 년도인 숫자를 저장할 변수 선언
//		year = 1998; //내가 태어난 년도를 초기화
//		
//		//나.
//		int age = 0; //자신의 나이를 저장할 수 있는 변수 선언
//		
//		//나이를 계산하는 함수로 내가 태어난 년를 대입하고
//		//계산된 나이를 리턴 받는다.
//		age = q1.Calender(year);  
//		
//		//나의 나이와 태어난 년도를 출력한다.
//		System.out.println("나이(만으로)는 "+age+", 내가 태어난 년도는 "+year);
//		System.out.println("나이는 "+(age+1)+", 내가 태어난 년도는 "+year);
//	}
}
