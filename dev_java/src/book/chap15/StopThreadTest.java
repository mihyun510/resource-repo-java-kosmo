package book.chap15;

public class StopThreadTest {
	
	public void process() {
		StopThread st = new StopThread();
		Thread th = new Thread(st); //자동차안에 내비게이션을 가지게 되잖아? 그리고 네비게이션을 켰을때 스레드가 동작하잖아? 
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		st.stop();
	}
	
	public static void main(String[] args) {
		StopThreadTest stt = new  StopThreadTest();
		stt.process();
	}
}
