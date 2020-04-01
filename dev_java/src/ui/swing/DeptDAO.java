package ui.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class DeptDAO {
	//오라클 서버와 연결통로를 만드는데 필요한 인터페이스 입니다.
	Connection con = null;
	//연결통로가 만들어지면 그 값을 따라 select문을 오라클에게 전달해 줄 클래스가 필요하죠
	//그 아이가 PreparedStatement입니다.
	PreparedStatement pstmt = null;
	//조회 결과를 오라클 서버로 부터 꺼내려면 커서가 필요한데 그 커서를 조작할 수 있도록
	//서버에서 제공하는 인터페이스가 ResulttSet입니다.
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	//메소드의 리턴 타입은 배열로 했어요. 왜냐하면 그 배열을 JComboBox에 생성자
	//파라미터로 넘겨야 하기때문에 리턴타입이 꼭 필요한거죠
	public String[] getDepList() {
		//부서명을 담을 배열을 선언했어요. 그런데 생성하는 건 안될거 같아요.
		//왜냐하면 오라클에서 꺼낸값이 몇건인지 알아야 배열의 크기를 정할 수 있기 때문입니다.
		String depts[] = null;
		//쿼리문을 작성할 떄 여러 로우가 나올 수 있는데 String을 사용하면 원본이 바귀지 
		//않아서 자바성능튜닝에서 못쓰게 됩니다.
		//대신 StringBuilder를 사용하라고 권장하죠.
		//이 클래스는 원본이 바뀌기 떄문에 불필요한 자원낭비를 막을 수 있데용.
		//서버 입자에서는 동시 접속자 수가 많아서 작은 양이지만 큰 문제를 일으킬 수도 있다고 합니다.
		StringBuilder sb = new StringBuilder("");
		//쿼리문작성하기
		sb.append("select dname	"); 
		sb.append("  from dept  ");
		//예외가 발생되면 시스템이 멈춰서있게 됩니다.
		//무한히 기다리는 상황이 발생되므로 다음 사람도 이용할 수가 없네요.
		try {//물리적으로 떨어져 있는 서버의 ip주소로 접근하니까 예외가 발생할 가능성이 있음. ex)노이즈, ip주소가 잘못 씌임..
			//insert here - DB연동을 해보자
			con =dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString()); //너가 가진거 다 보여줘 - 물음표가 필요없는 이유.. 인덱스가 맞지 않습니다..
			rs = pstmt.executeQuery(); 
			Vector<String> v = new Vector<String>();
			//커서를 한줄씩 넘기면서 커서위체에 값을 변수에 담는다. //while문을 다 돌고 나면 벡터의 방이 결정되고
			while(rs.next()) {
				String dname = rs.getString("dname");
				//조회된 값을 벡터 클래스에 추가한다.
				v.add(dname);
			}
			//오라클 서버에서 조회된 결과만큼 추가된 벡터의 크기값을 가지고
			//배열에 생성한다.
			depts = new String[v.size()];				//벡터의 크기를 가지고 배열의 크기를 초기화 합니다.
			//벡터에 들어있는 정보를 String 배열에 복사한다.
			v.copyInto(depts);							//copyInto을 가지고 vector의 정보를 depts로 모두 복사 타입과 파라미터 맞춰야됨. depts 부서의 이름을 모두 전달받게됨.
		} catch (Exception e) {
			System.out.println("Exception"+e.toString());
		}
		return depts;
	}
	
	public static void main(String[] args) {
		DeptDAO dd = new DeptDAO();
		String depts[] = dd.getDepList();
		for(String dept : depts) {
			System.out.println(dept);
		}
	}
}
