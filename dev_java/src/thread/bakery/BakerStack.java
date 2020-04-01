package thread.bakery;

import java.util.Vector;

public class BakerStack {
	private Vector<String> breads = new Vector();
	//빵을 가져가는 메소드
	public synchronized String pop() {
		String bread = null;
		
		while(breads.size()==0) {
			try {
				System.out.println("빵이 곧 만들어 집니다. 잠시만 기다려주세요.");
				this.wait(); //스레드를 잠시 멈춤.
				
			} catch (InterruptedException e) {
				System.out.println("여기...새치기 일어남."); //인터셉트가 일어난 경우에 나타나는 메세지
			}
		}
			
		bread = breads.remove(breads.size()-1);
		return bread;
	}
	
	//빵을 진열하는 메소드
	//synchronized - 자신의 순서를 절대 새치기 당하지 않고 제 순서에서 일을 처리하기가 가능
	public synchronized void push(String bread) {
		System.out.println("오래기다리셨습니다. 빵을 가져가세요.");
		this.notify();//잠자는 스레드를 다시 깨운다.
		breads.add(bread);
	}
	
}
