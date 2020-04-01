package book.chap09;
//상속을 하면 결합도가 높아진다.
//상속을 하면 독립적이지 않다.
//상속을 하면 재사용성이 떨어진다.
//상속은 재사용성을 높이는 모범 답안일 수 없다.
//결론
//인터페이스 중심의 코딩을 전개 한다.
public class Airplane extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Airplane ap = new Airplane();
		Duck ap2 = new Airplane(); //오리와 비행기는 상관이 없즤 않아..?
		ap.fly(); //오리는 날 수 있어요. -> 결합도가 높다. 오리가 나는 것과 비행기가 나는 것은 달라요. ->상속이 안되는 이유...
	}
}
