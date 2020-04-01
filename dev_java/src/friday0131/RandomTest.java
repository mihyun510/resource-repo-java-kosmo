package friday0131;

import java.util.Random;

/*세자리 숫자를 채번해봐요
0부터 9사이의 숫자를 고르는 겁니다
만일 같은 숫자가 나오면 다시 뽑아야 해요

세자리 숫자를 어디에 담는게 좋을까요
배열이고, 타입은 int
int com[] = new int[3];

첫번째 숫자를 채번할 땐 반복할 필요가 없어요.
왜냐하면 중복검사를 할 대상 카드가 없기 때문이죠.

com[0]번에 담기는 숫자는 반복할 필요가 없다.
두번째 방과 세번째 방에 담기는 숫자는 같은
숫자를 채번할 수 있으므로 다시 채번해야 할
경우가 발생할 수 있을것이다.

첫번째 방(com[0])에 담긴 숫자와 
두번째 방(com[1])에 담길 숫자가 
같니?
네- 다시 채번하기
아니- 세번째 숫자를 채번하러 가자.

com[1]
com[2]

채번하는 코드는 어디에 작성하지?
//선언부 //화면처리부 //메인메소드 // 메소드영역
 
채번하는 메소드를 선언하기

재사용성을 늘린다. 2번호출이 가능하다 즉, n번 호출이 가능하다.

새게임 버튼 - 새로 채번한다.
게임 강제 종료 - 새로 채번한다.

*/
public class RandomTest {
	//세자리 숫자를 채번하는 메소드 입니다.
	//새게임 버튼을 누르거나 강제 종료 후 다시 시작할 때 호출됩니다.
	int[] com = new int[3];
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
	
	public static void main(String[] args) {
		RandomTest rt = new RandomTest();
		for (int i = 0; i < 10; i++) {
			rt.ranCom();
		}
		System.out.println(rt.com[0]+""+rt.com[1]+""+rt.com[2]); //중간에 ""을 쓰는 이유 덧셈연산을 방지하기 위해서, 숫자를 연결하기위해서
//		int i =3;
//		while(i<3) {
//			System.out.println("한번두 기회를...");
//		}
//		do {
//			System.out.println("무조건 한번은 실행");
//		}while(i<3);
		
	}
}
