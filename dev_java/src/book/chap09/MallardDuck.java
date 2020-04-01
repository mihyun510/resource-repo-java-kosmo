package book.chap09;

public class MallardDuck extends Duck {
	
	public MallardDuck() { //인터페이스의 생성을 FlyWithWings()로 생성
		flyBehavior = new FlyWithWings();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	
}
