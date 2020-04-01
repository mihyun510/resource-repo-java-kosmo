package book.chap07;

public class ArrayString {
	public static void main(String[] args) {
		String[] nameList = {"김미현","김혜림","이민규","정의남"};
		String[][]  nameList2 = {
									{"김미현","김혜림","이민규","정의남"}
									,{"25","26","27","28"}
								};
		String[][] nameList3= {
				{"김미현","김혜림","이민규","정의남"},
				{"25","26","27","28"},
				{"바둑","웨이크보드","당구","사이클"}
		};
		//내 친구의 취미만 출력을 해주세요 취미정보만 출력하고 싶다
		//nameList3은 배열의 이름이고 여기에 length이면 로우의 수이다.
		for (int i = 2 ; i < nameList3.length; i++) { //2중 for문 사용안해도됨.
			for (int j = 0; j < nameList3[i].length; j++) {
				System.out.print(nameList3[i][j]+" ");
			}
		}
		System.out.println();
		
		for (int i = 0; i < nameList3[2].length; i++) {
			System.out.print(nameList3[2][i]+" ");
		}
	}
}

