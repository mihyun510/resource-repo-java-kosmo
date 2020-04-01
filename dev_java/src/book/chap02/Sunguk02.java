package book.chap02;

public class Sunguk02 {
	
	int hap(int kor, int eng) {
		int tot = 0;
		tot = kor + eng;//140
		return tot;
	}/////////////////////////////////////end of hap
	
	double avg(double tot, int subject) {
		return (double)tot/subject;//int/double = int 
	}//end of 
	
	public static void main(String[] args) {
		Sunguk02 gab = new Sunguk02();
		int kor = 80; //점수1이 결정되었다.
		int eng = 65; //점수2가 결정되었다.
		int total = gab.hap(kor, eng);
		final int subject = 2; //초기화할 수 있는 사람. 여기는 과목수를 담을거야
//		국어 영어 두 과목이니깐 얼마로 초기화 해야되지?
		double avgValue = gab.avg(total, subject);
		System.out.println("두 과목의 평균은 "+ avgValue);
	}
}
