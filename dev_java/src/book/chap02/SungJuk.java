package book.chap02;

public class SungJuk {
	/*
	 * 내 안에 있는 메소드라 하더라도 메소드 선언시 static이 없으면 main메소드에서 
	 * 호출할 수 없다.
	 * 총점을 구해주는 메소드가 있다. 이름은 hap임.
	 * 파라미터가 있다|없다.
	 */
	int hap(int kor, int eng) {
		int tot = 0;
		tot = kor + eng;//140
		return tot;
	}/////////////////////////////////////end of hap
	//평균은 소수점이 나올 수 있다.
	//리턴값은 실수형으로 선언한다.
	//메소드 선언하기
	//반환타입 메소드이름(파라미터1, 파라미터2,....)
	//tot/3;
	double avg(double tot) {
		return tot/2.0;//int/double = int 
	}//end of avg
	void methodA() {}
	/*
	 * 28-31-32(kor)-33(eng)-34-10(메소드를 호출※이때 ((kor,eng)값을 가지고 감),값이 복사됨)-11-12-13-14(hap끝)-
	 * 36[110]-39-20[140이 복사됨]-21-22-40
	 */
	public static void main(String[] args) {
		//insert here - hap메소드에서 합을 구한 값을 여기서 사용하고 싶어요.
		//어떡하지?	
		SungJuk gab = new SungJuk();
		int kor = 80; //점수1이 결정되었다.
		int eng = 65; //점수2가 결정되었다.
		int total = gab.hap(kor, eng);
		//위에서 계산한 결과인 tot변수를 avg메소드에서 사용하고 싶다.
		System.out.println("점수의 합은 "+ total);
		//int m = gap.methodA();
		//평균구하고 싶어요?
		double avgValue = gab.avg(total);
		System.out.println("평균은 "+ avgValue);
		
		
	}//end of main

}
