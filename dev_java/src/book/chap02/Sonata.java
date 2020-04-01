package book.chap02;
/*************************************************************************
 * 나는  클래스에서 변수의 선언 위치에 대해 말할 수 있다. 
 * 클래스 선언한 바로 다음 자리 - 전역변수
 *************************************************************************/
public class Sonata {
	//insert here
	int speed = 50; // int(정수)타입 speed(변수이름) =(대입연산자) 50(초기값) ;(문장끝)
					//전역변수 - 초기화를 생략할 수 있다.
					//왜 안해도 될까? - 생성자가 해주니깐..
	String carColor = null;//null : 참조형(reference type-주소번지출력) 타입을 선언할때 쓰는 예약어임.
						   //모르는 값
	public boolean methodA(int a, int b) { //test클래스를 위한 메소드를 하나 만들어서 테스트하자.
		boolean isOk = false;
		if(a==b) {
			isOk = true; //요번에 휴가가는 대상이야?, 인센티브받는 대상이야? 
		}
		return isOk;
	}
	public static void main(String[] args) {
		//insert here
		//선언
		int wheel;  //int wheelNum;
		//초기화
		wheel = 4;  
		
//		강사 > 왜 초기화를 따로 적었을까요??
//		이유1: 변수의 장애 - 변수는 한번에 하나만 담을 수 있다. - 동시에 두 개를 담을 수 없다.
		int wheelNum; //지역변수 - 반드시 초기화를 해야 된다. 초기화를 생략할 수 없다.
		wheelNum = 0;
		wheelNum = 4; //기초 + 활용
		System.out.println(wheelNum);//0 or 4 
		
		//carColor의 초기화를 빨강으로 해보시오.
		//전역변수는 클래스 전역에서 사용할 수 있다.
		Sonata gnomCar = new Sonata();
		gnomCar.carColor = "빨강";
		
		System.out.println(gnomCar.carColor);//변수 다른 사용자와 함께 사용할때 편함
		System.out.println("빨강");//상수는 변하지않는 값일때만 사용
		
		//소나타 클래스안에서 티볼리안에 있는 변수나 메소드를 누릴(호출) 수 있다.
		Tivoli yourCar = new Tivoli();
		
		int num = yourCar.speed;
		System.out.println("speed= " + num);
	}////////////////////////end of main
}
