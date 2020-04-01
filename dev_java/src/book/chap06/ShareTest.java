package book.chap06;
/*
 * 변수 2개 선언
 */
class STest{//선언과 초기화
	int i = 1; //non-static타입
	static int j = 2; //static타입
}
/*
 * 메소드 한개 선언 - methodA()
 * STest객체를 메모리에 로딩하였다.-인스턴스화
 * STest클래스의 변수 , i를 1증가시킴-초기화 했다.
 * 정적변수인 j를 1증가시킴.
 */
class STest2{//변조
	void methodA() {
		STest st = new STest();
		st.i = st.i+1;//2 //i는 static이 없으므로 다른 클래스에서 사용하려면 인스턴스화를 통해서 사용가능
		STest.j = STest.j+1;//3 //j는 static이 붙었으므로 인스턴스화 없이 클래스명으로 변수를 사용가능
		//st.i:2 , j:3
		System.out.println("methodA ==> st.i:"+st.i+" ,STest.j:"+STest.j);//3
	}
}
//관찰하기
public class ShareTest {
//28-29(먼저생성-원본)-30-31-16-17(복사본)
	public static void main(String[] args) {
		STest st = new STest();
		STest2 st2 = new STest2();
		st2.methodA();
		st.i +=3;//1->4 17번에서 생성된 객체가 아니라 29번에서 생성된 객체를 가리킴
		STest.j=5;
		System.out.println("st.i:"+st.i+" ,STest.j:"+STest.j);//3
	}
}
