package friday0131;

import java.util.Random;
import java.util.Scanner;

public class BaseBallGame {
	
	int[] com = new int[3];
	int[] my = new int[3];//my[0]=0 my[1]=0 my[2]=0
	public void ranCom() {
		Random r =new Random();//0.0~
		
		com[0] = r.nextInt(10);//0.0~10.0
		do {
			com[1] = r.nextInt(10);//0.0~10.0
		} while (com[0]==com[1]);
		do {
			com[2] = r.nextInt(10);//0.0~10.0
		}while((com[0]==com[2])||(com[1]==com[2]));
	}
	//insert here - account method 구현////////////////////////////////////////////////////////////
	
	public String account(String user) { //왜 스트링을 리턴타입으로 해야하냐고.,.,. 내가 입력한값을 매개변수로 받음
										//왜 스트링이냐면 리턴을 "0스2볼"이런식으로 할 예정임..
		//int[] com = new int[3];
		int temp = Integer.parseInt(user);
		my[0] = temp/100;//123/100=1;
		my[1] = (temp%100)/10;//2
		my[2] = temp%10;//3
		
		
		for (int i = 0; i < my.length; i++) {
			my[i] = Integer.parseInt(user.substring(0, 1));
		}
		for(int me:my) {
			System.out.println("me:"+me);
		}
		
		int strike = 0;  //지역변수로 하는 이유 계속 변하는 값이기 때문
		int ball = 0;
		for (int i = 0; i < com.length; i++) {
			for (int j = 0; j < my.length; j++) {
				if( my[j]== com[j]) {//내가 입력한 숫자중에 컴터에 그 숫자가 있나?
					if(i == j) {//혹시 그 숫자가 자리도 일치하는거야?
						strike++;
					}//스트라이크 확보
					else {
						ball++;
					}
				}//볼카운트 확보
			}///end of in for
		}//end of out for
	
		return strike+"스볼"+ball+"볼"; //스트링타입!!!!!!!!!!!!!!!!!!
	}
		
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		BaseBallGame bbGame = new BaseBallGame();
		bbGame.ranCom();
		System.out.println(bbGame.com[0]+""+bbGame.com[1]+""+bbGame.com[2]); //중간에 ""을 쓰는 이유 덧셈연산을 방지하기 위해서, 숫자를 연결하기위해서
		System.out.println("게임이 시작되었습니다.");
		System.out.println("세자리 숫자를 입력하세요.:");
		Scanner sc =new Scanner(System.in);
		String user = null;
//		//result에는 정답입니다. 혹은 0스 2볼.......    필요없어짐
//		String result = bbGame.account(user);
//		System.out.println(user+":"+result);
		//for문을 사용해 봅시다.
		int cnt = 1;
		for (int i = 0; i < 9; i++) {
			user = sc.nextLine();
			System.out.println("사용자가 입력한 값은 "+user+" 입니다.");
			System.out.println(cnt++ + "회: "+user+"==> "+bbGame.account(user));
		}
	}
}
