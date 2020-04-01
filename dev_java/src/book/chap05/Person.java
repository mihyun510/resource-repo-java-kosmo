package book.chap05;
/*
 * 같은 이름의 생성자를 여러개 가질 수 있다.
 * 단 파라미터의 갯수가 반드시 달라야 한다.
 * 단 파라미터의 타입이 반드시 달라야 한다.
 * 변수의 이름이 다른 건 인정 못한다. 생성자를 중복 정의했다라고 생각함. ※타입이나 매개변수 갯수만 다르면 됨.
 * 
 */
public class Person {
	//전역변수입니다.
	//초기화를 생략할 수 있어요. 왜냐하면 생성자가 대신 해주니까...
	String name;//성명
	float height;//그사람의 키
	float weight;//그사람의 몸무게
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public Person(String name) {//초기화가 하나만 됨. 나머지 하나의 값은 디폴트생성자가 해준다...?
		//this.name = name;
	}
	//생성자는 여러개 선언하기가 된다. - 단 타입은 반드시 달라야 한다.
	Person(float height){
		this.height = height;
	}
//	Person(float height2){//[오류발생]매개변수의 타입이 같아서 생성자의 오버로딩이 불가능, 변수의 이름만 바꾼다고 해서 생성자의 역할이 달라지지 않음
//		this.height = height2;
//	}
	Person(double height, float weight){//매개변수가 달라도 생성자 오버로딩 가능
		this.height = (float)height; //[오류발생]매개변수의 타입과 멤버변수의 타입이 다름, -캐스팅 연사자를 사용하여 타입을 맞춰주었다. 
		}
	Person(String name, float height, float weight){
		this.name = name;
		this.height = height;
		this.weight = weight;
	}
	
	//디폴트 생성자는 생략할 수 있어요.
	//왜냐하면 JVM이 대신 만들어 줄 수 있기 때문이죠.
	//그러나 파라미터를 갖는 생성자는 만들어줄 수 없어요.
	//왜냐면 그 사람 생각을 내가 알 수 없기 때문이죠.
//	public Person() { //전역변수 초기화를 생성자에서 해줌
//		System.out.println("Person 디폴트 생성자 호출 성공");
////		name = null;
////		height = 0.0f;
////		weight = 0.0f;
//	} //디폴트생성자
}
