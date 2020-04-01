package book.chap06;

import javax.swing.JOptionPane;

public class P181 {
	
	void methodA() {
		System.out.println("methodA호출 성공");
	}
	
	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("숫자를 입력하세요.");//JOptionPane 클래스 이름이 옴. 인스턴스명이아님. 즉, static
		//int input = Integer.parseInt(JOptionPane.showInputDialog("숫자를 입력하세요."));//String타입을 int 타입으로 변경
		int i = Integer.parseInt("20");
		//P181.methodA(); //static이 없으니 클래스명으로 함수를 호출 > 인스턴스화를 시켜야 함.
		//메소드 선언시 static을 사용하지 않았으므로 에러발생함.
		//static이 없는 메소드를 호출할땐 반드시 인스턴스화 할것
		//만일 static이 있다면 클래스 타입.메소드명
		P181 p = new P181();
		p.methodA();
		//"30" ===> 30
		System.out.println(Integer.parseInt(input)+10);
	}
}
