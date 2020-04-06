package exam0407.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exam0407.DateInfo;

class DateInfoTest {
	DateInfo di = new DateInfo();
	@Test
	void testGetDifferenceDates() {
		//				┌> di.getDifferenceDates("2020-03-29", "2020-03-31", "yyyy-MM-dd", 'H')결과를 
		//				│  24와 비교해서 true:성공 flase:실패
		assertEquals((long)24, di.getDifferenceDates("2020-03-29", "2020-03-31", "yyyy-MM-dd", 'H'));
	}

}
