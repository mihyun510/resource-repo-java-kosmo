package book.chap09;
//재사용성이 높아진다.
//독립성이 높아진다.
//단위테스트가 가능하다.
//통합테스트도 할 수 있다.
//의존성이 낮다.
//프레임워크가 인터페이스로 구성된 이유 - 틀만 재공해주기 때문에 의존성이 낮다.
public class FlyWithWings implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("나는 날고 있어요");
	}

	
}
