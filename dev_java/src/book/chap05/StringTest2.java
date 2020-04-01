package book.chap05;

public class StringTest2 {
	public static void main(String[] args) {
		String s1 = "apple";
		s1 = s1.replace('p', '1');
		System.out.println(s1); //a11le
		//s1 두개가 만들어져요. 그러니까 같은 타입의 변수 두개를 관리한다. 리턴받는 변수와 리턴 받기전의 변수가 다름을 의미
		StringBuilder sb = new StringBuilder("hello");
		sb.append(" world");//원본에 붙여쓰기를 한 경우에 해당되므로 메모리 사용을 절약할 수 있다. 원본이 바뀌지 않는다.
		System.out.println(sb.toString());
	}
}
