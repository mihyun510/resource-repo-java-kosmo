package design.book;

import java.util.List;

public class BookController {
	private static final String _DEL = "delete"; //삭제하기
	private static final String _SEL = "detail"; //상세조회
	private static final String _INS = "insert"; //입력하기
	private static final String _UPD = "update"; //수정하기
	private static final String _ALL = "all"; 	 //전체조회
	BookApp bookApp = null;
	
	public BookController() {}
	
	public BookController(BookApp bookApp) {
		this.bookApp = bookApp;
	}

	public BookVO send(BookVO pbVO) {
		BookVO rbVO = new BookVO();
		String command = pbVO.getCommand();
		//insert here
		if(_DEL.equals(command)) { //삭제 버튼을 눌렀겠구나?
			int result = 0;
			result = bookApp.bDao.bookDelete(pbVO);
			rbVO.setResult(result);
		}
		else if(_INS.equals(command)) { // 입력버튼을 눌렀구나?
			//INSERT INTO book2020(b_no, b_name, b_author, b_publish, b_info)
			//VALUES(seq_book_no.nextval,?,?,?,?)
			int result = 0;
			result = bookApp.bDao.bookInsert(pbVO);
			rbVO.setResult(result);
		}
		else if(_UPD.equals(command)) { // 수정 버튼을 눌렀구나?
			//UPDATE book2020 SET b_name=?, b_author=?, b_publish=?
			// WHERE b_no=2
			int result = 0;
			result = bookApp.bDao.bookUpdate(pbVO);
			System.out.println("result"+result);
			rbVO.setResult(result);
		}
		else if(_SEL.equals(command)) { // 상세조회를 눌렀구나?
			//1건이 검색될 것이다.
			//SELECT b_no, b_name, b_author, b_publish FROM book2020
			// WHERE b_no=3
			rbVO = bookApp.bDao.bookDetail(pbVO);
		}
		return rbVO;
	}
	
	//전체조회
	public List<BookVO> sendALL(BookVO pbVO){ 
		//n건이 검색될 것이다.
		System.out.println("sendALL 호출 성공");
		List<BookVO> bList = null;
		String commend = pbVO.getCommand(); //버튼의 이름을 받아올겁니다. - all이 전달된다.
		
		if(_ALL.equals(commend)) { //너 전체조회를 누른거니?
//			BookVO rbVO = null; 조회할 때 결정되어서 나온다. 즉, 필요가 없어요.
			//사용자의 선택은 읽었지만 여기서 DB연동은 하지 않는다. BookDao에서 연동 할 예정이다.
			bList = bookApp.bDao.bookList(pbVO); //디비연동을 위해서 받은 파라미터를 보내자.
		}
		return bList;
	}
}
