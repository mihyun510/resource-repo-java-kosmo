package book.chap04;


//1부터 100까지 세면서 5의 배수이면 fizz를 출력, 7의 배수이면 buzz 출력, 두 수의 공배수이면 fizz, buzz 두개 출력
public class FizzBuzzGameSimulation {
	public static void main(String[] args) {
		
		FizzBuzzGame Game = new FizzBuzzGame();
		
		Game.ifTest();
		Game.switchTest();
		Game.whileTest();
		
//		int[] arr = new int[100];
		
/*		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
			if((arr[i]%5==0)&&(arr[i]%7==0)) {
				System.out.println(arr[i]+" fizz, Buzz");
			}
			else if(arr[i]%5==0) {
				System.out.println(arr[i]+" fizz");
			}
			else if(arr[i]%7==0) {
				System.out.println(arr[i]+" Buzz");
			}
			else {
				System.out.println(arr[i]);
			}
			
		}*////end of if
		
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = i+1;
//			int isOn = (arr[i]%5==0 && arr[i]%7==0)? 35 : (arr[i]%5 == 0) ? 5 : (arr[i]%7==0)? 7 : i;
//			switch (isOn) {
//			case 5:
//				System.out.println("Fizz");
//				break;
//			case 7:
//				System.out.println("Buzz");
//				break;
//			case 35:
//				System.out.println("Fizz, Buzz");
//				break;
//			default:
//				System.out.println(isOn);
//				break;
//			}
//		}//end of switch
	}
	
}
