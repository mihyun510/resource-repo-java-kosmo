package oracle.jdbc2;

import java.util.Vector;

public class ZipCodeList2 {

	public static void main(String[] args) {
		Vector<ZipCodeVO> v = new Vector<ZipCodeVO>(); //아직 데이터가 추가되지 않았다. 인스턴스화는 되어있다.
//		Vector<String> v1 = new Vector<>(); // 이것을 해주면 바로 파인애플 넣을수있음
//		System.out.println("v.size() : "+v.size());
//		ZipCodeVO zcVO = new ZipCodeVO();
//		zcVO.setAddress("서울시 마포구 공덕동");
//		zcVO.setZipcode(21545);
//		v.add(zcVO);
//		//v.add(0,"파인애플");[오류발생] 타입이 맞지 않음 
//		
//		v1.add(0,"파인애플");//인스턴화 시켜서 에러안남
//		System.out.println(v1.get(0));//파인애플
//		System.out.println(v.get(0).getZipcode());//
//		ZipCodeVO zVO = v.get(0);
//		int zipcode = zVO.getZipcode();
//		System.out.println(zipcode);
		
		////////////////////////////////////////////////////////////////////
		ZipCodeVO zcVO = null;//선언만 한 상태, 아직 생성x 방크기가 얼마나인지 모르기때문
		ZipCodeVO zcVOS[] = null;//
		int i =0;
		while(i<3) {//3번의 인스턴스화를 하고 각 방에 각각 다른 주소가 들어감
			zcVO = new ZipCodeVO();//인스턴스화 주소가 메모리에 생성
			System.out.println("zcVO주.번가 바뀐다."+ zcVO);
			v.add(zcVO);//배열의 방에 ZipCodeVO의 주소를 넣음 
			i++;
		}
		for (int x = 0; x < v.size(); x++) {
			//Vector에 0번째 들어있는 주소번지는 ZipCodeVO타입이다.
			ZipCodeVO zVO = v.get(x); //zVO 변수로 선언가능 추력할때만 사용
			System.out.println(zVO);//각 방의 인스턴스화한 주소를 출력
		}
		zcVOS = new ZipCodeVO[v.size()];//Arraylist의 크기를  배열에 삽입 29,31-인스턴스화함과 동시에 배열에 삽입해주었으므로 3개의 방이 Arratlist에 생성 
		System.out.println("배열의 크기는 "+zcVOS.length);//배열의 크기를 출력
		//zcVOS가 가리키는 객체 배열에 들어있는 정보를 출력해 보세요.
		//insert here
		//zcVOS.length는 배열의 방크기(겟수) - 3개 있다.

		//리턴타입이 void이지만 값을 유지하게 할 수있다.
		//파라미터로 넘긴 주소번지에 v에 저장되어 있는 주소번지를 북제해주는 메소드
		v.copyInto(zcVOS);
		for (int y = 0; y < zcVOS.length; y++) {
			System.out.println(zcVOS[y]);//null, null, null
		}
		//저 배열의 세개 방안에 ZipCodeVO가 초기화 될 수 있도록 코드를 작성해 보세요.

		for (int j = 0; j < zcVOS.length; j++) {
			zcVOS[j] = v.get(j);
			System.out.println(zcVOS[j]);
		}
	}
}
