package book.chap15;
/*
 * 자바에서는 애플리케이션 내부의 모든 스레드가 종료되지 않으면 JVM이 종료되지 않는다.
 * main메소드의 스레드가 종료되었지만 중간에 생성한 스레드가 종료될 때 까지 
 * 빨강 버튼은 비활성화 되지 않고 있을 것이다.
//데몬스레드 모든어플이 종료되지않아도 정상적으로 종료된것처럼 할 수 있다.
 */
public class NormalThreadTest {
	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
				try {
					//5초가 될때까지 NormalThreadTest객체는 종료되지 않는다.
					Thread.sleep(5000); //milisecond 5초 정지 ------------> thread th의 run메소드 안에 thread.sleep()으로 새로운 메소드를 접근하여 sleep을 했으므로 thread는 2개가 되는 것이다.
					System.out.println("INNER Thread 종료");  //스레드를 시작하고 5초후에 run()안에 스레드가 종료된다.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		th.start();
		System.out.println("main종료");
	}
}
