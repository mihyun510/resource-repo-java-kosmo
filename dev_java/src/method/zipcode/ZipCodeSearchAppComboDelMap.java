package method.zipcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;
public class ZipCodeSearchAppComboDelMap implements ActionListener, FocusListener, ItemListener
{	
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	String zdos[] = null;
	JComboBox jcb_zdo = null;
	String zDO = null;
	Connection 			con 	= null;
	PreparedStatement 	pstmt 	= null;
	ResultSet		rs			= null;
	
	JTextField		jtf_dong	= new JTextField("동이름을 입력하세요");
	JButton			jbtn_search	= new JButton("조회");
	JButton			jbtn_del	= new JButton("삭제");
	String			cols[]		= {"주소","우편번호"};
//	String			cols[]		= {"우편번호","주소"};
	String			data[][]	= new String[0][2]; //
	DefaultTableModel dtm_zip	= new DefaultTableModel(data, cols);
	JTable			jt_zip		= new JTable(dtm_zip);
	JScrollPane		jsp_zip		= new JScrollPane(jt_zip);
	JTableHeader	jth_zip		= new JTableHeader();
	JFrame			jf_zip		= new JFrame(); 
	JPanel			jp_north 	= new JPanel(); 
	
	public ZipCodeSearchAppComboDelMap() { 
		zdos = getZDOList(); 
		jcb_zdo = new JComboBox(zdos); 
		System.out.println("나는 파라미터가 없는 디폴트 생성자라고 해.");
		System.out.println("나는 인스턴스화 하는 순간 자동으로 호출되는 거야.");
	}
	public ZipCodeSearchAppComboDelMap(String str, int i) {
	}
	
	public List<Map<String,Object>> refreshData(String zDO, String myDong) {
		int i = 1;
		con = dbMgr.getConnection();
		System.out.println("refreshData호출 성공"+myDong);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT address, zipcode");
	    sql.append("  FROM zipcode_t");
	    sql.append(" WhERE 1=1");
	    if(zDO!=null && zDO.length()>0) { 
	    	sql.append(" AND zdo=?"); 
	    }
	    
	    if(myDong!=null && myDong.length()>0) {
	    	sql.append(" AND dong LIKE '%'||?||'%'"); 
	    }
	    List<Map<String, Object>> addrList = new ArrayList<>();
	    try {
	    	pstmt = con.prepareStatement(sql.toString()); 
	    	if(zDO!=null && zDO.length()>0) { 
	    		pstmt.setString(i++, zDO);
	    	}
	    	if(myDong!=null && myDong.length()>0) {
	    		pstmt.setString(i++, myDong);
	    	}
	    	rs = pstmt.executeQuery();
	    	Map<String, Object> rMap = null;
	    	while(rs.next()) {
	    		rMap = new HashMap<>();
	    		rMap.put("address",rs.getString("address"));
	    		rMap.put("zipcode",rs.getInt("zipcode"));
	    		addrList.add(rMap); //addList에 rMap을 넣어주자
	    	}
			System.out.println("addrList.size():"+addrList.size());
			if(addrList.size()>0) {
				while(dtm_zip.getRowCount() > 0) { 
					dtm_zip.removeRow(0);
				}
				for (int x = 0; x < addrList.size(); x++) {
					Map<String , Object> map = addrList.get(x);
					Object[] keys = map.keySet().toArray(); //map의 키값을 모두 배열로 가져오자.
					Vector<Object> oneRow = new Vector();
					for (int j = 0; j < keys.length; j++) {
						oneRow.add(j, map.get(keys[j]));
						System.out.println(map.get(keys[j])); //키값을 넣으면 키값에 대한 값이 나오는 건가..?
					}
					dtm_zip.addRow(oneRow);
//					oneRow.add(0, map.get("address"));
//					oneRow.add(1, map.get("zipcode"));
				}
			}
		} catch (SQLException se) {
			System.out.println("[[query]]"+sql.toString());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("[[Exception]]"+e);
		}
	    return addrList;
	}
	
	//화면 그리기 
	public void initDisplay() {
		jbtn_search.requestFocus(); 
		jtf_dong.addFocusListener(this);
		jcb_zdo.addItemListener(this);
		System.out.println("initDisplay 호출 성공");
		
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(new Color(22,22,100)); 
		jth_zip.setForeground(Color.white); 
		jth_zip.setFont(new Font("맑은 고딕",Font.BOLD,20)); 
		jt_zip.setGridColor(Color.blue); 
		jt_zip.setRowHeight(20);
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(350); 
//		jt_zip.getColumnModel().getColumn(1).setPreferredWidth(350); 
		jt_zip.setSelectionBackground(new Color(186,186,241));
		jt_zip.setSelectionForeground(new Color(100,100,100));
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		jp_north.add(jcb_zdo);
		jp_north.add(jtf_dong); 
		jp_north.add(jbtn_search);
		jp_north.add(jbtn_del);
		jp_north.setBackground(Color.red); 
		jbtn_search.addActionListener(this);
		jbtn_del.addActionListener(this);
		jtf_dong.addActionListener(this);
		jf_zip.setTitle("우편번호 검색"); 
		jf_zip.add("North",jp_north); 
		jf_zip.add("Center",jsp_zip);
		jf_zip.setSize(600, 500); 
		jf_zip.setVisible(true); 
	}
	public String[] getZDOList() {
		String zdos[] = null;
		StringBuilder sb = new StringBuilder("");
		sb.append("select '전체' as zdo from dual"); 
		sb.append(" union all					");				
		sb.append("select zdo 					");
		sb.append("  from ( 					");
		sb.append("select distinct(zdo) as zdo  ");
		sb.append("  from zipcode_t 			");
		sb.append(" order by zdo asc 			");
		sb.append(") 							");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery(); 
			Vector<String> v = new Vector<String>();
			while(rs.next()) { 
				String zdo = rs.getString("zDO"); 
				v.add(zdo);
			}
			zdos = new String[v.size()];				
			v.copyInto(zdos);							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zdos;
	}
	
	public static void main(String[] args) throws Exception{
		JFrame.setDefaultLookAndFeelDecorated(true);
		ZipCodeSearchAppComboDelMap zipApp = new ZipCodeSearchAppComboDelMap();
		zipApp.initDisplay();
	}////////end of main
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if((obj == jbtn_search)||(obj == jtf_dong)) {
			String myDong = jtf_dong.getText();
			refreshData(zDO,myDong); 
		}
		else if(obj == jbtn_del) {
			int[] index = jt_zip.getSelectedRows();
			for(int row:index) {
				JOptionPane.showMessageDialog(jf_zip, row);
			}
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == jtf_dong) {
			jtf_dong.setText("");
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object obj = ie.getSource();
		if(obj == jcb_zdo) {
			if(ie.getStateChange() == ItemEvent.SELECTED) { 
				System.out.println(jcb_zdo.getSelectedIndex()); 
				zDO = zdos[jcb_zdo.getSelectedIndex()];
				System.out.println(zdos[jcb_zdo.getSelectedIndex()]);
			}
		}
		
	}
}/////////end of ZipCodeSearchApp
