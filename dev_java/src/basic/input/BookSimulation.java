package basic.input;

class Book{
	String title = null;
	int price = 0;
	
	public static Book getBook() {
		return new Book(); //생성자를 리턴. 인스턴스화기능 메소드
	}
}
public class BookSimulation {

	public static void main(String[] args) {
		Book myBook = new Book(); //표준은 이렇게 인스턴스화
		//메소드를 호출해서 객체를 주입 받을 수도 있다. -이런 느낌.
		Book gnomBook = Book.getBook(); // 인스턴스화 한것과 동일 한 효과를 받음.
		System.out.println(myBook+" , "+gnomBook);
	}
}
