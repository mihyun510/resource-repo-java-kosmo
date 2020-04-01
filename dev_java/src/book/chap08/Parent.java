package book.chap08;

public class Parent {
	public String book = null;
//	private String book = null; 
	public Parent() {
		System.out.println("Parent 디폴트생성자 호출 성공");
	}
	
	public void bookRead() {
		System.out.println("1달에 2권씩 책을 읽습니다.");
	}
	//파라미터나 리턴타입이 다르다면 같은 함수를 사용할 수 있습니다.
	//메소드 오버로딩
	//반드시 파라미터의 갯수나 파라미터의 타입이 달라야한다.
	public void bookRead(String book1, String book2) {
		System.out.println("1달에 2권씩 책을 읽습니다.");
		System.out.println("이달에는 " + book1 +"과 "+book2+"를 읽었습니다.");
	}
}
