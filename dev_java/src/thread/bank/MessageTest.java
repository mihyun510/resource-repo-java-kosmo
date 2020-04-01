package thread.bank;

import java.util.StringTokenizer;

public class MessageTest {
	public static void main(String[] args) {
		String msg = "200#apple#test#오늘 스터디 할까?";
		StringTokenizer st = null;
		if(msg != null) {
			st = new StringTokenizer(msg,"#");
		}
		System.out.println(st.nextToken());//#기준으로 문자열을 자른다. 그래서 출력할때 마다 #앞뒤로 잘린 문자열 한개씩 출력됨.
		System.out.println(st.nextToken());
		System.out.println(st.nextToken());
		System.out.println(st.nextToken());
//		System.out.println(st.nextToken()); 다섯번째는 구분자가 없으니 오류발생.
		//앞에 생성된 StringTokenizer는 이미 출력되어 while문으로 출력이 안됨.
		//다시 생성해주자.
		if(msg != null) {
			st = new StringTokenizer(msg,"#");
		}
		while(st.hasMoreElements()) {
			System.out.println(st.nextElement());
		}
		//앞에 출력된 것은 뒤에 또 출력해도 출력이 되지 않는다.
		//그러니 다시 생성 후 출력해주어야 2번 출력됨을 알 수 있다.
		if(msg != null) {
			st = new StringTokenizer(msg,"#");
		}
		for(;st.hasMoreTokens();) {
			System.out.println(st.nextToken());
		}
	}
}
