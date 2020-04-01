package book.chap15;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//보조 스트림
public class RandomGame2 extends JFrame {
	
	public static void main(String[] args) {
		RandomGame rg = new RandomGame();
		Random r = new Random();
		String user = "-1"; //사용자가 입력한 숫자 담기
		int dap = r.nextInt(10);//API exclusive -배제한다. 매개변수를
		JOptionPane.showMessageDialog(rg, "0부터 9중에서 입력해봐.","INFO",JOptionPane.INFORMATION_MESSAGE); //public static final : 클래스로 접근가능 및 변하지 않는 상수
//		InputStream in = new InputStreamReader(System.in); //에러발생 이것은 추상클래스임을 알수 있음
		
		InputStreamReader in = new InputStreamReader(System.in); //보조스트림이 아닌 기반 스트림이기 때문에 혼자 사용가능? InputStreamReader는 보조 스트림인데? 기반을 넣어준거다?
		//버퍼링 기능이 추가되어 있는 보조스트림이다.
		//단독으로는 읽기 불가함.
		BufferedReader br = new BufferedReader(in); //보조스트림 안에 기반스트림을 넣어주어 사용하자
		try {
			//            ┌>보조 스트림을 사용하여 문자열 전체를 가져온다. - 보조스트림 안에 기반스트림을 주입하여  생성 하였기때문에 사용가능
			while(((user=br.readLine()) != null)) {//
				System.out.println("사용자가 입력한 숫자:"+user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
