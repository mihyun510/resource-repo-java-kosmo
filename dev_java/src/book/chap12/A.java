package book.chap12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class A extends B {
	//클래스 A에서 메소드 4개를 호출해보자.
	public static void main(String[] args) {
		B b = new B();
		List<String> list = new ArrayList<String>(); //싱글스레드안전 - 동기화구현이 안되어 있다.
													 //벡터보다 속도가 빠르다.[동기화를 안하기때문]
													 //여러개의 프로세스가 실행되면 뺏길 수 있음[순서가 밀릴 수 있다.] - 멀티에서 안전하지않다.
													 //동기화: 프로세스가 어떤 일을 할때 그 일을 다른 프로세스가 맞추는 것임.
		List<String> list2 = new Vector<String>();   //멀티스레드안전 - 동기화를 구현한다. 속도가 느리다.[경합이 벌여진다. ex) 도서관, 채팅방, 단톡방, 사람관리]
		ArrayList<String> nameList = new ArrayList<String>();
		Vector<String> mailList = new Vector<String>();
		b.methodA(nameList); //무조건 ArrayList타입의 methodA()함수로 간다. why? 매개변수 nameList인데 이것이 ArrayList타입이기 때문에 
							 //만약 ArrayList타입의 함수가 없다면 그 상위타입인 List를 매개변수로 받는 함수로 간다.
							 //만약 List타입의 합수가 없다면 그 상위타입인 collection를 매개변수로 받는 함수로 간다.
							 //collection(I) <-- List(I) <-- ArrayList(구현class)
		
	}

}
