package study.sungjuk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Sungjuk implements ActionListener{
	//선언부
	JFrame jf_sungjuk 	= new JFrame();
	JLabel jlb_su 		= new JLabel("인원수");
	JLabel jlb_inwon 	= new JLabel("명");
	JTextField jtf_inwon = new JTextField(15);
	JButton jbtn_data 	= new JButton("데이터 가져오기");
	JButton jbtn_account = new JButton("성적처리");
	JButton jbtn_exit 	= new JButton("종료");
	JPanel jp_north		= new JPanel();
	JPanel jp_south		= new JPanel();
	//테이블 처리 코드 추가
	String			cols[]		= {"이름", "자바", "오라클","HTML","총점", "평균", "석차"};
	String			data[][]	= null;
	DefaultTableModel dtm_sj	= null; //3번 쌓기. 테이블 표 위에 데이터 표가 올라감.
	JTable			jt_sj		= null; //2번 쌓기. 스크롤 위에 테이블 표가 올라감
	JScrollPane		jsp_sj		= null; //1번 쌓기. 스크롤 판넬이 제일 마지막
	//사용자가 입력한 인원수를 담을 변수 입니다.
	//전역변수로 한 이유는 인원수를 듣기는 jtf_inwon에서 엔터쳤을 때 값이 결정됩니다.
	//그 때 결전된 3이 jbtn_account에서도 필요합니다.
	//왜냐하면 총점을 기준으로 석차를 구하기로 결정되었으므로 총점과 석차를 같이 관리할 
	//2차 배열을 선언하였기 때문입니다.
	int inwon = 0;
	//생성자
	Sungjuk() {
		start();
	}
	//총점을 구하는 메소드 구현
	public double total(int inwon) {
		return 0.0;
	}
	//평균을 구하는 메소드 구현
	public double average(int total) {
		return 0.0;
	}
	//석차를 구하는 메소드 구현
	public int[] ranking() {
		return new int[2];
	}
	//이벤트 소스와 이벤트 처리 클래스를 매핑
	public void start() {
		//엔터 쳤을때 JVM이 감지하고 콜백메소드를 호출하자.
		jtf_inwon.addActionListener(this);
		//데이터 가져오기 이벤트 연결
		jbtn_data.addActionListener(this);
		//성적처리 이벤트 연결
		jbtn_account.addActionListener(this);
		//종료 이벤트 연결
		jbtn_exit.addActionListener(this);
	}
	//화면처리부
	public void initDisplay() {
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));//북쪽 디자인
		jp_north.add(jlb_su);
		jp_north.add(jtf_inwon);
		jp_north.add(jlb_inwon);
		jf_sungjuk.add("North",jp_north);
		jp_south.setLayout(new FlowLayout(FlowLayout.RIGHT)); //서쪽 디자인
		jp_south.add(jbtn_data);
		jp_south.add(jbtn_account);
		jp_south.add(jbtn_exit);
		jf_sungjuk.add("South",jp_south);
		
		jf_sungjuk.setTitle("성적처리 프로그램Ver1.0");
		jf_sungjuk.setSize(400,300);
		//jf_sungjuk.pack();//디폴트 크기만큼 화면을 그려줌
		jf_sungjuk.setVisible(true);
	}
	//메인 메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);//java에서 지원하는 창 디자인
		Sungjuk sj = new Sungjuk();
		sj.initDisplay();
		
	}
	//@Override는 어노테이션이라고 읽음 - ActionListener가 가진 추상메소드를 
	//그대로 가져다가 재정의해서 사용하시오. 
	//추상메소드 : void methodA(); - 원래 메소드뒤에는 {}가 있고 기능이 구현이 되어있는데 추상메소드는 이름만 구현이 되어있고 {}가 없는 기능이 구현되어있지않음 메소드.
	//재정의하는 메소드는 기능을 다시 정의해서 쓰는건 되지만 틀(리턴타입을 바꾸거나, 매개변수를 추가하는 행위)을 바꾸는 것은 불가능
	@Override
	public void actionPerformed(ActionEvent e) {
		//insert here
		Object obj = e.getSource();
		if(obj == jbtn_account) {
			//총점과 석차가 들어갈 공간을 할당하기
			//인원수는 어떻게 가져오지? - 전역변수로 선언하고 사용하는게 좋겠어
			//왜냐면 다른 이벤트에서도 필요하기 때문이지.
			int imsi[][] = new int[inwon][2];
			for (int i = 0; i < inwon; i++) { //0~3을 입력했을 때만 석차가 나옴...
				int tot = Integer.parseInt((String)dtm_sj.getValueAt(i, 1))
						+ Integer.parseInt((String)dtm_sj.getValueAt(i, 2))
						+ Integer.parseInt((String)dtm_sj.getValueAt(i, 3));
				System.out.println("총점"+i+"행"+tot+"입니다.");
//				구한 총점을 DefaultTableModel객체에 담기
				dtm_sj.setValueAt(tot, i, 4);
				double avg = 0.0;
				avg = tot/3.0;
				dtm_sj.setValueAt(avg, i, 5);
				imsi[i][0] = tot;
				imsi[i][1] = 1; //조건을 수렴하지 않을 경우가 발생할 수 있다.
				//이 때 0등이 나오면 안되니까 초기화를 1로 변경하였다.
			}/////////////end of for
			//석차 매기기
			for (int i = 0; i < inwon; i++) {
				for (int j = 0; j < inwon; j++) {
					//imsi[0][0] < imsi[0][0]
					//imsi[0][0] < imsi[1][0]
					//imsi[0][0] < imsi[2][0]
					//3<3, 3<1, 3<2
					if(imsi[i][0] < imsi[j][0]) {
						imsi[i][1]++;  
					}
				}
			}//////////end of for
			for (int i = 0; i < inwon; i++) {
				dtm_sj.setValueAt(imsi[i][1], i, 6);
			}
		}
		else if(obj == jbtn_data) {
			String data[][] = {
					{"이순신","70","85","80"},
					{"김유신","60","75","70"},
					{"이성계","45","50","65"}
			};
			//초기화 할 수 있니?
			//2중 for문 할용할 수 있는거야?
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					dtm_sj.setValueAt(data[i][j], i, j); // dtm_sj에 데이터를 넣는 것은 setValueAt를 활용
				}
			}
		}
		else if(obj == jtf_inwon) {
			inwon = Integer.parseInt(jtf_inwon.getText());
			data		= new String[inwon][7]; 
			dtm_sj		= new DefaultTableModel(data, cols);
			jt_sj		= new JTable(dtm_sj);
			jsp_sj		= new JScrollPane(jt_sj);
			jf_sungjuk.add("Center",jsp_sj);
			jf_sungjuk.pack(); //데이터 양에 따라서 줄었다가 늘었다가 해야되니 크기를 정하지 못함. 즉 디폴트로 데이터 표를 조절하게 하기위함.
			jt_sj.getTableHeader().setResizingAllowed(false); //셀을 양쪽으로 이동하지 못함.
			jt_sj.getTableHeader().setReorderingAllowed(false); //셀을 옆으로 늘리지 못함
			//사용중인 컴터의 스크린 사이즈 정보 가져오기
			Dimension di = Toolkit.getDefaultToolkit().getScreenSize(); //기본 픽셀
			//현재 내가 그린 화면의 크기 가져오기(400*300에서 변화되었겠죠)
			Dimension di2 = jf_sungjuk.getSize(); //내가 설정한 픽셀
			jf_sungjuk.setLocation((int)(di.getWidth()/2-di2.getWidth()/2),
								   (int)(di.getHeight()/2-di2.getHeight()/2));  //화면을  정가운데 배치
		}
	}////////////////////end of actionPerformed

}
