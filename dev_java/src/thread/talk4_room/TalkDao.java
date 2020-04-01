package thread.talk4_room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConnectionMgr;

public class TalkDao {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	PreparedStatement pstmt = null; //쿼리문을 요청할 때 - 오라클
	PreparedStatement pstmt1 = null; //쿼리문을 요청할 때 - 오라클
	ResultSet rs = null; //오라클에 살고 있는 커서를 조작하기
	public String loginProc(String mem_id, String mem_pw) {
		String mem_name = null;
		//insert here
		return mem_name;
	}
	
	
	public String login(String mem_id, String mem_pw) {
		String mem_name = null;
		StringBuilder isID = new StringBuilder();
		StringBuilder sql = new StringBuilder();
        int status = 2;
		try {
			int i = 1;
			isID.append("select NVL((select 1 from dual");
			isID.append(" 				where exists (select mem_name from member");
			isID.append(" 								where mem_id = ?)),-1) as isID");
			isID.append(" from dual");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(isID.toString());
			pstmt.setString(i++, mem_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				status = rs.getInt("isID");
			}
			if(status == 1) { //아이디가 존재하면 비번을 비교한다.
				sql.append("select mem_name from member");
				sql.append(" where mem_id =?");
				sql.append(" and mem_pw =?");
				pstmt1 = con.prepareStatement(sql.toString());
				pstmt1.setString(1, mem_id);
				pstmt1.setString(2, mem_pw);
				rs = pstmt1.executeQuery();
				if(rs.next()) {
					mem_name = rs.getString("mem_name");
				} else {
					mem_name = "비밀번호가 맞지 않습니다.";
				}
				return mem_name;
			}
			else { //아이디가 존재하지 않으면 비번을 비교할 필요없음
				return "아이디가 존재하지 않습니다.";
			}
//			System.out.println("status:"+status);
		} catch (SQLException se) { 
			System.out.println("[Query]"+se.toString());
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return mem_name;
	}
}
