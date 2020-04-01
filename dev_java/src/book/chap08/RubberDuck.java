package book.chap08;

public class RubberDuck extends Duck{
	public void fly() {
		System.out.println("나는 날고 있어요.");
	}

	@Override
	public void swimming() {
		System.out.println("나는 물위에 뜰 수 있지만 잠수는 불가능합니다.");
		System.out.println("================================");
		super.swimming();
	}
	
	

}
