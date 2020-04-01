package book.chap09;
//인터페이스는 추상메소드만 갖는다
//그래서 abstract 생략가능
public interface FlyBehavior {
	
//	FlyBehavior(){} 생성자 불가능
//	public void fly() 일반메소드 선언 불가능 사용 불가
	public abstract void fly(); //추상메소드 abstract 예약어 생략가능 왜냐하면 인터페이스는 추상메소드만 가질 수 있음 
}
