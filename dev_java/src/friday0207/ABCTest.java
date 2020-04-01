package friday0207;
//클래스 A, B, C에는 main메소드가 없다.
class A{
	//B b = new B(); //A클래스에서 B클래스 사용가능 , 11번 호출됨. [B > A > B > A > B...반복 StackOverflowError]
	String name;
	public A() {
		System.out.println("디폴트 A생성자");
	}
	A(String name){
		System.out.println("파라미터가 name인 A생성자");
		this.name = name;
	}
	A(ABCTest abc){
		System.out.println(abc+"파라미터가 ABCTest타입인 A생성자");
	}
}
class B{
	A a = new A(); //B클래스에서 A클래스 사용가능
	public B() {
		System.out.println("디폴트 B생성자");
	}
}
class C{
	A a = new A(); //C클래스에서 A클래스와 
	B b = new B(); //                 B클래스 사용가능
}
public class ABCTest {
	//22-23-3-6-
	//클래스 안에디폴트 생성자를 생략할 수도 있고 명시적으로 선언할 수도 있다.
	public ABCTest() { //ABCTest의 생성자, 함수는 리턴타입이 있음
		A a1 = new A(this); //this = ? - ABCTest
	}
	public static void main(String[] args) {
		A a1 = new A("이순신"); //디폴트 A생성자 호출 그리고 heap메모리에 로딩됨. 로딩되는 순간 B b = new B()가 메모리에 로딩되면서 인스턴스화 그럼 B 클래스 로딩되는 순간 A a = new A()가 메모리에 로딩되면서 인스턴스화 그리고 다시 새로운 주소의 A클래스 로딩.....반복
	}
}
