package ocjp.basic;

public class Q26 {

	public static void main(String[] args) {
	
//		 int i = 1; //i=1
//		 int j = i++; //i=2, j=1
//		 if((i == ++j) | (i++ == j)) { //(2==2) or (2==2)
//		 i += j; //=(i=i+j)무조건 실행됨, 첫번째 조건에서 참이나왔기때문에, 만약 ||이였다면 뒤에 조건을 실행하지않음 그래서 i값이 증가하지 않아서 i=4
//		 }
//		 System.out.println("i = " + i);

		 int i = 1; //i=1
		 int j = i++; //i=2, j=1
		 if((i == ++j) && (i++ == j)) { //true(2==2) or true(2==2) = true (실행후:i=3, j=2)
			 i += j; //참&&참 이므로 결과는 참 
		 }
		 System.out.println("i = " + i);
		 
	}

}
