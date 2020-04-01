package book.chap06;
/*
 * 10번 12번 14번의 주소번지 모두 다른 값일 것이다.
 * 10번 12번은 같지만 14번은 다를 것이다.
 * 10번 12번 14번 모두 같은 주소값을 가질 것이다.
 */
public class Tivoli {
	public int speed=0;
	//디폴트 생성자 구현하기 - 파라미터가 없는 생성자임
	//JVM이 대신 만들어 줄 수 있는 생성자 - 파라미터를 결정하지 않아도 되니까 제공가능함.
	public Tivoli() { //생성자 호출하기
		//this - 자기자신의 주소
		//this() - 자기 자신의 생성자를 호출하기
		this(50);
	}
	Tivoli(int speed){
		this.speed = speed;
	}
	public static void main(String[] args) { 
		Tivoli myCar = new Tivoli();//디폴트 생성자 호출하기 -11-14(50) - 기본 디폴트 생성자 호출 함 기본 디폴트 생성자안에 this을 사용하여 자기자신의 생성자를 호출하여 50의 값을 넘겨주며 값을 생성하므로 speed의 값은 50으로 초괴화됨 그러므로 여기서 mycar의 speed의 값은 50임
//		myCar.speed=30;
		System.out.println("myCar 주.번:"+myCar); 
		System.out.println("나는 현재"+myCar.speed+"로 가는 중이야.");//위의 생성자에 의해서 mycar의 speed는 50임
//		자바에서는 같은 이름의 변수를 중복선언불가 그래서 앞에 타입을 빼고 초기화.
		myCar = new Tivoli();//초가화를 해줌
//		myCar.speed=50;  // 마찬가지로 위와같은 현상
		System.out.println("나는 현재"+myCar.speed+"속도를 내고 있어.");
		System.out.println("myCar2 주.번:"+myCar);
		Tivoli herCar = new Tivoli();
		herCar.speed =100; //50으로 초기화를 하였지만 여기서 다시 100으로 초기화.
		System.out.println("나는 현재"+herCar.speed+"속도로 달리고 있어.");
		System.out.println("herCar 주.번:"+herCar);
	}
	//10번과 19번은 변수를 각가 선언했기 때문에 각각 선언 
	
}
/*
 * 결론(완결편)
 * 같은 이름의 변수로 클래스를 인스턴스화 할 수 있다.
 * 이 때 주소번지는 서로 다른 값을 가지고 있다.
 * static이 붙은 변수는 인스턴스화 없이 사용할 수 있다.
 * 그리고 또 static이 붙은 변수는 하나가 공유되는 것이다.
 * 그렇기 때문에 위에서 methodA에서 출력한 j값과 main메소드 안에서 출력된 j값이
 * 서로 같다.
 * 그러나 변수i는 static이 없으니깐...하나가 아니라 복사본이 변경되는 것이다.
 * 따라서 methodA에서 출력한 i값과 main메소드 안에서 출력된 i값이
 * 서로 다를 수 밖에 없다.
 */
