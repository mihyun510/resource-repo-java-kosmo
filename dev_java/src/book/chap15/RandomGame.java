package book.chap15;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//보조 스트림
public class RandomGame extends JFrame {
	
	public static void main(String[] args) {
		RandomGame rg = new RandomGame();
		Random r = new Random();
		int user = -1; //사용자가 입력한 숫자 담기
		int dap = r.nextInt(10);//API exclusive -배제한다. 매개변수를
		JOptionPane.showMessageDialog(rg, "0부터 9중에서 입력해봐.","INFO",JOptionPane.INFORMATION_MESSAGE); //public static final : 클래스로 접근가능 및 변하지 않는 상수
//		InputStream in = new InputStreamReader(System.in); //에러발생 이것은 추상클래스임을 알수 있음
		InputStreamReader in = new InputStreamReader(System.in); //보조스트림이 아닌 기반 스트림이기 떄문에 혼자 사용가능
		try {
//			user = in.read();//아스키코드 10진수를 가져온다.
			while(((user=in.read()) != -1)) {
				System.out.println("사용자가 입력한 숫자:"+user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
