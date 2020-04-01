package design.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConnectionMgr;

public class BookDao {
	
	DBConnectionMgr dbmgr = DBConnectionMgr.getInstance();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int bookDelete(BookVO pbVO) {
		System.out.println("bookDelete 출력 성공");
		List<Integer> bons = null;
		int result = 0;
		StringBuilder sql = new StringBuilder("");
		int cnt = 0;
		try {
			if(pbVO.getBnos()!=null) {
				cnt = pbVO.getBnos().size();
				System.out.println(cnt);
			}
			sql.append("DELETE FROM book2020 WHERE b_no IN(");
			for (int x = 0; x < cnt; x++) {
				if(x == cnt - 1) { //마지막이면 ,대신 )로 sql문을 종료
					sql.append("?)");
				} else {
					sql.append("?,"); //마지막이 아니라면 ,로 물음표를 계속 추가.
				}
			}
			con = dbmgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			int no = 0;
			bons = pbVO.getBnos();
			for (int j = 0; j < cnt; j++) {
				pstmt.setInt(++no, bons.get(j));
			}
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]:"+sql.toString());
		} finally {
			dbmgr.freeConnection(con, pstmt);
		}
		return result;
	}

	//파라미터로 1을 네개 삽입해서 추가해봄. 4개가 필요함
	//1 row inserted => 1 실패하면 0
	public int bookInsert(BookVO pbVO) {
		System.out.println("bookInsert 출력 성공");
		
		int i = 1;
		int result = 0;
		StringBuilder sql = new StringBuilder("");
		
		try {
			sql.append("INSERT INTO book2020(b_no, b_name, b_author, b_publish, b_info)");
			sql.append(" VALUES(seq_book_no.nextval,?,?,?,?)");
			con = dbmgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(i++, pbVO.getB_name());
			pstmt.setString(i++, pbVO.getB_author());
			pstmt.setString(i++, pbVO.getB_publish());
			pstmt.setString(i++, pbVO.getB_info());
			
			result = pstmt.executeUpdate();
			System.out.println("result: "+result);//1이면 입력 성공, 0이면 입력 실패
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]:"+sql.toString());
		} finally {
			dbmgr.freeConnection(con, pstmt);
		}
		return result;
	}

	public int bookUpdate(BookVO pbVO) {
		System.out.println("bookUpdate 출력 성공");
		int i = 1;
		int result = 0;
		StringBuilder sql = new StringBuilder("");
		sql.append("UPDATE book2020 SET b_name=?, b_author=?, b_publish=?");
		sql.append(" WHERE b_no=?");
		
		try {
			con  = dbmgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(i++, pbVO.getB_name());
			pstmt.setString(i++, pbVO.getB_author());
			pstmt.setString(i++, pbVO.getB_publish());
			pstmt.setInt(i++, pbVO.getB_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]:"+sql.toString());
		} finally {
			dbmgr.freeConnection(con, pstmt);
		}
		return result;
	}

	public BookVO bookDetail(BookVO pbVO) {
		System.out.println("bookDetail 출력 성공");
		BookVO rbVO = null;
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select b_no, b_name, b_author, b_publish, b_info, b_img");
			sql.append(" from book2020						   ");
			sql.append(" WHERE b_no=?						   "); //2번
			con = dbmgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, pbVO.getB_no());
		
			rs = pstmt.executeQuery();
			//미리 rs.next()가 실행되면 커서가 한 번 옮겨져서 false값이 밑에 if문이 실행이 안된다.
			//그래서 rbVO가 생성이 안된다.
			//그래서 if문 밑에 rbVO.get~ 이부분을 찍을때 nullpoint가 떨어진다. 
//			System.out.println("rs.next():"+ rs.next()); -rs.next():true 하지만 주석 처리 했으므로 
			//if문 안에서 true값이 입력됨.
			if(rs.next()) { //어자피 조회됳 건은 1건이니 while대신 if문을 사용하자.
				rbVO = new BookVO();
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_name(rs.getString("b_name"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_info(rs.getString("b_info"));
				rbVO.setB_img(rs.getString("b_img"));
			}
			//
			System.out.println("rbVO: "+rbVO.getB_info());
		} catch(SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString()); //sql문의 오류가 난 것을 오라클의 에러로 확인하자.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rbVO;
	}

	//전체 조회 구현
	public List<BookVO> bookList(BookVO pbVO){ //all, b_no, b_name
		System.out.println("bookList() 호츨 성공 ");
		List<BookVO> bookList = new ArrayList<BookVO>();//bookList.size() = 0
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select b_no, b_name, b_author, b_publish, b_img");
			sql.append(" from book2020						   ");
			sql.append(" ORDER BY b_no desc");
			con = dbmgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			BookVO rbVO = null;
			while(rs.next()) { //연동이 되어 얻어온 값을 꺼내와서
				rbVO = new BookVO(); //여기에 값을 담고
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_name(rs.getString("b_name"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_img(rs.getString("b_img"));
				bookList.add(rbVO);
			}
			System.out.println("bookList.size(): "+bookList.size());//처리가 되었다면 bookList의 사이즈가 나올것이니 한번 찍어보자.
		} catch(SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString()); //sql문의 오류가 난 것을 오라클의 에러로 확인하자.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}////////////////////end bookList
	
	
	public static void main(String[] args) {
		BookDao bd = new BookDao();
		BookVO pbVO = new BookVO();
		List<Integer> bnos = new ArrayList<Integer>();
		bnos.add(9);
		bnos.add(8);
		bnos.add(7);
		pbVO.setBnos(bnos);
		int result = bd.bookDelete(pbVO);
		System.out.println("result:"+result);
		
//		pbVO.setB_no(3);
//		BookVO rbVO = bd.bookDetail(pbVO);
//		System.out.println(rbVO.getB_img());
		
//		BookVO pbVO = new BookVO();
//		pbVO.setB_name("1");
//		pbVO.setB_author("2");
//		pbVO.setB_publish("3");
//		pbVO.setB_info("3");
//		int result = 0;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
