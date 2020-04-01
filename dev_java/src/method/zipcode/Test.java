package method.zipcode;

public class Test {
	//인스턴스화를 메소드 안에서 하기
	//메소드를 통해서 인스턴스화 하는 이유는 뭘까?
	//메소드를 사용해서 객체 주입을 받는 경우 무엇을 더 할 수 있나?
	static Test t2 = null;
	public static Test getInstance() {//미묘한 차이가 있기 때문에
		if(t2 == null) {
			t2 = new Test();
		}
		return t2;
	}//메소드에다가 하면 인스턴스화를 한번만 할 수 있음. 다음부터는 test에 대해 인스턴스화 할때 t2의 주소를 반환.
	String setName() {
		return "이순신";
	}
	
	public static void main(String[] args) {
		Test t = getInstance();
		System.out.println(t);
		Test t1 = new Test();
		System.out.println(t1); //다른 주소를 반환. 이유는...? 생성자가 따로 있음 default 생성자가 주소를 생성해주기때문에
//														그러나 함수를 통해서하면 같은 주소 반환. 
		//insert here 이순신을 출력해보자.
		//이럴 경우 기존에 있는 객체를 사용하여 메소드가 호출됨
		//즉, 불필요한 객체를 또 정의하지 않았음
		System.out.println(t.setName());  				  //인스턴스화한 객체를 가지고 함수에 접근
		//이렇게 할 경우 새로운 객체가 또 만들어지고 메소드가 호출됨
		//주소번지가 없으므로 다른 메소드를 호출할 때나 변수를 접근할 수 없음.
		System.out.println(new Test().setName());		  //클레스 생성함과 동시에 함수에 접근 그러나 일회용 주소를 받은 객체가 없어서 재사용 불가능 
		//메소드를 통해서 객체를 생성하므로 null인 경우만 새로 생성하고
		//기존에 유지되고 있다면 새로 생성하지 않음.
		System.out.println(Test.getInstance().setName()); //getInstance()함수가 static이기때문에 클래스명.함수() 
//															이런식으로 함수에 접근이 가능 그런데 getInstance()함수가 생성자를 만들어주는 
//															함수이므로 생성과 동시에 메모리에 생성되고 그 생성된 것으로 setName()에 접근함. 
	}
}
