package book.chap15;

import java.io.FileInputStream;

public class FileInputStreamTest3 {
	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream("input2.txt")){
			byte[] bs = new byte[10]; //byte배열로 읽어 들이기 위한 배열선언
			int i;
			while((i = fis.read(bs))!=-1){ //read(byte[] b)메소드는 선언한 바이트 배열의 크기만큼 한꺼번에 자료를 읽어온다.
				for (byte b : bs){ //읽어들인 배열의 값에서 값을 하나씩 복사해서 값을 확인 
					System.out.println((char)b);
				}
				System.out.println(":"+i+"바이트 읽음");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
