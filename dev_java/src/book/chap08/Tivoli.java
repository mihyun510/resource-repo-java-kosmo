package book.chap08;

public class Tivoli extends Car{
	String carColor;
	int volumn;
	//생성자는 전.변을 초기화해준다.
	public Tivoli() {
		carColor = "남색"; //생성자 안에서 전역변수 초기화를 해줌.
		volumn = 0; //처음에 출시된 차의 볼륨은 0이겠죠..?
	}
	@Override
	public void stop() {
		System.out.println("ovveride 호출 성공");
		if(speed > 0) {
			speed -= 2;
		}
	}
	public void volumup() {
		volumn += 1;
	}
	public void volumdown() {
		volumn -= 1;
	}

}
