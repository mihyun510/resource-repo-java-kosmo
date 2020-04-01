package ocjp.basic;
/*
 * 전역변수
 * 인스턴스 변수로 선언할 수 있다.
 * 지역변수
 * 인스턴스 변수로 호출할 수 없다.
 */
public class Q32_1 {
	//자바에서는 원시타입의 디폴트값이 있다. double 0.0, boolean false...
	int x;//전역변수는 초기화를 생략할 수 있다. 왜냐하면 생성자가 해준다.
	boolean check() {//메소드선언하기
		x++;
		return true;
	}
	public static void main(String[] args) {
		new Q32_1().check(); // 재사용이 불가능, why? 주소값을 받을 객체가 없기때문에
		Q32_1 a1 = new Q32_1();//복사본이 또 생기는 것임. 같은 클래스 타입의 객체가 하나 더 생김. a1은 주소값을 받음.
		a1.check();//17에서 만든 new 사용
		a1.check();
		System.out.println("x : "+a1.x); //전역변수 - 인스턴스화 가능, 0
		System.out.println("x : "+(new Q32_1().x)); //재사용이 불가능하여 x값은 초기값
		int y=10;//지역변수-메소드 안에서 선언한 변수는 지역변수임.
		//System.out.println("y : "+a1.y);//지역변수 - 인스턴스화 불가능
		
	}

}
