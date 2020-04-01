package book.chap15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest2 {
	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream("input.txt")){ //FileInputStream이 바이트 단위로 파일을 읽어들임.
			int i;
			//				i를 바이트 단위로 읽어들임
			while((i = fis.read())!= -1) { //fis.read()가 읽어서 i값에 넣은후 이값이 읽어들인 값이 있다면 , read()메소드는 읽어들인 파일이 없다면 -1를반환한다.
				System.out.println((char)i); //i를 계속 출력
			}
			System.out.println("end");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
