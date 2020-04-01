package book.chap15;
//실무에서 사용하는 thread종료를 해보자.
public class StopThread2 implements Runnable{

	boolean stopped = false;
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) { //스레드가 혹시 방해를 받고있지 않니? - 그럼 종료하자.
			System.out.println("Thread is alive.....");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Thread is alive.....");
			}
		}
		System.out.println("Thread is dead...");
	}
	
}
