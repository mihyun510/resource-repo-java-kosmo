package book.chap09;

public abstract class Duck {
	
	public FlyBehavior flyBehavior = null;
	final int eye = 2; //오리의 눈은 두개다. 변경이 되면 안되요.
	
	Duck(){ //추상클래스는 생성자를 가질 수 있다.
		
	}
	public void performFly() {
		//인터페이스의 메소드를 사용하고 싶다면 implement를 하지 않고 인터페이스를 직접 선언하고 생성후 사용하자. 그러나 주의해야 되는 것은 인터페이스를
		//사용하기 위해서 생성할때는 인터페이스의 추상메소드를 구현한 것을 가지고 있는 구현체 클래스로 생성해야 된다는것을 잊지말아요.
		flyBehavior.fly(); //인터페이스의 메소드를 사용한다고?
	}
	public void fly() {
		System.out.println("오리는 날 수 있어요.");
	}
	
	public abstract void display(); //모양은 모두 다르다. 
									//즉, 재정의해서 사용할 기능을 추상메소드로 만들자
	
	public void swimming() { //일반 메소드
		System.out.println("모든 오리는 물위에 뜹니다. 가짜 오리도 물 위에 뜨죠");
	}
	
	
	
}
