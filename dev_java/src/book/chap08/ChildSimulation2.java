package book.chap08;

public class ChildSimulation2 {
	//상속관계를 이용하면 재사용성이 높아지므로 코딩량이 줄어든다.
	//코딩량이 줄어야 오타 발생도 줄어들 것 아닌가...
	//가능하면 업무에 내용이 변경된다. 하더라도 가능한 코드를 적게 수정하도록 클래스 설계를 하여야 한다.
	public static void main(String[] args) {
		int i = 5;
		double d = i;
		Parent p = new Parent();
		Child c = new Child();
		
		//두개의 상황이 뭐가 다른 것일까? 왜 c=p는 에러가 발생하고 p=c는 발생하지 않는 것일까?
		//오른쪽 타입이 왼쪽 타입보다 상위클래스가 올 수 없다.
		//항상 하위클래스만 가능하다.
		p=c; // p = new Child(); 부모가 자녀의 타입을 생성할 수는 있다.
		//insert here 만일 p로 bookRead메소드를 호출하여다면 어떤 클래스의 메소드를 호출되었을까?
		//13번 라인에서 원래는 Parent객체를 가리켰으나 Child타입으로 변경되었다.
		p.bookRead(); //13번을 거치고 나면 child()로 다시 생성되었으므로 자식 타입으로 호출됨
//		c=p; // c = new Parent(); 자녀가 부모의 타입을 생성할 수는 없다.
	}
}
