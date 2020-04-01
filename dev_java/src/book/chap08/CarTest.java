package book.chap08;

public class CarTest {
	public static void main(String[] args) {
		//myCar로 접근할 수 있는 변수의 갯수는 몇개 일까요?
		//myCar로 호출할 수 있는 메소드의 갯수는 몇개 일까요?
		//Car로 객체를 생성한 경우에는 메소드 한개 변수 한개만 호출 할 수 있어요.
		/*
		 * myCar의 타입이 Car타입이여서 Tivoli타입의 변수나 메소드는 한 개도 접근, 호출 불가합니다.
		 * 이것은 new Tivoli()로 인스턴스화한 경우에도 동일하게 적용됩니다.
		 * 다시 말하지만 타입 Car타입이어서 생성부의 이름이 설사 Tivoli가 온다 하더라도 
		 * Tivoli타입의 변수나 메소드는 접근, 호출이 불가하다는 것이죠.
		 * 이런 경우 메소드는 부모의 자식클래스 모두에 선언해 놓으면[메소드오버라이드] 호출은 가능합니다.
		 * 만일 부모에는 존재하고 자식에는 존재하지 않는 메소드의 경우 둘다 무조건 부모 메소드가 호출된다.
		 * 객체생성은 무조건 생성부 이름으로 생성되는 것이다.
		 * 그러나 변수는....
		 */
		
		Car myCar = new Car();
		System.out.println(myCar.speed);
		myCar.stop();
		myCar = null;			//새로운 주소를 받기전에 일단 안에 쓰레기를 비워준다. 언젠가 자바가 쓰레기를 버려주지만 빨리 비우기 위해서 최적화>.?
		myCar = new Tivoli(); //20번에서 21번으로 진행되는 과정에서 candidate상태
		System.out.println(myCar.speed);
		myCar.stop();
		
		///////////////////////////////////////////////////////////////////////EX
//		Car myCar = null;
		myCar = new Car();  //부모타입의 변수는 자신의 클래스의 생성자로 생성이 가능
		myCar.speed = 100; // myCar로 접근가능한 변수의 갯수는 1개이다..? Car클래스에 선언된 변수 (전역변수)는 speed밖에 없음.
		myCar.stop(); 	   // myCar로 접근가능한 메소드의 갯수는 1개이다...Car클래스에 정의된 메소드는 stop이 전부다.
		
		myCar = new Tivoli(); //부모타입의 변수는 자신이 상속하고 있는 자식 클래스의 타입으로 생성이 가능,.
		myCar.speed = 10; //myCar을 자식 클래스인 Tivoli로 생성하였어도 Car타입의 자원에서 재정의한 것의 자원만 접근가능하므로 변수는 speed만 접근가능
		myCar.stop();	  //메소드는 자식 클래스에서 재정의한 메소드가 없으므로 자신의 클래스 타입인 stop()[부모 클래스안에 있음]만 접근가능
		//herCar로 접근할 수 있는 변수의 갯수는 몇개 일까요?
		//herCar로 접근할 수 있는 메소드의 갯수는 몇개 일까여?
		Tivoli herCar = null;
		herCar = new Tivoli(); //자식클래스는 부모타입의 생성자를 사용하여 생성이 불가능
		herCar.speed = 50;     //부모를 상속받고 있다면 상속받고 있는 부모클래스안의 자원을 사용가능, 자신의 클래스안에서 부모클래스의 자원을 재정의가능.
		herCar.volumn = 70;    //자신의 클래스 안에 자원도 사용가능
		herCar.stop();			//부모의 클래스안에 있는 메소드 사용가능
		herCar.volumup();		//자신의 클래스안에 있는 메소드 사용가능
		herCar.volumdown();		//위와 같음 
//		Car himCar = null;
		
		
		
	}
}
