package Q2;
/*
 * 2. 1부터 1000까지 숫자중 2의 배수 이거나 5의 배수인 숫자의 합을 구하는 
   소스코드와 결과화면 스크린 샷을 제출하시오
    [요구사항] 
   가. 단, 2와 5의 공배수인 경우 제외
   나, while문 버전과  for문 버전으로 두가지 소스코드를 작성하시오
 */
/*****************************************************************
 * for{ if()else if() } 
 * while( < ){if()else if()}
 * 조건은 2개 - 2의 배수이니? 5의 배수이니? 아니면 둘다 만족하면 최소공배수이니?
 * 최소공배수이면 &&, 각각의 조건이면 ||이다.
 * 
 *****************************************************************/
public class Q2 {
	
	public static void main(String[] args) {
		int dap1 = 0, dap2 = 0;
		int i = 0;
		//for문 사용
		for(i = 0;i<1000;i++) {
			if(i%2==0 && i%5==0) {
				//제외
				continue;
			}else if(i%2==0 || i%5==0) {
				dap1 = dap1 + i;
			}
		}///////end for
		
		System.out.println("for문 사용 ===>"+ dap1);
		
		i=1;///////////////////i를 다시 초기화
		//while문 사용
		while(i<1000) {
			if(i%2==0 && i%5==0) {
				//제외
			}else if(i%2==0 || i%5==0) {
				dap2 = dap2 + i;
			}
			i++;///////////이것이 없다면 무한루프가 돈다.[무한루프 방지코드]
		}//////////end while
		
		System.out.println("while문 사용 ===>"+ dap2);
	}///////end main
	
	
	
	
	
//	public static void main(String[] args) {
//		int[] num = new int[1000];
//		int sumNumFor = 0;
//		int sumNumWhile = 0;
//		int cnt = 0;
//		for (int i = 0; i < num.length; i++) {
//			num[i] = i+1; //num배열에 1~1000까지 초기화
//		}////////////end for1
//		
//		//for문으로 구하기
//		System.out.println("============== For 문 ==============");
//		for (int i = 0; i < num.length; i++) {
//			if(num[i] % 2 == 0 && num[i] % 5 == 0) {
//				continue;
//			}
//			else if (num[i] % 2 == 0 || num[i] % 5 ==0) {
//				sumNumFor += num[i];
//			}
//		}//////////////end for2
//		System.out.println("1부터 1000까지 숫자중 2의 배수 이거나 5의 배수인 숫자의 합: "
//							+sumNumFor);
//		
//		//while문으로 구하기
//		System.out.println("============== while문 =============");
//		while(cnt < 1000) {
//			if(num[cnt] % 2 == 0 && num[cnt] % 5 == 0) {
//			}
//			else if (num[cnt] % 2 == 0 || num[cnt] % 5 == 0) {
//				sumNumWhile += num[cnt];
//			}
//			cnt++;
//		}/////end while
//		System.out.println("1부터 1000까지 숫자중 2의 배수 이거나 5의 배수인 숫자의 합: "+sumNumWhile);
//	}//////////////end main

}
