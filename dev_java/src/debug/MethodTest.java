package debug;
/*
 * 1.변수, 연산자, 제어문
 * 2.배열, 클래스
 * 3.클래스 쪼개기
 * 4.MVC패턴을 적용한 프로그램 작성
 * 5.JDBC연동하기
 */
public class MethodTest {
	//선언부
	int i = 10;
	boolean isOk = false;
	//생성부
	public MethodTest() {
		// TODO Auto-generated constructor stub
	}
	//메소드 선언부
	void methodA() {
		i = 30;
		i = i+10;
 		System.out.println("i : "+i);
  	}
	public boolean methodB(int a, int y) {
		if(a>y) {
			isOk = true;
		} else {
			isOk =false;
		}
		return isOk;
	}
	//메인메소드
	public static void main(String[] args) {
		MethodTest mt = new MethodTest();
		mt.methodA();
	}
}
