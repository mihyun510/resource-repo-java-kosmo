package book.chap08;
//자동차 - 완전 추상적이다
public class Car {
	int speed;
	public Car() {
		speed = 0;
	}
	public void stop() {//모든 차는 멈추는 기능이 있다., 파라미터를 적어주지 않은 이유: 전변을 사용
		System.out.println("stop메소드 호출 성공");
		if(speed>0) {
			speed = speed-1;
		}
	}

}
