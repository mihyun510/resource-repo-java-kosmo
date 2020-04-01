package book.chap04;

public class Fibonachi {
	
	public void suyeal() {
		int[] arr = new int[20];
		
		for (int i = 0 ; i < 20; i++) {
			if(i<=1) {
				arr[i] = i;
			}
			else {
				arr[i] = arr[i-2] + arr[i-1];
			}
			System.out.println("a"+(i+1)+"항= "+arr[i]);
		}
	}

}
