package bank;

public class Bank {
	
	//밑의 name과 name2의 차이는 무엇일까..? 
	String name = null;//예금주
//	String name2 = "";
	
	String account = null; //계좌번호
	int cash = 0; // 잔액
	
	//생성자
	public Bank() { //디폴트 생성자
		
	}
	
	public Bank(String name, String account, int cash) { //전역변수를 초기화하기 위한 생성자.
		this.name = name;
		this.account = account;
		this.cash = cash;
	}
	
	public void deposit(int money) {//입금하기
		cash = cash+money;
	}
	public void take(int money) {//얼마를 가져갈 거니? - 파라미터 [요청] *****무조건 요청다음에 응답return이므로 파라미터 에 값이 넘어오는지 확인하기
		if(cash >= money) {
			cash = cash - money;
		}else if(cash < money) {
			System.out.println("잔액이 부족 합니다.");
			return; //if문 안에다가 retrun쓰기를 잊지 말긔.. 잔액이 부족한테 잔액에서 받은 돈을 뺄 수 없다. 그러니 함수를 탈출하고 부족하다면 것을 알려주자.,.!!
		}
	}
	public String toString() {
		String accountINFO = "예금주는"+ name +"이고, 계좌번호는 "+ account +" 잔액은"+ cash +"원입니다.";
		return accountINFO;
	}
	
	public void print() {
		System.out.printf("예금주는 %s 이고, 계좌번호는 %s 잔액은 %d원입니다.",name, account, cash);
	}
	
	
}
