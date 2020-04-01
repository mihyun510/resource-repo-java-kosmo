package book.chap12;

public class Parent {
	public void methodA(){
		System.out.println("[Parent]methodA호출 - 둘다있는 메소드");
	}
	
	public void methodB() {
		System.out.println("[Parent]methodB호출, 부모한테만 있는 메소드");
	}
}
