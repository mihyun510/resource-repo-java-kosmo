package ocjp.basic;
/*
 * 두개의 정수 중에서 큰 숫자를 봔환하는 max함수를 구현하세요.
 * 두개의 정수를 입력 받고 큰 숫자를 출력합니다.
 * 출력) 정수(두 개): 5 8
 * 		최대값:8
 */
import java.util.Scanner;

public class WeightAccountVer2 {
	
	int max_choice(int data1, int data2) {
		int max = data1;
		max = max < data2 ? data2 : data1;
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int data1 = 0, data2 = 0;
		
		WeightAccountVer2 mx = new WeightAccountVer2();
		
		System.out.print("두 개의 정수를 입력하세요.:");
		data1 = sc.nextInt();
		data2 = sc.nextInt();
		
		int result = mx.max_choice(data1, data2);
		System.out.println("두 정수 중에서 최댓값은: "+result);
	}
}
