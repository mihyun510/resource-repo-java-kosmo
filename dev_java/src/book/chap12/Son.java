package book.chap12;

public class Son extends Parent{
	public void methodA() {
		System.out.println("[Sun]methodA호출 - 둘다있는 메소드 ");
	}
	
	public void methodC() {
		System.out.println("[Sun]methodC호출 - 자식한테만 있는 메소드");
	}
}
