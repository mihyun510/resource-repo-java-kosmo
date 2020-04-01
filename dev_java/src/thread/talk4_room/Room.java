package thread.talk4_room;
/*****************************************************************************
 * 생성자 살펴보기 - default / 파라미터가 존재하는 생성자...등등
 * 제공되는 메소드 살펴보기
 * 제공되는 필드 살펴보기
 * 관련있는 추상클래스, 관련있는 인터페이스 같이 생각해보기
 *****************************************************************************/
import java.util.List;
import java.util.Vector;

public class Room {
	//단톡방에 있는 친구들만 관리하는 List이다. - 여러개의 방중에 같은 방에 있는 사람들만 관리하겠다.
	List<TalkServerThread> userList = new Vector<TalkServerThread>(); 
	List<String> 		   nameList = new Vector<String>();
	String 				   title 	= null; //단톡 이름
	String 				   state    = null; //대기, 단톡이름출력
	int 				   max 		= 0; //최대인원수
	int 				   current 	= 0; //현재 인원수
	
	public Room() {};
	public Room(String title, int current){//방목록을 만드는 생성자 - 방생성시에
		this.title = title;
		this.current = current;
	}
	public Room(String title, int current, String state){ //방에입장하면 클라이언트의 상태와 방의 인원수, 어떤방인지 방의 제목을 생성하는생성자. 방입장시에
		this.title = title;
		this.current = current;
		this.state = state;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	
	
	
	
	
}
