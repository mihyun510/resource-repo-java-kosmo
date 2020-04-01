package book.chap05;
/*
 * 쿼리문을 작성할 때 String을 사용하면 안되는 이유에 대해 말할 수 있다.
 * String은 원본이 절대로 바뀌지 않는다.
 * 따라서 변경하려면 반드시 새로운 변수에 그 값을 다시 담아야 한다.
 * 이렇게 되면 같은 이름의 변수가 n번 만큼 생성되므로 비효율적이다.
 * 따라서 이럴때는 String Builder를 사용한다.
 */
public class StringTest {
	//주소번지를 비교한다.
	//주소번지가 가리키는 값을 비교한다.
	public static void main(String[] args) {
		String s1 = "apple";
		String s2 = "apple";
		String s3 = new String("apple");
		String s4 = new String("apple");
		String s5 = new String();
		//s1과 s2가 가리키고 있는 값이 같음. 이 값들은 상수풀에 저장된 상태
		System.out.println(s1 == s2); //true, false? ------- true 값을 비교함. 이미 만들어진 값은 다시 주소를 생성하지 않고 그 값을 참조한다. 즉, 주소가 같아진다. new을 하지않고 초기화한 값은 상수풀이라는 데이터메모리에 들어가므로 다 같은 상수풀의 값을 참조하므로 주소가 같다.
		//주소번지가 같니? -- new을 하는 순간 다른 주소값을 가지게 됨 즉, 주소를 비교하였으니 false가 반환
		System.out.println(s3 == s4); //true, false? ------- false 주소를 비교함.
		// 그 주소번지가 가리키는 값이 같니? -- 주소는 다르나 주소가 가리키는 값들은 같음
		System.out.println(s3.equals(s4)); //true, false? -- true equals()함수는 주소번지에 해당하는 값을 비교함.

		System.out.println(s1 == s3); //주소를 비교 s1과 
		System.out.println(s1.equals(s3));
	}

}
