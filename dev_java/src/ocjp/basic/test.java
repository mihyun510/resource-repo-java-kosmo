package ocjp.basic;

public class test {
	
	public static void replaceJ(String text) {
		 text.replace('j', 'l');
	}
	
	
	public static String replaceJ2(String text) {
		String imsi = null;
		imsi = text.replace('j', 'l');
		return imsi;
	}
	
	public static void main(String args[]) {
		 String text = new String("java"); //String text = "java"
		 replaceJ(text);
		 System.out.println(text);
		 System.out.println("===========================");
		 String text2 = null;
		 text2 = replaceJ2(text);
		 System.out.println(text2);
		 
		 System.out.println("===========================");
		 String str2 = "java";
		 String str3 ="java";
	}
}
