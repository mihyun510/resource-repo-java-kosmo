package basic.input;

public class CountinueKeyCode {
	
	public static void main(String[] args) throws Exception{
		int keyCode;
		while(true) {
			keyCode = System.in.read(); 
			System.out.println("Keycode : "+keyCode);//줄바꿈 후 커서가 두번째줄의 커서가 맨앞에있는 이유는 ln임 줄을 바꺼서 맨앞으로 보냄
			if(keyCode == 113) { //숫자는 아스키코드값으로 구분함 113번의 알파벳(q)을 누르면 keyCode에서 충족
				break; //와일문에 대한 탈출
			}
		}
		System.out.println("종료"); // if문을 실행하면 와일문을 탈출하고 출력문이 실행
	}

}
