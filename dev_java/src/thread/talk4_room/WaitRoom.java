package thread.talk4_room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//이것은 대기방 or 단톡방에 들어가야되는 판넬 클래스
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class WaitRoom extends JPanel implements MouseListener, ActionListener{
	//단톡명 담기
	String roomTitle = null;
	TalkClientVer2 tc 	= null;
	
	JPanel jp_first 	= new JPanel(); //대기모드 처리
	String cols[] 		= {"대화명", "위치"};
	String data[][] 	= new String[0][2];
	DefaultTableModel dtm_wait	= new DefaultTableModel(data, cols);
	JTable 			  jtb_wait 	= new JTable(dtm_wait);
	JScrollPane 	  jsp_wait 	= new JScrollPane(jtb_wait);
	JTableHeader	  jth_wait  = jtb_wait.getTableHeader(); //헤더부분의 색을 바꾸자.
	
	JPanel jp_second 	   = new JPanel(); //단톡정보 처리
	String cols2[] 		= {"단톡명", "현재인원"};
	String data2[][] 	= new String[0][2];
	DefaultTableModel dtm_room	= new DefaultTableModel(data2, cols2);
	JTable 			  jtb_room 	= new JTable(dtm_room);
	JScrollPane 	  jsp_room 	= new JScrollPane(jtb_room);
	JTableHeader	  jth_room  = jtb_room.getTableHeader(); 
	
	JPanel  jp_second_south = new JPanel();
	JButton jbtn_create 	= new JButton("단톡만들기");
	JButton jbtn_in 		= new JButton("입장하기");
	JButton jbtn_out 		= new JButton("나가기");
	JButton jbtn_exit 		= new JButton("종료하기");
	
	JLabel jlb_banner = new JLabel();
	
	public WaitRoom(TalkClientVer2 tc) {
		this.tc = tc;
		initDisplay();
	}
	
	public void initDisplay() {
		jbtn_create.addActionListener(this);
		jbtn_in.addActionListener(this);
		jtb_room.addMouseListener(this);
		this.setLayout(new GridLayout(1,2));
		jth_wait.setBackground(Color.red); //헤더 배경색 변경
		jth_wait.setForeground(Color.white); //헤더 글자색 변경
		jtb_wait.setGridColor(Color.black); //테이블 테두리 변경
		jtb_wait.setSelectionBackground(Color.RED); //행을 선택했을 때 색상 변경
		//테이블 헤더 위치 변경 꺼두기
		jth_wait.setReorderingAllowed(false);
		jp_first.setBorder(BorderFactory.createEtchedBorder()); //테두리 설정
		jp_first.setBackground(Color.pink);
		jp_first.setLayout(new BorderLayout());
		jp_first.add(jsp_wait);
		
		jth_room.setBackground(Color.yellow);
		jth_room.setForeground(Color.black);
		jtb_room.setGridColor(Color.black);
		jtb_room.setSelectionBackground(Color.yellow);
		jth_room.setReorderingAllowed(false);
		
		jp_second.setBackground(Color.cyan);
		jp_second.setLayout(new BorderLayout());
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_in);
		jp_second_south.add(jbtn_out);
		jp_second_south.add(jbtn_exit);
		jp_second.add("Center",jsp_room);
		jp_second.add("South", jp_second_south);
		
		jlb_banner.setBorder(BorderFactory.createEtchedBorder());
		jlb_banner.setIcon(new ImageIcon("src\\thread\\img\\banner5.gif"));
		jp_second.add("North", jlb_banner);
		
		this.add(jp_first);
		this.add(jp_second);
		this.setBackground(Color.green);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand(); //버튼의 라벨을 가지고 이벤트 처리를 진행
		if("입장하기".equals(commend)){
			int[] index = jtb_room.getSelectedRows();
			if(index.length == 0) {
				JOptionPane.showMessageDialog(this, "단톡방을 선택하세요.");
				return;
			}
			else {
				try {
					for(int i=0;i<jtb_room.getRowCount(); i++) {
						if(jtb_room.isRowSelected(i)) {
							String roomTitle = (String)dtm_room.getValueAt(i, 0);
							tc.oos.writeObject(Protocol.ROOM_IN+Protocol.SEPERATOR+roomTitle+Protocol.SEPERATOR+tc.nickName);
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		else if("단톡만들기".equals(commend)){
			roomTitle = JOptionPane.showInputDialog("단톡이름을 입력하세요.");
			if(roomTitle != null && roomTitle.trim().length()>0) { //trim():공백을 제거 한다. - 만약 입력을 안하고 enter치는 경우를 방지하자
				try {
					tc.oos.writeObject(Protocol.ROOM_CREATE+Protocol.SEPERATOR+roomTitle+Protocol.SEPERATOR+0); //방생성#방이름#인원소[0] 초기방은 0 
				} catch (Exception e2) {
					
				}
			}
		}
		else if("입장하기".equals(commend)) {
			tc.jtp.setSelectedIndex(1);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == jtb_room) {
			JOptionPane.showMessageDialog(tc, "mousePressrd");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
