package book.chap09;

public class WoodDuck extends Duck{ //일반클래스에서 추상 클래스를 상속받으면 추상메소드를 무조건 재정의 해야 되지만
									//추상클래스에서 추상클래스를 상속받으면 추상메소드를 무조건 재정의 할 필요가 없다.
	public WoodDuck() {
		//생성타입은 인터페이스인데 생성은 이 인터페이스를 구현한 구현체클래스로 생성해요.
		//이렇게 하면 다형성을 누릴 수 있습니다.
		flyBehavior = new FlyNoWay(); //날지 말아야되요.
	}
	
	@Override 
	public void display() { //템플릿 메소드 - 추상클래스에서 정의 해놓지 않은 기능을 무조건 재정의 하기.
		// TODO Auto-generated method stub
		
	}
	
	
	
}
