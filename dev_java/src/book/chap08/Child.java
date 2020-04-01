package book.chap08;
//child에서는 파라미터 두 개짜리 메소드는 재정의하지 않음.
//아빠가 가진 메소드에 대해서만 재저의 함.
public class Child extends Parent{
//	private String book = null; Parent를 상속받았으니 더이상 필요없는 변수
	public String car = null;
	public Child() {
		System.out.println("Child 디폴트생성자 호출 성공");
	}
	//메소드는 오버라이딩은 부모의 메소드를 재정의하는 것이다.
	//선언부는 반트시 일치해야한다.(리턴타입과 파라미터)
	@Override //부모의 함수를 재정의하는 함수는 기능만 재정의가능. 타입이나 매개변수 변경이 불가 
	public void bookRead() {
		System.out.println("1달에 3권씩 책을 읽습니다.");
	}
	//나는 오버라이드가 아닙니다. 왜냐하면 부모에겐 없는 메소드 이니까.
	public void carDriver() {
		System.out.println("파주아울렛으로 쇼핑을 갑니다.");
	}
}
