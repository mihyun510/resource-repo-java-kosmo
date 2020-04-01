package ocjp.basic;
/*
 * TestParam.java소스안에 두 개 클래스를 가짐.
 * TestParam.java를 컴파일하면(고급언어->저급언어)
 * Param.class와 TestParam.class가 만들어짐
 * Param클래스에는 전역변수 ival이 있고 초기값은 현재 0임
 * 그 값이 testParam클래스의 main메소드에서 100으로 초기화됨.
 * effectParam안에서는 500으로 초기화 되었다.
 * 이 때 main ival이 먼저 출력되는가? 아니면 sub ival이 먼저 출력되는가? ===> sub ival이 먼 출력.
 * 둘 중에 먼저 출력된 값이 나중에 출력되는 곳에 영향을 미쳤다. 아니면 영향을 주지 않았다. 판단. ===> 영향을 미쳤다.
 * 
 * 두 번째 변화 주기
 * 21번 주석이 있을때와 없을때 결과에 차이가 있다.|없다. ===> 차이가 있다. effectParam()안으로 원본이 넘어갔지만 안에서 p가 다시 new생성자로 새로운 복사본을 생성함 그리고 그
 * 												복사본의 값을 500으로 변경시킴. main안에서는 원본이 변경이 안되고 100 그대로 남아있음.
 * 만약 차이가 있다고 생각한다면 어떤 차이가 어떻게 있는 것인지 옆사람에게 설명해 볼것.
 */
class Param{
	int ival=0;
}///end of Param
public class TestParam {
	void effectParam(Param p) {//원본을 받았으나
		p = new Param();//여기서 복제본 새로 생성됨
		p.ival = 500;//복제본이 500으로 변경
		System.out.println("sub ival===>"+p.ival); //sub ival ===> 500
	}
	/* 복제본이 없을 경우 경로
	 * 28(entry-point)-31(객체가 램에 상주하게됨:인스턴스화)-32(전역번수초기화-0)-33(100)
	 * 34(파라미터로 원본)-20(32번객체가리킴)-22(원본이 바뀐다)[0-100-500]-23-24-35
	 */
	public static void main(String[] args) {
		//내안에 있는 메소드를 호출하지만 static영역에서 non-static을 호출할때
		//반드시 인스턴스화
		TestParam tp = new TestParam();
		Param p = new Param(); //지역변수이지만 원본 변수 p의 주소번지
		p.ival = 100;
		tp.effectParam(p);//동일한 주소번지
		System.out.println("main ival ===> "+p.ival); //복제본이 없을 경우 main ival ===> 500, 복제본이 있을경우: 100
	}////end of main
}/////end of TestParam
