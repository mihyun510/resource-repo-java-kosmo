package ocjp.basic;

import java.util.Scanner;

/*0~9사이의 숫자 중 임의수 선택하여 사용자가 3번의 기회동안 답을 맞추게함.
힌트: 낮춰라 높혀라
기회 3번 , 3번 안에 못맞추면 넌 바보하고 강제종료
*/
public class Quiz {
	
	int nanSu() {
		double rand = Math.random();
		int result = (int)(rand*10);
		return result;
	}///end of nanSu
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Quiz rand = new Quiz();
		
		int result = rand.nanSu();
		
		for(int i=0; i<3; i++) {
			System.out.println("정답을 말해보시오.:");
			int choice = sc.nextInt();
			if(choice == result) {
				System.out.println("정답입니다.^^");
				break;
			}
			else if(choice < result){
				System.out.printf("틀렸습니다.(기회:%d)\n",2-i);
				if(i < 2) {
				System.out.println("지금 값보다 큽니다.");
				}
			}
			else if(choice > result) {
				System.out.printf("틀렸습니다.(기회:%d)\n",2-i);
				if(i < 2) {
				System.out.println("지금 값보다 작습니다.");
				}
			}
			if(i==2) {
				System.out.println("바보!!");
				System.out.printf("정답은 %d 이지롱~", result);
			}
		}
	}//end of main
}//end of Quiz
