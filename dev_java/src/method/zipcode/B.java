package method.zipcode;

public class B {
	public static void main(String[] args) {
		int i = 0;
		int j = 0;
//		i = 100/0;//문법적으로 오류는 안나지만 계산을 할 수 없음. 이럴때 예외처리를 해야됨. [오류발생]계산할 수 없으니 중간에 멈춤
		
		try { 										// 중간에 문제가 있더라도 그냥 끝까지 감 그러나 예외를 잡아주고 감
			//만약 예외가 발생하지 않으면 없는 것과 동일한 실행
			
			//i = 100/0;//예외가 발생할 가능성이 있는 코드를 작성함.
			
			i = 100/j; //j에도 0이 들어오는 것을 주의!!
		} catch (Exception e) {
			System.out.println("0으로 나눈값은 계산할 수 없잖아.");
		}
		System.out.println("변수i는 "+i);
	}

}
