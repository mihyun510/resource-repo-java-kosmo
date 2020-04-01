package ocjp.basic;

public class B {
	void methodA() {
		
	}
	
	static void methodB() {
		
	}
	
	public static void main(String[] args) {
		//methodA();메소드가 static이 아니라서 그냥 사용 불가능
		B b = new B();
		b.methodA(); //인스턴스화 이후는 사용가능.
		
		methodB(); //static이 붙으면 사용가능.
	}
}
