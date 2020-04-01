package book.chap15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//톰캑서버의 페이지를 읽어보자
public class ViewURL {
	public ViewURL() {}
	public ViewURL(String strURL) { //URL주소를 서버에 연결하자.
		URL 			myURL 	= null;
		URLConnection 	urlCon 	= null;
		InputStream 	is 		= null;	//기반스트림
		BufferedReader 	br 		= null; //보조스트림 안쓰는 경우는 거의없다.
		String 			data 	= null;
		String 			headerType = null;
		try {
			myURL = new URL(strURL);
			urlCon = myURL.openConnection();
			urlCon.connect();
			headerType = urlCon.getContentType(); //mine type
			is = urlCon.getInputStream();
			br = new BufferedReader(new InputStreamReader(is)); //2줄로 나누어 쓰지않고 한줄로 사용가능.
			File f = new File("src\\book\\chap15\\google_source.txt"); //파일을 생성하자. - 파일의 내용은 비었다. 파일의 이름만 정해서 파일이 생성되는 것이다.
			FileOutputStream fos = new FileOutputStream(f, true); //파일이 새성됨 true는 기존에 있는 파일에 뒤에 내용을 붙혀서 써준다.
			while((data=br.readLine())!=null) { //스트링으로  받은것을 getbyte을 통해서 바이트로 반환한다.
				fos.write(data.getBytes()); //생성한 파일에 읽어온 데이터를 바이트단위로 작성하기.
				System.out.println(data);
			}
			//밑에 2개의 코드는 무조건 실행하도록 하자.
			fos.flush(); //쓰레기통을 비워주자
			fos.close(); //리소스를 닫아주자
		} catch (Exception e) {
			System.out.println("Exception:" + e.toString());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//웹페이지의 자바스크립트 소스코드를 가져오자.
//		new ViewURL("http://localhost:8000/kmhProject/index.jsp"); //https의 s는 인증서가 있어야 열람이 가능하다. 보완을 강화한 것이다.
		new ViewURL("https://www.google.com/index.html");
	}
}
