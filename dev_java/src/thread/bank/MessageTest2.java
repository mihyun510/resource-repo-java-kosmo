package thread.bank;

import java.util.StringTokenizer;

public class MessageTest2 {
	public static void main(String[] args) {
		String msg = "200#apple#오늘 스터디 할까?";
		msg = "210#apple#오늘 수업 끝나고 농구할까?";
		msg = "100#apple";//입장하기
		StringTokenizer st =null;
		if(msg != null) {
			st = new StringTokenizer(msg, "#");
			
		}
		int protocol = 0;
//		protocol = Integer.parseInt(st.nextElement().toString()); 형변환 번거롭다. 스트링 -> 오브젝트 -> 스트링
		protocol = Integer.parseInt(st.nextToken());
		//msg가 마지막에 100#apple값을 가지고 있으므로 100인 경우를 실행한다.
		switch (protocol) {
		case 100:
			System.out.println("100일때 해야 할 일");
			break;
		case 200:
			System.out.println("200일때 해야할 일");
			break;
		case 210:
			System.out.println("210일때 해야할 일");
			break;
		}
	}
}
