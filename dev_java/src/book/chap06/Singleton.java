package book.chap06;

public class Singleton {
	private static Singleton instance = new Singleton();
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	
	public static void main(String[] args) {
		Singleton myCompany1 = Singleton.getInstance();
		Singleton myCompany2 = Singleton.getInstance();
		if(myCompany1 == myCompany2) {
			System.out.println("싱글톤 패턴 입니다.");
		}
	}
	
}
