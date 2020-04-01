package ocjp.control;
//객체지향적 언어 - JAVA
//절차지향적 언어 - C언어 - 순서대로 실행함
/*
 * 변수의 종류
 * 1. 원시형타입(Primative data type) - 부르면 값이 반환
 * 참조형 타입이 아니라서 변수나 메소드를 누릴 수 없다.
 * 2. 참조형타입(reference data type) - Sonata, String, Tivoli
 * 부르면 주소번지가 반환됨
 */
public class WrapperTest {
	int i;
	static void methodA() {
		//int i = new int(); 원시형 int는 인스턴스화가 불가능
		Integer i = new Integer(10);
		//오토박싱 개념추가
		//변수 i는 클래급이고 변수 j는 원시형 타입이라서 서로 급이 다름.
		//원래는 급이 다르면 대입 불가능 그러나 오토박싱은 가능하게 해줌.
		int j = i; 
		//오토박싱이 나오기 전에는 반드시 래퍼클래스 타입을 원시형 타입으로 바꾸어주는 
		//메소드를 태워야 했음.
		j = i.intValue();//intValue()는 Integer가 소유주, return 타입은 int
		//String s = i.intValue(); [오류발생] return타입이 int타입인데 String 타입으로 받음. 즉, 타입을 맞추지 않음.
		Boolean b = new Boolean(false);
		boolean b1 = b;
		String str = "false";
		Boolean b2 = Boolean.valueOf(str);//valueof(): Stirng 타입의 str을 Boolean타입으로 전환
		if(b2) {
			
		}
	}
	public static void main(String[] args) {
		//원시형 타입에는 8가지가 있다.
		//int, boolean, double
		//Integer, Boolean, Double Wrapper Class라고 함
		//원시형 타입인 int에도 메소드를 가지게 하고싶다.
	}
}
