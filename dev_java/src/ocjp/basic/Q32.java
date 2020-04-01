package ocjp.basic;

public class Q32 {
	int x; //전역변수(class 바로 아래 선언)
	
	void methodA() {
		int z; //지역변수
	}
	
	boolean check() { //3번, 메소드 선언하기
		x++; //(x=x+1)
		return true;
	}
	
	void zzz() { //2번
		x = 0;
		//z = 0; z는 methodA()함수 안에 선언된 지역변수이므로 zzz()안에서 선언없이 사용이 불가능
		//앞에 괄호에서 결과가 true이므로 뒤에 명제가 참이든 거짓이든 상관없이 실행문은 반드시 실행됨.
		if((check() | check()) || check()) { // (true | true)=true || 실행안함, x=2
			x++; //(x=x+1), x=3, 현재 변수 x값은 유지되고 있다. 
	}
	 System.out.println("x = " + x); //4번
	} 
	
	public static void main(String[] args) {
		new Q32().zzz(); //1번
		
	}

}
