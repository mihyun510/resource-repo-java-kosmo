package thread.bakery;

public class Customer extends Thread{
	private BakerStack bs = null;
	
	public Customer(BakerStack bs) {
		this.bs = bs;
	}


	@Override
	public void run() {
		String bread = null;
//		for (int i = 0; i < 5; i++) {
//			//빵주세요.
//			bread = bs.pop();
//			//고객이 시간 빵이름 출력
//			System.out.println("손님이 가져가는 빵"+bread);
//			try {
//				sleep(2000);
//			} catch (InterruptedException e) {
//				System.out.println(e.toString());
//			}
//		}
		
		
		while(true) {
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
			bread = bs.pop();
			System.out.println(this+"번재 손님이 가져가는 빵은 "+bread);
		}
	}
}
