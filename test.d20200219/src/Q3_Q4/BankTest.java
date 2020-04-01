package Q3_Q4;

public class BankTest {

	public static void main(String[] args) {
		Bank bankCostom = new Bank("자바맨","123-456",10000);
		
		System.out.println("--------입금----------");
		//예금하는 함수 호출
		bankCostom.deposit(15000);
		
		System.out.println("---------인출---------");
		//인출하는 함수 호출
		bankCostom.withdrow(30000);
	}
}
