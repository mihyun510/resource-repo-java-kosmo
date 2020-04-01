package oracle.jdbc2;

import java.util.Vector;

public class ZIpCodeList {
	
	public static void main(String[] args) {
		//Vector<Object> v2 = new Vector<Object>();
		Vector v2 = new Vector(); //제네릭을 생략한 경우, Object타입과 동일하다.
		v2.add("사과");
		v2.add("딸기");
		v2.add(1,"바나나");
//		for (int i = 0; i < v2.size(); i++) {
//			//Stirng f = v2.get(i);//타입이 Object라서 담을 수가 없어요. 타입이 맞지 않죠.
//			//Object f = v2.get(i);
//			String f = (String)v2.get(i);
//			System.out.println("v : "+v2.get(i));
//		}
		Vector<String> v = new Vector<String>(); //<String> : Stirng 타입의 배열이므로 String 타입의 결과를 반환가능.
		v.add("사과");//0번방에 사과추가
		v.add("딸기");//1번방에 딸기추가 문자열 추가
		v.add(1, "바나나");//끼워넣기, 1인덱스에 바나나를 끼어든다. 원래있던 값은 뒤로 밀려난다.
		v2.remove(2);
		for (int i = 0; i < v.size(); i++) {
			String f = (String)v2.get(i);
			System.out.println("v : "+v.get(i));
		}
	}
}
