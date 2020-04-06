package exam0407.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exam0407.MyCalcView;

class MyCalcViewTest {

	MyCalcView mv = new MyCalcView();
	
	@Test
	void testInitDisplay() {
		assertTrue(mv.initDisplay()); //블리언 타입의 메소드를 테스트하기위해서는 assertTrue을 사용., 해당하는 함수에 대한 테스트 진행
	}
	
	@Test
	void testMethodA() {
		assertEquals(1,mv.MethodA(3, 3)); //2개의 값이 같은지를 테스트하기 위해서는 assertEquals을 사용한다.
	}
}
