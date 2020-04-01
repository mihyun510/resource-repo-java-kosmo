package Q3_Q4;
/*
 * 3. 다음 요구사항을 만족하는 은행 계좌 클래스를 정의하여 소스코드와 결과화면 
  스크린 샷을 제출하시오
   [요구사항]
   가. 속성으로 예금주,계좌번호,잔액을 갖는다.
      예금주는 String,계좌번호 String,잔액은 int로 정의하여라
   나. 메소드로 인출 및 입금 메소드를 갖는다.
       인출 메소드는 인자로 인출할 금액을 받고 잔액이 부족시에는
       “잔액이 부족합니다”라는 메시지를 잔액이 충분할때는 인출한 금액을 출력하는 
       메소드이다
       입금 메소드는 인자로 입금할 금액을 받고 입금액을 잔액에 더하고
       입금한 금액을 출력하는 메소드이다
   다. toString()메소드를 오버라이딩하여 계좌정보 즉 얘금주,계좌번호,잔액을
      출력하는 메소드를 정의하여라
      예]예금주:자바맨,계좌번호:123-456,잔액:1000원
 */
/******************************************************************
 * 계좌번호 String -> 000-000-000 '-'때문이다. 만약 '-'이 없다면 int 
 * int 형이 검색속도가 빠르다.
 * 생성자로 전역변수를 초기화하기. [매우중요] 
 * 클래스 안에다가 main메소드 사용하지 않기 - 다른 테스트 클래스를 만들어서 main사용하기
 * 
 *
 *******************************************************************/
public class Bank {
	
	//가.
	String name = null;
	String account = null;
	int cash = 0;
	
	//생성자
	public Bank() {};
	public Bank(String name, String account, int cash) {
		this.name= name;
		this.account = account;
		this.cash = cash;
	}
	
	//나. - 인출 메소드
	void withdrow(int money) {
		if(cash >= money) {
			cash -= money;
			System.out.println("인출한 금액은 : "+ money);
			System.out.println("잔액은 :"+cash);
		}
		else {
			System.out.println("잔액이 부족합니다.");
		}
	}
	
	//나. - 입금 메소드
	void deposit(int money) {
		cash += money;
		System.out.println("입금한 금액은 : "+ money);
		System.out.println("잔액은 :"+cash);
	}
	
	//toString 재정의
	public String toStirng() {
		return String.format("예금주: %s, 계좌번호: %s, 잔액: %s%n"
							,name,account,cash);
	}
	
	public static void main(String[] args) {
		Bank bankCostomTest = new Bank("자바맨","123-456",10000);
		System.out.println(bankCostomTest.toStirng());
	}

}
