package book.chap05;

public class PersonTest {

	public static void main(String[] args) {
		//파라미터가 있는 생성자가 한개라도 있으면 자동으로 제공되지 않아요
		//생성자가 한개도 없으면 JVM이 디폴트 생성자는 제공해 주지만
		//한개라도 있을 땐 제공하지 않아요.
		Person p = new Person("강호동"); //this.name으로 자신의 멤버변수로 초기화를 하지 않았기 때문에 매개변수로 받아도 그 매개변수는 자신의 멤버변수로 초기화하지 못함.
		System.out.println(p.name);
		//Person p = new Person(); // 생성함과 동시에 디폴트 생성자가 호출됨, 디폴트 생성자가 없어도 생성이됨. JVM에서 자동으로 만들어주는 것을 확인
		//System.out.println(p.name+", "+p.height+", "+p.weight); //초기화가 되었음을 확인.
		
		p.name = "이순신";
		p.height = 50.0f;
		p.weight = 40.0f;
		System.out.println(p.name+p.height+p.weight);
		
		p.setName("김미현");
		p.setHeight(100.0f);
		p.setWeight(100.0f);
		System.out.println(p.getHeight()+p.getName()+p.getWeight());
	}
}
