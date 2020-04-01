package book.chap02.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import book.chap02.Sonata;
/*
 * JUnit 단위테스트 도구 설치
 * 외부 테스트 클래스를 따로 작성하지 않아도 된다.
 * 단위테스트를 제공하는 프레임워크 - 현재 실무에서도 많이 사용중
 * main메소드 없이도 테스트가 가능하다.
 * 어노테이션(@) 지원 @Test
 * 결과 체크
 * 녹색 - 단위테스트 성공
 * 붉은색 - 단위테스트 실패
 * 
 * assertEquals(a,b):a와 b의 값이 같은지 확인
 * assertTrue(a):a가 참인지 확인
 * assertNotNull(a):a가 null이 아닌지 확인
 */
class SonataTest {
	//아래 메소드가 단위테스트 대상 메소드임.
	@Test
	void testMethodA() {
		Sonata myCar = new Sonata();
		assertTrue(myCar.methodA(3, 3));//해당 파라미터의 값이 참인가 아닌가를 구별
	}

}
