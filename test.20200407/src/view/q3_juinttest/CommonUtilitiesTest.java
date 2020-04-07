package view.q3_juinttest;
import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

import view.q3_junit.CommonUtilities;
class CommonUtilitiesTest {
	//테스트를 위해서 CommonUtilitie을 인스턴스화 해준다.
	CommonUtilities cu = new CommonUtilities();
	@Test
	void testIsNumber() {
		//문자열이 숫자 형식이면 true, 아니면 false를 반환하는 메소드
		assertTrue(cu.isNumber("123"));
		
	}
	@Test
	void testGetDifferenceDates() {
		//두 날짜의 차이를 반환하는 메소드
		try {
			assertEquals((long)48, cu.getDifferenceDates("2020-04-07", "2020-04-05", "yyyy-MM-dd", 'H'));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testGetFirstCharacter() {
		//이름에서 초성을 구해 초성을 반환하는 메소드
		assertEquals('0', cu.getFirstCharacter("123"));
	}
}
