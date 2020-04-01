package book.chap05;

public class TelBookSimulation extends Object{

	public String toString() {
		return "나는 TelBook클래스임.";
	}
	public static void main(String[] args) {
		TelBook tb = new TelBook();
		System.out.println(tb);
		//toString메소드는 내 안에 없는 메소드 이지만 모든 클래스의 부모클래스인 
		//Object안에 정의된 메소드임
		//부모 클래스인 Object를 상속받고 있으므로 자식 클래스에서 부모클래스 자원을 사용가능 
		//아빠가 가지고 있는 자원을 내가 다시 제정의해서 사용
		System.out.println(tb.toString()); //정의되지 않았을떄는 이상한 주소 나옴 그러나 정의 후에는 정의된 문구가 나옴.
	}
}
