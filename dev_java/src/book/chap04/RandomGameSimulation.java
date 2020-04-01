package book.chap04;

import java.util.Random;

public class RandomGameSimulation {

	int random(int choice, int cnt) {
		Random r = new Random();
		RandomGameSimulation rs = new RandomGameSimulation();
		
		int imsi = r.nextInt(10);//0.0 ~ 10.0 여시서 10.0은 포함이 안됨.
		if(imsi==choice) {
			System.out.println("정답^^");
		}
		else if(imsi < choice) {
			System.out.println("틀렸습니다.(기회"+(2-cnt)+"번)");
			rs.hint(cnt, imsi, choice);
		}
		else if(imsi > choice) {
			System.out.println("틀렸습니다.(기회"+(2-cnt)+"번)");
			rs.hint(cnt, imsi, choice);
		}
		//System.out.println("imsi="+imsi);
		if(cnt == 2) {
			System.out.println("정답은 "+imsi+"(이)였습니다.");
		}
		return imsi;
	}//end of random
	
	//힌트를 제공하는 메소드
	void hint(int cnt, int imsi, int choice) {
		if(cnt<2) {
			if(imsi < choice) {
				System.out.println("(힌트)값을 낮추세요.");
			}
			else if(imsi > choice) {
				System.out.println("(힌트)값을 높히세요.");
			}
		}//end of if
	}//end of hint
	
}//end of randomSimulation
