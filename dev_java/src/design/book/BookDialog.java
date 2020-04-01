package design.book;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDialog extends JDialog implements ActionListener{
	/*
	 * 자녀 창에서 저장(입력) 성공했을때 부모창의 refreshData를 호출해야 한다.
	 * 그런데 원본에서 재사용해야 하므로 set메소드의 파라미터로 받아서 사용할 것이다.
	 * 다른 메소드에서 ba를 사용해야 하니까 전역변수로 선언한 다음 초기화를 반드시 할것.
	 */
	BookApp ba = null;
	boolean isView = false;
	
	//수정 전 전에 있던 값을 미리 확인하기 위해서 이전값을 받아오기 위한 변수 선언.
	String name = null;
	String autor = null;
	String publish = null;
	String info = null;
	
	JLabel jlb_name = new JLabel("책제목");
	JTextField jtf_name = new JTextField(20); //(20):사이즈를 20정도 주자.
	JLabel jlb_autor = new JLabel("저자");
	JTextField jtf_autor = new JTextField(20); 
	JLabel jlb_publish = new JLabel("출판사");
	JTextField jtf_publish = new JTextField(20); 
	JLabel jlb_info = new JLabel("도서소개");
	JTextArea jta_info = new JTextArea(8,25); 
	JScrollPane jsp_info = new JScrollPane(jta_info);
	
	JPanel jp_center = new JPanel();
	JPanel jp_south = new JPanel();
	
	JButton jbtn_save = new JButton("저장");
	JButton jbtn_close = new JButton("닫기");
	JScrollPane jsp = new JScrollPane(jp_center);
	
	//도서이미지 추가해보기
	String imgPath = "src\\design\\book\\";
	ImageIcon icon = null;
	JLabel jlb_img = new JLabel(icon);
	
	JButton jbtn_file = new JButton("파일찾기");
	JTextField jtf_file = new JTextField(30);
	JFileChooser jfc = new JFileChooser(); //파일을 찾을  수 있는 파일탐색기를 제공하주는 클래스!!!!!!!!!!!
	
	Container cont = this.getContentPane(); //컨테이너를 얻는다.
	
	BookVO rbVO = null;
	
	//인스턴스화를 하면 생성자 호출이 일어남.
//	public BookDialog(boolean b) { //화면을 띄우기 위한 값을 받아서 오자.
//		this.isView = b;
//	}
//	public BookDialog(boolean b, String title) { //생성자로 title을 넘기면 시차가 발생하여 재대로 구동 x
//		this.isView = b;						 //시점 차이로 인해 필요없는 생성자가 됨.
//		this.title = title;
//	}
	public BookDialog() {
		//initDisplay안에 있으면 반복해서 일어나므로 오류..
		jbtn_file.addActionListener(this);
		jbtn_save.addActionListener(this);
		jbtn_close.addActionListener(this);
	}
	//입력과 수정시에는 컬럼값을 수정 가능하도록 하고
	//조회시에는 불가능하게 하는 메소드를 선언해 봐요.
	public void setEditable(boolean isOk) { //한번에 모든 커서의 상태를 관리하자. - 부모화면에서 관리하자.
		jtf_name.setEditable(isOk); //책제목
		jtf_autor.setEditable(isOk); //저자
		jtf_publish.setEditable(isOk); //출판일
		jta_info.setEditable(isOk); //도서 소개
	}
	/************************************************************************
	 * public void set(String title, boolean isView, boolean editable, Map<String, Object> rMap){}
	 * @param title 다이어로그창 제목
	 * @param isView 다이어로그창 view 여부
	 * @param editable 입력 컴포넌트 수정여부
	 * @param rMap 조회결과를 담은 주소번지
	 ************************************************************************/
	//여기 메소드로 제목과 boolean타입의 flag의 상태를 받아오자. -> 즉, 생성자보자 메소드로 초기화하는 것이 더 확실하다.
	public void set(String title, boolean isView, boolean editable, Map<String, Object> rMap, BookApp ba) {
		this.ba = ba;
		setValue(rMap); // 제목 텍스트에 수정, 상세정보 시에 값을 넣어주기 위해서 생성한 함수에 rMap 데이터를 넣어주자
		setEditable(editable);		//상태정보를 받아서 seteditable에 넣어서 한꺼번에 커서의 상태를 제어하자.
//		if(rMap!=null) { //setValue에서 실행해야하는 구문
//			jtf_title.setText(rMap.get("b_title").toString()); //이전의 값을 띄우고 수정하자.
//		}
		this.setTitle(title);		//함수안에서 제목을 받아와서 정하고
		initDisplay();				//그리고 화면를 띄우자.
		this.setVisible(isView);	//화면창이 열리는지 안열리는지 결정하자
	
	}
	
	//BookVO의 타입을 Map타입 대신에 받아보자 
	/******************************************************************
	 * @param title 입력|수정|상세조회
	 * @param isView true : 화면에 보여줌, false : 안보여줌.
	 * @param editable true : 수정하게 해줌, false : 수정 못하게 함
	 * @param rbVO null이면 값 없음, rbVO[new BookVO]이면 값 있음
	 * @param ba BookApp의 주소번지 원본을 가지고 있음.
	 ******************************************************************/
	public void set(String title, boolean isView, boolean editable, BookVO rbVO, BookApp ba) {
		this.ba = ba;
		this.rbVO = rbVO;
		setValue(rbVO); 
		setEditable(editable);		
		this.setTitle(title);		
		initDisplay();				
		this.setVisible(isView);	
	}
	
	//화면 그리기
	public void initDisplay() {
		//TextArea의 자동 줄바꿈 처리해보기 - 스크롤바의 수평을 사용하지 않게 된다.
		jta_info.setLineWrap(true);
		//속지의 Layout이 FlowLayout이었던 것을 null로 초기화함 - SetBounds로 직접 배치할 예정이기 때문이지.
		jp_center.setLayout(null); //좌표값으로 배치할 것이다.
		
		jp_south.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_close);
		
		//화면에 배치할 때 setBound(x좌표,y좌표,가로,세로) - 앞에 두 자리가 시작점 좌표 20,20 - 시작점,
//													세/네번째가 가로세로 결정. 100, 20 - 가로길이와 세로길이
		jlb_name.setBounds(20,20,100,20);
		jtf_name.setBounds(120, 20, 150, 20);
//		jtf_title.setEditable(false); 커서를 없애주세요. - set()에서 처리할 것입니다.
		jp_center.add(jtf_name);
		jp_center.add(jlb_name);
		
		jlb_autor.setBounds(20,45,100,20);
		jtf_autor.setBounds(120, 45, 150, 20);
		jp_center.add(jtf_autor);
		jp_center.add(jlb_autor);
		
		jlb_publish.setBounds(20,70,100,20);
		jtf_publish.setBounds(120, 70, 150, 20);
		jp_center.add(jtf_publish);
		jp_center.add(jlb_publish);
		
		jlb_info.setBounds(20,95,100,20);
		jsp_info.setBounds(120, 95, 300, 160);
		jp_center.add(jsp_info);
		jp_center.add(jlb_info);
		
		//판넬에 이미지 붙혀서 jframe에 붙혀보자.
		jbtn_file.setBounds(20,260, 90, 20);
		jtf_file.setBounds(120,260, 300, 20);
		jlb_img.setBorder(BorderFactory.createEtchedBorder()); //라벨(그림)의 테두리를 그려주자.
		jlb_img.setBounds(120, 285, 300, 360);
		jp_center.add(jbtn_file);
		jp_center.add(jtf_file);
		jp_center.add(jlb_img);
		
//		this.setTitle(title); 		//set()에서 처리할 것입니다.
		this.add("Center",jsp);
		this.add("South",jp_south);
		this.setSize(500, 720);
		//부모창에서 선택한 버튼에 따라 화면을 제어하고싶다. - 변수사용			
//		this.setVisible(isView); 	//함수에서 처리, this.setVisible(true);, 여기에 ture가 아닌 변수를 사용하자 - 기본이 false로 설정
	}
	
	//조회된 결과를 BookDialog에 초기화하기
	//새로 입력하는 경우에는 빈 문자열로 초기화하기.
	/***********************************************************************
	 * BookApp에서 조회된 1건을 BookDialog에 초기화함.
	 * @param rmap - 조회된 1건을 Map으로 받은 경우 
	 ***********************************************************************/
	public void setValue(Map<String, Object> rmap) {
		//입력을 위한 화면 설정 - 모든 값을 빈문자열로 셋팅한다.
		if(rmap == null) {
			setB_Name("");
			setB_Author("");
			setB_Publish("");
			setB_Info("");
		} 
		//상세 조회와 수정시는 파라미터로 받은 값으로 셋팅한다. - Map으로 처리
		else { 
			setB_Name(rmap.get("b_name").toString());
			setB_Author(rmap.get("b_author").toString());
			setB_Publish(rmap.get("b_publish").toString());
			setB_Info(rmap.get("b_info").toString());
		}
	}
	
	/************************************************************
	 * BookApp에서 조회된 1건을 BookDialog에 초기화함.
	 * @param rbVO - 조회된 1건을 rbVO으로 받은 경우 
	 ************************************************************/
	//BookVO rbVO를 받는 경우 setValue로 하자.
	public void setValue(BookVO rbVO) {  
		if(rbVO == null) {
			setB_Name("");
			setB_Author("");
			setB_Publish("");
			setB_Info("");
			setB_Img(""); //새로 이미지를 초이스 해야 하니깐 비워둔다.
		} else {
			//처음 설계시에는 맵으로 했던걸 어제 bVO로 추가 처리함.
			
			setB_Name(rbVO.getB_name());
			setB_Author(rbVO.getB_author());
			setB_Publish(rbVO.getB_publish());
			setB_Info(rbVO.getB_info());
			setB_Img(rbVO.getB_img());
		}
	}
	
	//각 컬럼의 값들을 설정하거나 읽어오는 getter/setter메소드 입니다.
	public String getB_Name() { return jtf_name.getText();}
	public void setB_Name(String name) { jtf_name.setText(name);}
	
	public String getB_Author() { return jtf_autor.getText();}
	public void setB_Author(String author) { jtf_autor.setText(author);}
	
	public String getB_Publish() { return jtf_publish.getText();}
	public void setB_Publish(String publish) { jtf_publish.setText(publish);}
	
	public String getB_Info() { return jta_info.getText();}
	public void setB_Info(String info) { jta_info.setText(info);}
	
	//JLabel에 도서 이미지 출력하기
	public void setB_Img(String img) { 
		icon = new ImageIcon(imgPath+img);
		//원본의 이미지 크기 정보를 가져온다.
		Image originImg = icon.getImage(); //원래 이미지의 크기를 가져오자 
		//원본의 이미지를 가져와서 내가 원하는 크기의 이미지로 재정의해주자. 원본은 바뀌지 않으니 다시 갱신해주자.
		//																┌> 이미지를 맞춰주자.
		Image changrImg = originImg.getScaledInstance(300, 380, Image.SCALE_SMOOTH); //원래 이미지를 내가 원하는 크기의 이미지로 바꾸자
		//원본의 이미지 아이콘이 icon이었는데 이제 바뀐 이미지의 아이콘으로 cicon으로 변경해주자.
		//원래있던 이미지 아이콘을 버리고 새로운 ImageIcon객체를 인스턴스화 하였다.
		ImageIcon cicon = new ImageIcon(changrImg); // 그리고 바뀐이미지를 다시 생성하고
		//JLabel에 setIcon이라는 메소드의 파라미터로 넘겨서 적용시킨다.
		jlb_img.setIcon(cicon); //생성한 이미지 아이콘을 라벨에 붙히자.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); //이벤트 소스의 라벨을 읽어온다.
		JOptionPane.showMessageDialog(ba, "이벤트 소스 라벨:"+command);
		if(jbtn_file == e.getSource()) {
			int ir = jfc.showOpenDialog(this);
			//						┌> ok의 버튼을 누르면 파일 탐색기 가 열린다.
			if(ir == JFileChooser.APPROVE_OPTION) {
				//절대경로를 가져온다. <-> 상대경로:내가 바라보는 위치를 기준으로 적음
				//D:\59기수업\01.자바_수업
				//선택한 파일을 file 객체에 대입
				File myFile = jfc.getSelectedFile(); // 선택한 파일을
				jtf_file.setText(myFile.getAbsolutePath()); //텍스트 창에 보여주자.
				
				String cion = myFile.getAbsolutePath();
				icon = new ImageIcon(cion);
				//원본의 이미지 크기 정보를 가져온다.
				Image originImg = icon.getImage(); //원래 이미지의 크기를 가져오자 
				//원본의 이미지를 가져와서 내가 원하는 크기의 이미지로 재정의해주자. 원본은 바뀌지 않으니 다시 갱신해주자.
				//																┌> 이미지를 맞춰주자.
				Image changrImg = originImg.getScaledInstance(300, 380, Image.SCALE_SMOOTH); //원래 이미지를 내가 원하는 크기의 이미지로 바꾸자
				//원본의 이미지 아이콘이 icon이었는데 이제 바뀐 이미지의 아이콘으로 cicon으로 변경해주자.
				//원래있던 이미지 아이콘을 버리고 새로운 ImageIcon객체를 인스턴스화 하였다.
				ImageIcon cicon = new ImageIcon(changrImg); // 그리고 바뀐이미지를 다시 생성하고
				//JLabel에 setIcon이라는 메소드의 파라미터로 넘겨서 적용시킨다.
				jlb_img.setIcon(cicon); //생성한 이미지 아이콘을 라벨에 붙히자.
				cont.revalidate(); // 화면을 갱신한다. 컨데이너가 갱신된다.
			}
		}
		//저장버튼을 누른거니?
		else if("저장".equals(command)) {
			System.out.println("저장눌림");
			//insert here - 입력인지 수정인지 어떻게 구분하지?
			int result = 0;
			//rbVO는 BookVO타입으로 BookApp에서 이벤트 발생시 set메소드의 
			//4번째 파라미터로 넘어온 값이다.
			//이 주소번지가 null이면 조회를 하지 않았다는 뜻이고
			//null이 아니면 죄회를 하였다는 뜻이다.
			if(rbVO != null) { //수정처리하기
				BookVO pbVO = new BookVO();
				pbVO.setCommand("update");
				pbVO.setB_no(rbVO.getB_no()); 
				pbVO.setB_name(getB_Name()); // 도서명 가져오기
				pbVO.setB_author(getB_Author()); // 저자 가져오기
				pbVO.setB_publish(getB_Publish()); // 출판사 가져오기
				pbVO.setB_info(getB_Info()); // 도서설명 가져오기
				result = ba.bDao.bookUpdate(pbVO);
				JOptionPane.showMessageDialog(ba, result);
				
			} else { //입력처리하기
				BookVO pbVO = new BookVO();
				pbVO.setB_name(getB_Name()); //입력한 도서명 가져오기
				pbVO.setB_author(getB_Author()); //입력한 저자 가져오기
				pbVO.setB_publish(getB_Publish()); //입력한 출판사 가져오기
				pbVO.setB_info(getB_Info()); //입력한 도서설명 가져오기
				result = ba.bDao.bookInsert(pbVO);
				JOptionPane.showMessageDialog(ba, result);
			}
			ba.refreshData();
			this.dispose();
		}
		//닫기 버튼을 누른거니?
		else if("닫기".equals(command)) {
			this.dispose();
		}
	}
	
	public static void main(String[] args) {
		BookDialog bd = new BookDialog();
		bd.set("입력", true, true, new BookVO(), null);
		bd.initDisplay();
	}
	
	//상세조회시 책이미지를 넣어보자 - DB에다가는 이미지를 넣으면 된다.
	
}
