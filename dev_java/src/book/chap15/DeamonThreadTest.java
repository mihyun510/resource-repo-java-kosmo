package book.chap15;
/*
 * 상황에 따라 분리된 스레드로 백그라운드 작업을 해야 하는 경우가 있다.
 * JVM안에 가비지 컬렉션과 같은 작업이 대표적이다.
 * 이런 백그라운드 작업이 일반 스레드로 설정되어 있다면 전원이 종료되거나 사용자가 강제 종료
 * 하지 않는 한 애플리케이션은 영원히 정지 하지 않을 것이다.
 * 
 * 데몬스레드 모든어플이 종료되지않아도 정상적으로 종료된것처럼 할 수 있다.
 */
public class DeamonThreadTest {
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
		//th의 스레드를 종료함 run()메소드를 실행시키기 전에.
		th.setDaemon(true); //데몬 같은 경우는 main이 끝나자 마자 바로 종료됨을 확인할수있다. 즉, "INNER Thread 종료"가 출력되기도 전에 main에서 스레드를 종료
		th.start();
		System.out.println("main종료");
	}
}
