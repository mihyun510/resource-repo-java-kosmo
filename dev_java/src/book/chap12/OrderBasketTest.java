package book.chap12;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//UI와 직접적으로 만나는 부분을 연습해보자.
//JFrame을 상속받았으므로 자식클래스인 OrderBasketTest클래스의 인스턴스 변수로
//부모가 가진 변수나 메소드를 누릴 수 있다.
public class OrderBasketTest extends JFrame {
	String[] col 	= {"날짜", "판매수량", "매출액"};
	String[][] data = new String[0][3];
	DefaultTableModel dtm = new DefaultTableModel(data,col);
	//서로 연관(의존성)이 있는  클래스 사이에서 별도의 메소드나 코딩 없이 
	//생성자의 파라미터를 통해서 값을 초기화할 수 있다.
	JTable jtb 		= new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jtb); //스크롤 패인을 붙히지 않으면 헤더부분을 따로 만들어서 붙혀야됨
//	public OrderBasketTest() {
//		
//	}
	public void initDisplay() {
		this.add("Center", jsp);
//		this.add("Center", jtp); jtb를 붙히면 아무것도 창에 나타나지가 않는다. 즉, jsp를 붙히자.
		this.setSize(500, 400);
		this.setVisible(true);
		dataSetMapping(); // 함수를 불러주자 그럼 화면에 보여진다.
	}
	public void dataSetMapping() {
		OrderBasketDataSet ds = new OrderBasketDataSet();
		ArrayList<OrderBasketVO> al = ds.getList2();
		for (int x = 0; x < al.size(); x++) {
			Vector v = new Vector();
			v.add(al.get(x).getIndate_vc());
			v.add(al.get(x).getT_qty());
			v.add(al.get(x).getT_price());
			dtm.addRow(v); //dtm에 데이터를 담은 행을 추가해주자  
		}
	}
	public static void main(String[] args) {
		OrderBasketTest obt = new OrderBasketTest();
		obt.initDisplay();
	}

}
