package ocjp.basic;

public class ArrayTest1 {
	static void methodA(int[] is) {//메소드 선언하기
		System.out.println("is(주소번지):"+is);//hot spot - I@15db9742
		//복제본이 아닌 원본을 변경한것. 원본의 주소값을 매게변수로 받아옴.
		//파라미터로 넘어온 is배열의 원본에 세번째 방에 있는 값을 0에서 10으로 오버라이트
		is[2] = 10;
	}
	
	public static void main(String[] args) {
		//배열 선언과 생성하기 - 방이 3개 만들어짐
		//변수 is는 배열타입이고 배열의 변수명이다.
		int[] is = new int[3]; //is[0]=0, is[1]=0, is[2]=0
		//배열의 주소번지 출력해보기 I@15db9742
		System.out.println("main is:"+is);//hot spot
//		ArrayTest1 a1 = new ArrayTest1();
//		a1.methodA(is);
		
		//methodA(is)호출할 떄 is배열의 주소번지(I@15db9742)를 메소드의 파라미터로 넘겨줌
		//이 메소드에서 is[2]방에 0값 대신 10으로 재정의함.
		//is의 주소번지는 I@15db9742
		methodA(is);
		
		//for(초기화; 조건식; 증감연산자)
		for(int x=0; x<3; x++) {
			System.out.println("is["+x+"]="+is[x]);
		}
		System.out.println("======================================");
		
		for(int x=0; x<3; ++x) { //0도 출력되는 이유는 초기값으로 0을 설정 했기때문 그래서 일단 for문 실행후 증감영향미침
			System.out.println("is["+x+"]="+is[x]);
		}
		System.out.println("======================================");
		
		//개선된 for문 - 배열에 있는 모든 정보를 다 출력할때 for(옮겨질변수 : 옮길변수)
		for(int a:is) {
			System.out.println(a);
		}
	}

}
