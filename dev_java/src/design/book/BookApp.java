package design.book;
/*
 * main에서 결정된 정보들
 * 예를 들면 입력버튼 , 수정버튼, 
 * 그리고 또... 한 건을 조회한 결과를 담고 있는
 * BookVO객체 혹은 Map객체 까지도 담을 수 있는 Set메소드를 추가하시오.
 * 
 * BookMain을 인스턴스화 할 때 전역변수에 선언된 BookDialog도 같이 인스턴스화를 한다.
 * 이떄 파라미터로 넘어간 boolean, String 은 값이 
 * 이미 결정된 상태이므로 아무리 버튼을 바꾸어 
 * 생성했어도 title값은 변하지 않는 것이다.
 * 생서자가 호출되는 시점과 이벤트가 감지되는 시점에서 시점차이에 시간차이가 발생한다.?
 * public void set(제목, 수정유무, Map)
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class BookApp extends JFrame implements ActionListener{ //JFrame을 상속받았기 때문에 JFrame을 따로 선언안해도 this를 사용해서 사용가능
	//선언부

	//DB커넥션 연결하기
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	//이미지 경로 추가
	String imgPath ="src\\design\\book\\";
	URL bookURL = getClass().getResource("bookicon.png");
	ImageIcon bicon = new ImageIcon(bookURL);
	
	//메뉴바 추가하기
	JMenuBar jmb_book = new JMenuBar();
	
	JMenu 	 	jm_file  = new JMenu("File");
	JMenuItem 	jmi_db	 = new JMenuItem("DB Connection");
	JMenuItem 	jmi_open = new JMenuItem("Open File");
	JSeparator  js_file  = new JSeparator(); //메뉴 아이템 사이의 구분선을 넣어준다.
	JMenuItem 	jmi_exit = new JMenuItem("Exit");
	
	JMenu 	  	jm_edit = new JMenu("Edit");
	JMenuItem 	jmi_all = new JMenuItem("전체조회");
	JMenuItem 	jmi_sel = new JMenuItem("상세조회",new ImageIcon(imgPath+"detail.gif"));
	JMenuItem 	jmi_ins = new JMenuItem("입력",new ImageIcon(imgPath+"new.gif"));
	JMenuItem 	jmi_upd = new JMenuItem("수정",new ImageIcon(imgPath+"update.gif"));
	JMenuItem 	jmi_del = new JMenuItem("삭제",new ImageIcon(imgPath+"delete.gif"));
	
	static BookApp ba = null;
	String title = null;
	//jp_north속지는 JFrame의 북쪽에 배치
	JPanel jp_north = new JPanel();
	//버튼를 jp_north(북쪽 속지)에 배치(붙힘) - 차례대로 배치 - 왼쪽부터

	JToolBar jtbar = new JToolBar(); //버튼에 이미지 붙히기
	JButton jbtn_db = new JButton("DB Connection");
	JButton jbtn_all = new JButton("전체조회");
	JButton jbtn_sel = new JButton();
	JButton jbtn_ins = new JButton();
	JButton jbtn_upd = new JButton();
	JButton jbtn_del = new JButton();
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER);
	
	//생성자
	//파라미터가 없는 생성자는 디폴트 생성자로 지원해주지만 있는 경우는 예측불가이므로 지원불가함
//	BookDialog bd = new BookDialog(true); //블리언의 값을 넘겨주는 생성자. ->의미x set메소드로 받을것이다. 밑의 생성자도 같음
//	BookDialog bd = new BookDialog(false, title); //이렇게 하면 생성은 미리 title이 null인 상태로 bd가 생성되서 event가 일어났을떄 
												  //title을 바뀌므로 이벤트를 일으켜도 제목이 바뀐값으로 
												  //이벤트가 일어나는 것이 아니라 그냥 초반에 null인값인 제목을 가지고 생성한다. 
	BookDialog bd = new BookDialog();
	TimeClient tc = null;
	
	String[] cols = {"도서번호","도서명","저자","출판사","그림 이름"};
	String[][] data = new String[0][5];
	DefaultTableModel dtm_book = new DefaultTableModel(data, cols);
	JTable jtb_book = new JTable(dtm_book);
	JScrollPane jsp_book = new JScrollPane(jtb_book);
	BookController bCtrl = new BookController(this); //6가지의 버튼의 이벤트 처리가 필요하다. - 인스턴스화로 사용하자.
	
	BookDao bDao = new BookDao();
	
	//이베트 소스와 이벤트 핸들러 클래스 연결하기
	public void eventMapping() { //기본 클래스
		//db연결 버튼 이벤트 처리
		jbtn_db.addActionListener(new ActionListener() { //익명클래스 -> 이름이 없는 클래스 -> 내부 클래스 BookApp$1.class로 생성
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				dbActionPerformed(ae);
			}
		});
		
		//db연결 메뉴 아이템 이벤트 처리
		jmi_db.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				dbActionPerformed(ae);
			}
		});
//		jmi_all.addActionListener(this); 2번 호출이 일어나요 없애줘요. - initDisplay안에 있습니다.
//		jbtn_all.addActionListener(this);
	}
	
	//버튼의 이벤트 처리를 여기서 모두 처리한다.
	protected void dbActionPerformed(ActionEvent ae) {
		System.out.println("db연결 테스트");
		Connection con = null;
		con = dbMgr.getConnection();
		System.out.println(con.toString());
	}

	//화면 그리기
	public void initDisplay() {
		Runnable rn = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		
		//버튼을 단축키로 조절하도록 하기
		jm_file.setMnemonic('F'); //단축키 F ->메뉴바가 나온다.
		jm_edit.setMnemonic('E');
		//메뉴 안에 메뉴 아이템을 넣고 메뉴를 메뉴바에 넣자. - 메뉴아이템 > 메뉴 > 메뉴바
		jm_file.add(jmi_db);
		jm_file.add(jmi_open);
		jm_file.add(js_file); //구분선
		jm_file.add(jmi_exit);
		
		jm_edit.add(jmi_all);
		jm_edit.add(jmi_sel);
		jm_edit.add(jmi_ins);
		jm_edit.add(jmi_upd);
		jm_edit.add(jmi_del);
		
		jmb_book.add(jm_file);
		jmb_book.add(jm_edit);
		
		this.setJMenuBar(jmb_book);
		
		//실제로 타임서버로부터 시간정보를 듣기는 TimeClient에서 진행되지만 
		//생성자의 파라미터를 통해서 BookApp jlb_time원본의 주소번지를
		//넘겼으므로 TimeClient에서는 원본에 직접 써주면 화면에 보임.
		tc = new TimeClient(jlb_time);
		tc.start();
		
		//아래코드가 JFrame의 자원을 회수함
		//부모자원이 회수될 때 JDialog도 같이 회수됨.
		//해당 창을 닫으면 자원이 회수된다. - 콘솔창의 빨간 실행 버튼이 비활성화됨.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //부모창이 닫히면 자식창도 닫히자.
		jbtn_ins.addActionListener(this);
		jbtn_sel.addActionListener(this);
		jbtn_all.addActionListener(this);
		jbtn_upd.addActionListener(this);
		jbtn_del.addActionListener(this);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//insert here
		this.setTitle("도서관리시스템");
		
//		jp_north.add(jbtn_all);
//		jp_north.add(jbtn_sel);
//		jp_north.add(jbtn_ins);
//		jp_north.add(jbtn_upd);
//		jp_north.add(jbtn_del);
//		this.add("North",jp_north);
		
		//버튼에 이미지를 넣어보자
		jbtn_sel.setIcon(new ImageIcon(imgPath+"detail.gif"));
		jbtn_sel.setToolTipText("상세조회"); // 마우스를 버튼에 가져가면 풍선 도움말로 텍스트가 보인다.
		
		jbtn_ins.setIcon(new ImageIcon(imgPath+"new.gif"));
		jbtn_ins.setToolTipText("입력");
		
		jbtn_upd.setIcon(new ImageIcon(imgPath+"update.gif"));
		jbtn_upd.setToolTipText("수정");
		
		jbtn_del.setIcon(new ImageIcon(imgPath+"delete.gif"));
		jbtn_del.setToolTipText("삭제");
		
		//north속지가 아닌 이미지를 붙힌 버튼을 North에 추가하자
		jtbar.add(jbtn_db);     
		jtbar.add(jbtn_all);     
		jtbar.add(jbtn_sel);
		jtbar.add(jbtn_ins);
		jtbar.add(jbtn_upd);
		jtbar.add(jbtn_del);
		this.add("North",jtbar);
		this.add("Center", jsp_book);
		this.add("South",jlb_time);
		this.setIconImage(bicon.getImage()); //메뉴의 타이틀 옆에 이미지를 넣는다.
		this.setSize(700, 500);
		this.setVisible(true);
//		bd.isView = true; //BookApp을 실행시키기 위해서 true값을 전달. -> 위에 생성자에 true을 넘겨주어 해결하자. 
						  //-> 근데 생성자로 넘겨서 실행시키면 바뀐 제목과 함께 생성하기 곤란하다. 그러니 함수의 파라미터로 넘겨서 해결
//		bd.initDisplay(); //여기서 실행시키면 제목과 내용을 모두 가지고 있음.set함수에서 화면창을 띄어주자.
	}
	//메인메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true); //윈도우의 실행 창의 스킨을 윈도우타입으로 설정
		//앱을 실행하면 서버를 따로 실행하지 않아도 같이 바로 실행 그러나 화면보다 먼저 실행되어야하므로 앞에다가 인스턴스화해주자.
		TimeServer ts = new TimeServer();//클라이언트의 정보를 담고있는 client를 넘기자. 
		ts.initDisplay(); 
		Thread th = new Thread(ts); 
		th.start();
		//insert here
		ba = new BookApp();
		ba.initDisplay(); //화면 처리 부분
		ba.eventMapping();//이벤트 연결 처리  - 익명클래스로 처리[함수중심 코딩]
	}

	//JButton에 대한 이벤트를 지원하는 인터페이스가 ActionListener임.
	//클래스 뒤에 implements 할것
	//ActionListener에 정의된 추상메소드를 재정의할 것
	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 감지된 클래스의 주소번지 받기
		Object obj = e.getSource();
		//입력버튼을 누른거니?
		if(jbtn_ins == obj) {
			System.out.println("입력호출 성공");
			//insert here
//			Map<String , Object> rMap = null; BookVO로 넘겨받아보자.
			BookVO bVO = null;
			bd.set("입력하기",true,true, bVO, ba); //입력일때는 입력만하는 됨. 넘길값이 없음.
//			bd.isView = true;			//set 함수로 상태값을 받을것임
//			bd.title = jbtn_ins.getText(); //title의 값을 이렇게 받지 말고 메소드로 받자.
			// initDisplay를 호출한 이유는 setTitle("입력")과 setVisible(true)
			//때문이었다. 그런데 그 둘은 set메소드로 이전하였다.
//			bd.initDisplay(); 필요없음 메인에서 실행하자. - 여기서 실행하면 제목만 띄어짐
		}
		else if(jbtn_upd == obj) {//수정시에는 먼저 기본 정보를 조회하고 화면 이동 처리를 해야됨.
			System.out.println("수정호출 성공");
			//insert here
			//select처리를 한 후 한 개 로우를 받아서 Map에 저장
//			Map<String , Object> rMap = null;
//			rMap = new HashMap<>();
//			rMap.put("b_title", "자바의 정석"); //이전의 책의 이름이 들어있어야된다.
			
			BookVO rbVO = null;
			int index = jtb_book.getSelectedRow();
			if(index >= 0) {// 선택한 로우가 있다.
				BookVO pbVO = new BookVO();
				pbVO.setCommand("detail"); 
				int b_no = Integer.parseInt(dtm_book.getValueAt(index, 0).toString());
				pbVO.setB_no(b_no);
				rbVO = bCtrl.send(pbVO);
			} else { //선택한 로우가 없다.
				JOptionPane.showMessageDialog(this, "수정한 데이터를 선택하세요.");
				return;
			}
			bd.set("수정하기",true, true, rbVO, ba);
		}
		else if(jbtn_sel == obj) {
			System.out.println("상세조회 호출 성공");
			//insert here
			Map<String , Object> rMap = null;
			int indexs[] = jtb_book.getSelectedRows(); //선택된 열의 인덱스가 담긴다.
			if(indexs.length == 0) {//정상적으로 처리가 되지 않았다면 -> 선택된 열이 없다면.
				JOptionPane.showMessageDialog(this, "상세조회할 로우를 선택하세요.");
				return; //정상적으로 처리가 되지 않았으니 return을 하여 반복문을 종료 시키자.
				
			} else {//정상적으로 처리가 되었다면 
				int b_no = Integer.parseInt(dtm_book.getValueAt(indexs[0] , 0).toString()); //getValueAt(선택한 로우 , 선택한 열)
				System.out.println("b_no : "+b_no); //2
				BookVO pbVO = new BookVO();
				pbVO.setCommand("detail");
				pbVO.setB_no(b_no); 
				System.out.println("command:"+pbVO.getCommand());
				BookVO rbVO = bCtrl.send(pbVO);
				bd.set("상세조회",true, false, rbVO, null); //화면을 부른다. BookDialog
			}
//			bd.set(jbtn_sel.getText(),true, false, rMap, null); //화면을 부른다. BookDialog
		}
		else if(jbtn_del == obj) {
			System.out.println("삭제 호출 성공");
			int indexs[] = jtb_book.getSelectedRows();
			if(indexs.length == 0) {
				JOptionPane.showMessageDialog(this, "삭제할 로우를 선택하세요.");
				return; 
			} else {
				List<Integer> bnos = new ArrayList<Integer>(); // 경합이 일어나는것이 아니니 어레이리스트 사용.
//				for (int i = 0; i < indexs.length; i++) {
				for(int i = 0; i < dtm_book.getColumnCount(); i++) {
					//선택된 로우인지 체크
					if(jtb_book.isRowSelected(i)) { //하나가 아닌 여러개를 삭제할 수도 있자나?
						int b_no = Integer.parseInt(dtm_book.getValueAt(indexs[i] , 0).toString());
						bnos.add(b_no);
					}
				}
				BookVO pbVO = new BookVO();
				pbVO.setCommand("delete"); //Commend = 'delete'을 넘기고
				pbVO.setBnos(bnos); //삭제할 행이 담긴 리스트를 VO에 입력
				BookVO rbVO = new BookVO();
				rbVO = bCtrl.send(pbVO);
				int result = rbVO.getResult();
				if(result>0) {
					JOptionPane.showMessageDialog(this, "삭제되었습니다.");
					refreshData();
				} else {
					JOptionPane.showMessageDialog(this, "실패하였습니다.");
					refreshData();
				}
			}
		}
		else if(jbtn_all == obj) {
			System.out.println("전체조회 호출 성공");
			//insert here
			//전체조회는 공통적으로 ? 
			refreshData();
		}
	}
	
	//사용자 화면에 보여지는 함수. - 전제조회의 결과를 하는 메소드
	public void refreshData() {
		System.out.println("refreshData 호출 성공");
		List<BookVO> bookList = null;
		BookVO pbVO = new BookVO(); // 파라미터로 보내지는 값
		pbVO.setCommand("all");
		bookList = bCtrl.sendALL(pbVO); //BookController안에 sendALL함수 안에 보내서 디비 연동을 해보자.
		//기존에 조회된 결과를 출력한 화면은 삭제처리한다.
		while(dtm_book.getRowCount()>0) { //BookList.size()의 숫자와 동일 
			dtm_book.removeRow(0); //계속 row수 만큼 반복하면서 첫번째로우 즉,[0번] 계속 지워준다.
		}
		//삭제한 후 다시 출력하기
		for (int i = 0; i < bookList.size(); i++) { //사이즈 만큼 반복하면서
			BookVO bVO = bookList.get(i); //리스트의 값을 가져와 인스턴스하고
			Vector<Object> v = new Vector<Object>(); //벡터에 추가하소
			v.add(bVO.getB_no());				//add해서
			v.add(bVO.getB_name());
			v.add(bVO.getB_author());
			v.add(bVO.getB_publish());
			v.add(bVO.getB_img());
			//JTable에 추가하는 것이 아니다. JTable은 양식일 뿐이고 실제로 데이터를 갖는 클래스는 DefaultTableModel이다
			//부트스트랩등 다른 툴에서는 DataSet의 기능을 지원 => DefaultTableModel과 같은 기능을 하는 아이.
			//한개로우는 Vector에 담고 그 벡터를 for문 안에서 반복 추가해줌.
			dtm_book.addRow(v); 			//행을 추가하자.
		}
	}//////////////////end refreshData

}
