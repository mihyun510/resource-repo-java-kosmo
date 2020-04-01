package book.chap15;

public class StopThread implements Runnable{

	boolean stopped = false;
	
	@Override
	public void run() {
		while(!stopped) {
			System.out.println("Thread is alive.....");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread is dead...");
	}
	
	public void stop() {
		stopped = true;
	}

	
}
