package book.chap15;

public class StopThreadTest2 {
	public void process() {
		StopThread2 st = new StopThread2();
		Thread th = new Thread(st); //자동차안에 내비게이션을 가지게 되잖아? 그리고 네비게이션을 켰을때 스레드가 동작하잖아? 
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th.interrupt(); //인터럽트를 실행하자.
	}
	public static void main(String[] args) {
		StopThreadTest2 stt = new  StopThreadTest2();
		stt.process();
	}
}
