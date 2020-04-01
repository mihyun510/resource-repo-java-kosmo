package book.chap08;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookFrame extends JFrame {
	String[] cols = {"도서명", "저자"};
	String[][] data = new String[0][2];
	DefaultTableModel dtm_book = new DefaultTableModel(data,cols);
	JTable jtb_book = new JTable(dtm_book);
	JScrollPane jsp_book = new JScrollPane(jtb_book); //헤더가 나올려면 JScrollPane을 추가하거나 JTableHeader를 추가해야됨.
	
	public BookFrame() {
		ArrayList<Book1> library = new ArrayList<>();
		Book1 b1 = new Book1();
		b1.b_title = "태백산맥";
		b1.b_autor = "조정래";
		library.add(b1);
		b1 = new Book1();
		b1.b_title = "토지";
		b1.b_autor = "박경리";
		library.add(b1);
		System.out.println("size : "+library.size()); //2
//		Vector<Book1> v = new Vector<>(); //Book1에서 String타입으로 바꾼 이유는..? --> Vector에 담을 타입이 String으로 변경되었기때문이다. Book1의 주소를 add해줄려고 하는 것이 아니라 Book1안에 변수의  값을 담을 것인데 그 값의 타입이 String이다. 
		Vector<String> v = new Vector<>();
		v.add(library.get(0).b_title);
		v.add(library.get(0).b_autor);
		System.out.println("v:"+v); 	//칸이 비어 있으면 백터에서 자동으로 null값을 채워줌 그래서 칸이 4개 일때 다음에 찍으면 2개는 데이터가 들어가고 나머지 데이터가 없는 방에는 null이 들어가고 다음값이 추가되는 것을 확인하였다.
		dtm_book.addRow(v); //기존의 데이터행을 먼저 추가한다.
		System.out.println("v:"+v);
		v = new Vector<>(); //???????왜 를 하는데 새로운 백터를 생성하는 것일까 원래 기존에 있던 백터에 한개의 방을 더 추가하면 되는 것이 아닐까?.......?
		v.add(library.get(1).b_title); //└> 백터의 방은 무한히 들어가지만 
		v.add(library.get(1).b_autor); 
//		v.add(b1); //이것은 b1의 주소를 넣은 것이다.
		System.out.println("v:"+v);
		dtm_book.addRow(v); //addRow는 벡터 형태의 파라미터 값을 받는다. b1의 주소값이 행에 출력된다.
		this.add("Center",jsp_book);
		this.setSize(600,300);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new BookFrame();
	}
}
