package friday0207;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class BaseBallGameEvent implements ActionListener{
	BaseBallGameView bbView = null; //디폴트 생성자의 기능이 있었기 때문이다. 
//	BaseBallGameLogic bbLogic = new BaseBallGameLogic(); // 위는 null이지만 여기는 인스턴스화를 함 왤까? 서로 크로스(충돌)가 되지 않았기 때문. 생성자가 어디에도 자신의 클래스 외에 선언된곳이 없다(자시의 클래스에서는 default 생성자가 생성되므로 자동으로 생성된다.) 그러므로 널로 주면 주소를 받거나 값을 받을 수 없기 때문에 NullPointerException이 발생한다.
	//게임을 진행하는 동안에는 계속 그 숫자를 기억하고 있다가 1씩 증가되야 하니까...
	DBConnectionMgr dbMgr = null; //선언과 생성을 따로 하기위해서 선언을 먼저 해줌
	Connection con = null;        //위와 같은 의미
	//전역변수로 해야 함.
	int cnt = 0; //회차를 출력할 변수 선언
	int[] dap = new int[3];
	String str = "";
	String instr = "";
	public BaseBallGameEvent(BaseBallGameView baseBallGameView) { //NullPointerException 생성자View에서 BaseBallGameEvent를 선언함과 동시에 this로 view 주소값을 받아와서 Event의 생성자의 baseBallGameView 멤버변수로 초기화 , 충돌x
		this.bbView = baseBallGameView; //초기화.   오늘의 학습목표!!!!
	}

	public void exitGame() {
		System.exit(0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//BaseBallGameView bbView = new BaseBallGameView();
		if(obj == bbView.jbtn_clear) {
			System.out.println("지우기 버튼 호출 성공");
			bbView.jta_display.setText("");
		}
		else if(obj == bbView.jbtn_dap) {
			System.out.println("정답 버튼 호출 성공");
		
			bbView.jta_display.append("정답: "+bbView.bbLogic.com[0]+" "+bbView.bbLogic.com[1]+" "+bbView.bbLogic.com[2]+"\n"); //문자열 더하기 숫자는 문자열로 (붙혀쓰기로 반환)
			bbView.jbtn_dap.setEnabled(false); //버튼을 여러번 누르지 못하게 설정 - 설정하니깐 아예 번튼이 안눌림;;; 뭐징
		}
		
		else if(obj == bbView.jmi_oracle) {
			System.out.println("오라클 테스트 호출 성공"); //호출된다면 이벤트처리가 성공한 것임
			dbMgr = DBConnectionMgr.getInstance(); //dbMgr클래스 안에 생성자를 만들어 싱글톤 패턴으로 만듬 함수를 사용하여 인스턴스화가 가능
			con = dbMgr.getConnection(); //인스턴스화를 한 후 디비연동 함수를 불러와서 연동결과를 변수로 반환받음
			if(con != null) {				//반환받은 변수의 결과에 따라 null이 아니면 연동결과(연동이성공함)가 들어왔다는 의미이므로 성공확인 메세지를 출력
				System.out.println("DB연동 성공");
			} else { 						//반환받은 변수의 결과에 null값이 있다면 연동된 결과가 없다는 의미이므로 연동이 실패했다는 실패확인 메세지를 출력
				System.out.println("DB연동 실패");
			}
		}
		else if(obj == bbView.jbtn_exit) {
			System.out.println("나가기 전에 호출 성공");
			exitGame();
		}
		else if(obj == bbView.jbtn_next) {
			System.out.println("다음겜 버튼 호출 성공");
			cnt = 0;
			//세자리 숫자를 다시 채번해요.
			bbView.jbtn_dap.setEnabled(true);
			//BaseBallGameLogic안에 com배열이 선언되어 있음.
			//인스턴스화를 한 상태이므로 접근이 가능함.
			
			bbView.bbLogic.ranCom();  //View에 Logic을 선언함 그러니 View로 접근 후 Logic으로 접근하여 함수로 사용
			//3자리 숫자가 새로운 숫자가 다음게임을 누를때마다 확인을 해보고 싶다. 어떻게 해야 될까?
			//BaseBallGameLogic안에 com배열이 선언되어 있음.
			//인스턴스화를 한 상태이므로 접근이 가능함
			for (int coms:bbView.bbLogic.com) { //채번된 3개의 숫자가 com배열안에 들어감. BaseBallGameLogic타입임. 그것을 coms에 복사하고 이것을 출력
				System.out.println(coms+"");
			}
			System.out.println();
		}
		//세자리 숫자를 입력했어?
		else if(obj == bbView.jtf_input) {
			str = bbView.bbLogic.account(bbView.jtf_input.getText());
			instr = bbView.jtf_input.getText();
			bbView.jta_display.append((++cnt)+"회: "+instr+"==>"+str+"\n");
//			bbView.jtf_input.setText(""); //엔터치면 다시 초기화 하자 숫자 없어지자. [에러발생]값을 받고 디비로 넘기기 전에 초기화 시켜버려서 NumberFormatException 에러 발생
			//insert here - 오라클 서버에 insert문 요청 처리하기
			//수집해야 하는 정보를 출력해보기
			System.out.println("mem_id: "+bbView.result[1]);
			System.out.println("game_seq: "+cnt);
			System.out.println("input: "+instr);
			System.out.println("hint: "+ str);
			System.out.println("dap: "+bbView.bbLogic.com[0]+" "+bbView.bbLogic.com[1]+" "+bbView.bbLogic.com[2]);
			bbView.jtf_input.setText("");
			
			Vector<BaseballVO> v = new Vector<BaseballVO>();
			//VO값을 초기화 할때 생성자를 활용해 보세요.
//			bVO.setMem_id(bbView.result[1]);
//			bVO.setGame_seq(cnt);
//			bVO.setInput(instr);
//			bVO.setHint(str);
//			bVO.setDap(bbView.bbLogic.com);
			
			BaseballVO bbVO = new BaseballVO(bbView.result[1],cnt,instr,str,
											 bbView.bbLogic.com[0]+" "+bbView.bbLogic.com[1]+" "+bbView.bbLogic.com[2]);
			
			System.out.println("mem_id: "+bbVO.getMem_id());
			System.out.println("game_seq: "+bbVO.getGame_seq());
			System.out.println("input: "+bbVO.getInput());
			System.out.println("hint: "+ bbVO.getHint());
			System.out.println("dap: "+bbVO.getDap());
			bbView.jtf_input.setText("");
			int result = bbView.bbLogic.history(bbVO);
			if(result == 1) {
												//.jf_bbgame 제거
				JOptionPane.showMessageDialog(bbView, "등록성공");
			}else if(result == 0) {				//.jf_bbgame
				JOptionPane.showMessageDialog(bbView, "등록실패");
			}
		}
	}
}

//10회까지 바등면 다음게임이나 다시실행을 해보자
//꼭 갚을 입력받도록해보자
