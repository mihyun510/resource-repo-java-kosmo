package thread.bank;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
//Customer는 한개임 Server는 여러개 - 접속하는 소켓이..?
public class CustomerBank extends JFrame {

	String mem_id = null; 
	JPanel jp_centor = new JPanel();
	String[] cols = {"대화명"};
	String[][] data = new String[0][1];
	DefaultTableModel dtm_nickName = new DefaultTableModel(data,cols);
	JTable jtb_nickName = new JTable(dtm_nickName);
	JScrollPane jsp_nickName = new JScrollPane(jtb_nickName,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JPanel jp_south = new JPanel();
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER); //따로 생성을 안하고 선언과 동시에 생성한 이유는 여기서 바로 사용. -timeclient에서는 중간에 timeserver가 껴있었음. 즉, 거쳤다가 와야되었다.
	JTextArea jta_clog = new JTextArea(8,30);
	JScrollPane jsp_clog = new JScrollPane(jta_clog,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	Socket socket = null;
	ObjectInputStream ois = null; 
	ObjectOutputStream oos = null;
	
	public CustomerBank() {
		//아이디를 입력받자_대화명을 입력받기
		mem_id = JOptionPane.showInputDialog("아이디를 입력하세요.");
		if(mem_id == null) {
			return; //생성자 탈출 - 아래 메소드 2개는 호출이 안되요.
		}
		initDisplay();
		connect_process();
	}
	public void initDisplay() {
		jp_centor.setLayout(new GridLayout(1,2)); //배치를 위한 설정 - 중앙, 남쪽
		jp_centor.add(jsp_nickName);
		jp_south.setLayout(new BorderLayout()); //배치를 위한 설정 - 중앙, 남쪽
		jp_south.add("Center", jsp_clog); //센터에 넣을것을 아직 결정하지 못함. null로 넣어놓자.
		jp_south.add("South",jlb_time);
		this.setTitle(mem_id+"님 계좌입니다.");
		this.add("Center",jp_centor);
		this.add("South", jp_south);
		this.setSize(500,400);
		this.setVisible(true);
	}
	
	//서버에 접속하기 구현
	public void connect_process() {
		try {
			socket = new Socket("192.168.0.15", 3000);
			oos = new ObjectOutputStream(socket.getOutputStream()); 
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(100+"#"+mem_id);
			CustomerBankThread cbt = new CustomerBankThread(this);
			cbt.start();
			
		} catch (Exception ie) {
			System.out.println("은행 서버에 접속할 수 없습니다."+ie.toString());
		}
	}
	
	//타임서버에 접속하기 구현
	public void time_process() {
		
	}
	public static void main(String[] args) {
		CustomerBank cb = new CustomerBank();
	}

}
