package debug.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import debug.MethodTest;

class JunitMethodTest {

	@Test
	void testMethodB() {
		MethodTest mt = new MethodTest();
		assertTrue(mt.methodB(5, 3));
	}

}
