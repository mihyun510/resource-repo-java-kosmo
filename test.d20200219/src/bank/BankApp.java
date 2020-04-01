package bank;

public class BankApp {
	public static void main(String[] args) {
		Bank bank = new Bank();
		Bank bank2 = new Bank("자바맨","123-456-789", 10000);
		System.out.println(bank.toString());//예금주는null이고, 계좌번호는 null 잔액은0원입니다. 파라미터를 받는 생성자로 생성하지 않고 디폴트 생성자로 생성한 객체이기 떄문에 데이터가 들어가지 않고 출력된 것을 알 수 있다.
		System.out.println(bank2.toString());
		bank2.deposit(15000);
		bank2.take(30000);
		
	}

}
