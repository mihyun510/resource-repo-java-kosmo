package thread.talk3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.sun.prism.Image;

public class EmoticonView extends JDialog implements ActionListener{
	//버튼에 이모티콘을 입혀보자.
	JButton emot1 = new JButton();
	JButton emot2 = new JButton();
	JButton emot3 = new JButton();
	JButton emot4 = new JButton();
	JButton emot5 = new JButton();
	JButton emot6 = new JButton();
	String imgFile[] = {"lion11.png","lion22.png","lion33.png","lion44.png","lion55.png","lion66.png"};
	JButton emots[] = {emot1,emot2,emot3,emot4,emot5,emot6}; //버튼을 배열로 담자.
	ImageIcon imgs[] = new ImageIcon[imgFile.length]; //이미지 파일의 길이만큼 배열을 생성
	String path = "src//thread//emoticon//";
	String imgChoice = "default";
	
	TalkClient tc = null;
	
	public EmoticonView(TalkClient tc) {
		this.tc = tc;
		emot1.addActionListener(this);
		emot2.addActionListener(this);
		emot3.addActionListener(this);
		emot4.addActionListener(this);
		emot5.addActionListener(this);
		emot6.addActionListener(this);
	}
	
	public EmoticonView() {}

	public void initDisplay() {
		this.setLayout(new GridLayout(2, 3, 2, 2));

		for (int i = 0; i < emots.length; i++) {
			//이벤트가 중복된다. 즉, 이모티콘의 버튼을 누를때마다 initDiaplay가 호출되어 반복된다. 생성자에 따로 생성하자.
			//emots[i].addActionListener(this);
			imgs[i] = new ImageIcon(path+imgFile[i]); //이미지 파일을 생성할 경로설정
			emots[i].setIcon(imgs[i]); //각각의 버튼에 각각의 이미지를 붙혀주자.
			emots[i].setBorderPainted(false);
			emots[i].setFocusPainted(false);
			emots[i].setContentAreaFilled(false);
			this.add(emots[i]);
		}
		this.setTitle("이모티콘");
		this.pack(); // 페키지로 담아준다.
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		EmoticonView emot = new EmoticonView();
		emot.initDisplay();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == emot1) {
			imgChoice = "lion11.png";
			//테스트 할때 엔터칠때마다 이모티콘이 날아가는 것은 별로다 이모티콘을 선택했을때 이모티콘 메세지가 날아가자.
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
		else if(obj == emot2) {
			imgChoice = "lion22.png";
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
		else if(obj == emot3) {
			imgChoice = "lion33.png";
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
		else if(obj == emot4) {
			imgChoice = "lion44.png";
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
		else if(obj == emot5) {
			imgChoice = "lion55.png";
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
		else if(obj == emot6) {
			imgChoice = "lion66.png";
			tc.message_process(null, imgChoice);
			this.setVisible(false); //이미지 선택했을때마다 화면창을 닫아주자.
		}
//		JOptionPane.showMessageDialog(this, "imgChoice:"+imgChoice);
	}
}
