package friday0131;

public class BreakTest {
	
	public static void main(String[] args) {
		for (int i = 1; i < 4; i++) {
			if(i==2) {
				break;
				//System.out.println("break다음...."); 에러발생 break 다음에는 문장을 쓸수없음.
			}
			System.out.println("여기...."); //i가 1일때는 출력하므로 한번 출력후 for문 탈출
		}
		System.out.println("for문 밖....");
	}
}
