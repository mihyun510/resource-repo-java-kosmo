package book.chap09;

public class DuckTest {

	public static void main(String[] args) {
//		Duck myDuck = new Duck(); Duck은 추상 클래스이고 추상메소드가 존재하므로 자기자신으로 인스턴스화 불가능하다. 그 추상메소드를 재정의하지 않는 이상 - 스스로 재정의하던지 재정의한 클래스로 생성하던지
		
		//선언부의 타입과 생성부의 타입이 다를 때 다형성을 누릴 수 있다.
		Duck myDuck = new WoodDuck(); //WoodDuck()은 Duck의 추상메소드를 상속받아 재정의한 클래스이다.
	
		//다형성은 그때마다 결과각 달라집니다.
		myDuck.performFly(); //나는 날지 못합니다. 왜? WoodDuck이니까...
	
		myDuck = null;
		myDuck = new MallardDuck();
		myDuck.performFly(); //나는 날고 있어요.
		
		myDuck = null;
		myDuck = new RoberDuck();
		myDuck.performFly(); //나는 날고 있어요.
		
	}
}
