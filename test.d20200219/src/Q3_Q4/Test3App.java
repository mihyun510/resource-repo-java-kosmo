package Q3_Q4;

public class Test3App {
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		System.out.println(t3); //Q3_Q4.Test3@15db9742 - toString()을 재정의 하지 않았을 경우 주소번지가 찍힘
								//나는 Test3클래스 입니다. - toString()을 재정의 했을 경우 오버라이드된 toString이 나옴.
		
	}
}
