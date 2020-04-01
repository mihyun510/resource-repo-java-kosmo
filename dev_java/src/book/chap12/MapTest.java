package book.chap12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
 * 인터페이스는 단독으로 인스턴스화 할 수가 없다.
 * 반드시 구현체클래스가 있어야한다.
 * 컬렉션프레임워크에서 제공되는 모든 클래스는 객체타입만 담을 수 있다.
 */
public class MapTest {
	Map<String, Object> pMap = new HashMap<>(); 
	public void showAllDept2() { //showAllDept의 코딩을 간결하게 줄여본 메소드
		Object keys[] = pMap.keySet().toArray(); //key의 값을 배열로 받아보자. toArray() Key의 값을 배열로 반환. - (받을 타입)
		for (int i = 0; i < keys.length; i++) {
			String key = (String)keys[i];
			System.out.println(key +", "+pMap.get(key));
		}
	}
	public void showAllDept() {
		//Map에서 제공하는 주소번지가 필요하다.....?
		Iterator<String> ir = null; //Map에 담긴 정보를 꺼내는데 필요한 메소드를 제공함.
		Set<String> keys = pMap.keySet(); //keySet이 Set타입이니까 
		ir = keys.iterator();
		int deptno = 0;
		String dname = null;
		String loc = null;
		while(ir.hasNext()) {
			//┌> key값이 String이다
			String key = ir.next();//deptno, dname, loc --> 검사해서 날올 키의 값이 String타입이다.
			//┌> deptno가 int 타입이라서  String 타입을 Integer로 형변환 시켰다.
			if(pMap.get(key) instanceof Iterator) {
				deptno = Integer.parseInt(pMap.get(key).toString());
			}
			//┌ 위에 if문에서 pMap.get(Key)값을 Integer타입의 변수로 받기 위해서 Integer형식으로 형변환을 했으니 
			//│ 다시 String 타입의 변수를 받기위해서 String타입으로 형변환을 해주어야된다. 
			//v deptno 키의 해당하는 0 정수형이다. dname과 loc 키의 해당하는 value는 String이다.
			if(pMap.get(key) instanceof String) {
				if("dname".equals(key)) { //key의 값이 dname이면
					dname = pMap.get(key).toString(); //dname의 해당하는 value의 값을 가져와서 dname변수에 넣어주자.
				}else if("loc".equals(key)) {
					loc = pMap.get(key).toString();
				}
			}
		}///////////end while
		System.out.println(deptno+", "+dname+", "+loc);
	}///////////////end showAllDept
	public static void main(String[] args) {
//		Map<String, Object> pMap = new Map<>(); //Map도 인터페이스라서 단독으로 인스턴스화가 불가능하다.
//		Map<String, String> pMap = new HashMap<>(); //Map도 인터페이스라서 단독으로 인스턴스화가 불가능하다.
		//  └>키의 타입 └>값의 타입 : 값의 타입을 Object로 놓은것은 pMap에 추가할 값이 정수형도 있고 String형도 있기때문이다. Object가 모든 타입을 포용하기 때문이다. 
		MapTest mt = new MapTest();
		mt.pMap.put("deptno", 10); 
		mt.pMap.put("dname", "ACCOUNTING");
		mt.pMap.put("loc", "NEW YORK");
		mt.showAllDept();
//		System.out.println(mt.pMap.get("deptno")); //키값을 가지고 구분하기 때문에 키값을 다 찍어보자 
//		System.out.println(mt.pMap.get("dname")); //키값을 가져와서 출력해보면 그 키에대한 값을 보여준다.
//		System.out.println(mt.pMap.get("loc")); //키값을 가지고 구분하기 때문에 키값을 다 찍어보자
		
		
	}
}
