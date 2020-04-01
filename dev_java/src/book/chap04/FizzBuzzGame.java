package book.chap04;

public class FizzBuzzGame {
	
	//if문으로 처리하기
	public void ifTest() {
		System.out.println("ifTest호출 성공");
		int i = 0;
		for (i = 1; i <= 100; i++) { //for(초기화; 조건식; 증감연산자), 1부터 100까지 센다. - for, while문
			if(i%35==0) {//35로 나누었을때 0이야?
				System.out.println("Fizz, Buzz");
			}
			else if(i%5==0) {//5로 나누면 0이니?
				System.out.println("Fizz");
			}
			else if(i%7==0) {//7로 나누었을때 나머지가 0인거야?
				System.out.println("Buzz");
			}
			else {
				System.out.println(i);
			}
		}
	}////////////////end of ifTest
	
	//switch문으로 처리하기
	public void switchTest() {
		System.out.println("switch호출 성공");
		int i=0;
		for (i = 1; i <= 100; i++) { //for(초기화; 조건식; 증감연산자), 1부터 100까지 센다. - for, while문
			switch(i%35) {
				case 0: //1가지
					//insert here fizzbuzz출력
					System.out.println("FizzBuzz");
				break;
				case 5: case 10: case 15: case 20: case 25:
				case 30://2가지
					//insert here fizz출력
					System.out.println("Fizz");
					break;
				case 7: case 14: case 21: case 28: //3가지
					//insert here Buzz출력
					System.out.println("Buzz");
					break;
				default://4가지
					System.out.println(i);
			}///end of switch
		}//////////////end of for
	}/////////////////end of switchTest
	
	public void whileTest() {
		System.out.println("while호출 성공");
		int i=1;
		while(i<=100) {
			switch(i%35) {
			case 0: //1가지
				//insert here fizzbuzz출력
				System.out.println("FizzBuzz");
			break;
			case 5: case 10: case 15: case 20: case 25:
			case 30://2가지
				//insert here fizz출력
				System.out.println("Fizz");
				break;
			case 7: case 14: case 21: case 28: //3가지
				//insert here Buzz출력
				System.out.println("Buzz");
				break;
			default://4가지
				System.out.println(i);
			}///end of switch
			i++;
		}///end of while
		
	}//end of whileTest
}////////////FizzBuzzGame
