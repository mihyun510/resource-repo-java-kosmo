package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class OrderBasketDataSet {
	//데이터를 어레이리스트로 받고 디비를 연동해보자.
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	public ArrayList<OrderBasketVO> getList2(){
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>(); //이런경우는 return에 al를 넘길때 null이 들어갈 확률이 났다. 그런데 이렇게 인스턴스화하는것은 좋지 않다. 처음엔 선언만 하고 나중에 필요에 의해서 생성을 해주는 것이 베스트이다.
		StringBuilder sb = new StringBuilder("");
		sb.append("select decode(b.rno,1,a.indate_vc, 2,'총계') as indate_vc							");
		sb.append(" , sum(a.qty_nu) as t_qty, sum(qty_nu*price_nu) as t_price						");
        sb.append("  from t_orderbasket a, (select rownum rno from dept where rownum < 3) b        ");
        sb.append(" group by decode(b.rno,1,a.indate_vc,2,'총계')                                   ");
        sb.append(" order by indate_vc                                  										");
        
        try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			//VO는 한번에 한개 row만 담을 수 있어요.
			//두 개 로우는 안되죠.
			//VO에는 변수 하나에 한 개 값만 담는 변수를 선언했기 때문이죠.
			OrderBasketVO obVO = null;
			while(rs.next()) {
				obVO = new OrderBasketVO();
				//오라클에서 꺼낸 값은 rs로 꺼내고
				//위에서 꺼낸 값은 obVO에 선언된 변수 중 indate_vc에 값을 담아주세요.
				obVO.setIndate_vc(rs.getString("indate_vc")); //rs를 사용해서 indate_vc에 해당하는 값을 꺼낸다음(여기서 이 값은 String타입) 이값을 setIndate_vc함수를 사용해서 OrderBasketVO타입의 obVO객체의 indate_vc멤버변수의 값을 초기화해준다. 즉, 디비연동을 해서 얻은값을 obVO에 넣어주자
				obVO.setT_qty((rs.getInt("t_qty")));
				obVO.setT_price((rs.getInt("t_price")));
				al.add(obVO);
			}
			
			//자바에러는 이클립스에서 잡고 오라클 에러는 토드에서 잡는게 좋겠다.
		} catch (SQLException se) { //오라클에서 발생되는 에러메시지 잡기
			System.out.println(se.toString()); 
		} catch (Exception e) { //자바전체에서 발생되는 에러메시지 잡기
			System.out.println(e.toString());
		}
        return al; //null은 값이 넘어가지 않는다. ※ al = null인 경우를 주의하자. al에 값이 들어가지 않는 경우가 있다.
	}
	
	//main메소드를 직접 컨트롤 할 수 없다. - JVM이 컨트롤한다.
	//ArrayList에 OrderBasketVO타입의 데이터드를 담자.
	public ArrayList<OrderBasketVO> getList(){ //ArrayList<OrderBasketVO>타입의 함수를 생성
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>();
		OrderBasketVO obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-19"); //날짜 
		obVO.setT_qty(150);			 	//수량
		obVO.setT_price(560000);        //판매가격
		al.add(obVO);
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-20");
		obVO.setT_qty(105);
		obVO.setT_price(360000);
		al.add(obVO);
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("총계");
		obVO.setT_qty(255);
		obVO.setT_price(920000);
		al.add(obVO);
		
		return al;
	}
	public static void main(String[] args) {
//		OrderBasketTest obt = new OrderBasketTest();
//		ArrayList<Integer> al = new ArrayList<Integer>(); 1개짜리 타입은 ┐의 n개 짜리를 알면 자연스럽게 이해되는 개념
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>();
		OrderBasketVO obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-19"); //날짜 
		obVO.setT_qty(150);			 	//수량
		obVO.setT_price(560000);        //판매가격
		al.add(obVO);
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-20");
		obVO.setT_qty(105);
		obVO.setT_price(360000);
		al.add(obVO);
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("총계");
		obVO.setT_qty(255);
		obVO.setT_price(920000);
		al.add(obVO);
		
		for(OrderBasketVO obVO2:al) {
			System.out.println(obVO2.getIndate_vc()+"  "+obVO2.getT_qty()+"  "+obVO2.getT_price()+"\n");
		}
		
		
		//이렇게 하지 말고 ArrayList함수를 만들어서 함수를 불러서 화면에 보여주도록 하자.
//		Vector<Object> v = new Vector<Object>();
//		v.add(al.get(0).getIndate_vc());
//		v.add(al.get(0).getT_qty());
//		v.add(al.get(0).getT_price());
//		obt.dtm.addRow(v);
//		v = new Vector<Object>();
//		v.add(al.get(1).getIndate_vc());
//		v.add(al.get(1).getT_qty());
//		v.add(al.get(1).getT_price());
//		obt.dtm.addRow(v);
//		v = new Vector<Object>();
//		v.add(al.get(2).getIndate_vc());
//		v.add(al.get(2).getQty_nu());
//		v.add(al.get(2).getT_price());
//		obt.dtm.addRow(v);
		
	}
}
