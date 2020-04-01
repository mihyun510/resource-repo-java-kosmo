package book.chap08;

public class WoodDuck extends Duck{
	public void fly() {
		System.out.println("나는 날지 못합니다.");
	}

	@Override
	public void swimming() {
		//재정의
		System.out.println("나무오리는 물위에 뜨긴 떠요");
		super.swimming();
	}
	
	

}
