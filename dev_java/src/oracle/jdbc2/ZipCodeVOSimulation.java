package oracle.jdbc2;

public class ZipCodeVOSimulation {
	static void methodA() {
		ZipCodeVO zcVOS[] = new ZipCodeVO[2];//컬렉션과 제네릭 공부
		ZipCodeVO zcVO = new ZipCodeVO();
		//삼각형 안에 zipcode변수에 값 넣기
		zcVO.setZipcode(21548);//우편번호를 [**각각-6(인스턴스화)] 담았다.
		zcVOS[0] = zcVO; //사각형의 삼각형 넣기
		zcVO = new ZipCodeVO();
		zcVO.setZipcode(23598); //우편번호를 [각각-9(인스턴스화)] 담았다.
		zcVOS[1] = zcVO;
		//메소드호출
		printZcVOS(zcVOS);//메소드 파라미터로 주소번지 넘겨주기 - 배열의 주소번지 넘겨주기
	}
	static void printZcVOS(ZipCodeVO zcVOS[]) {
		for(ZipCodeVO zcVO:zcVOS) {
			System.out.println(zcVO.getZipcode());
		}
	}

	public static void main(String[] args) {
		methodA();

		ZipCodeVO zcVO = new ZipCodeVO();
		//zcVO.uid_no =10; //[문법에러] - private -왜? 웹이나 앱에서 동시사용자가 많을때 변조되면 안됨.
		zcVO.setUid_no(10);//setter 초기화 쓰기 0 -> 10
		zcVO.setUid_no(30);//오버라이트 10 -> 30
		int Uid_no=zcVO.getUid_no();
		System.out.println("?"+Uid_no);//10 -> 30 초기화가 바뀜 함수를 거쳐옴.
		zcVO.setAddress("서울시 관악구 신림동");
		String address = zcVO.getAddress();
		System.out.println(""+address);
		zcVO.setZipcode(151010); //151010은 어떤 변수에 저장되는 값일까요?? - ZipCodeVO클래스의 setZipcode함수의 zipcode변수명에 저장된다. 그리고 ZipcodeVO클래스의 zipcode멤버변수에 저장된다.
		int zipcode = zcVO.getZipcode();
		System.out.println(""+zipcode);
	}
}
