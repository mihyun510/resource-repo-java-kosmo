package thread.talk4_room;

import java.util.ArrayList;
import java.util.List;

public class RoomTest {
	TalkServer	ts = null;
	//축구부 명단 출력하기
	void printFList(List<String> nameList) {
		if(nameList != null) {
			for(int i = 0; i<nameList.size(); i++) {
				String name = nameList.get(i);
				System.out.println(i+", "+name);
			}
		} else {
			System.out.println("명단이 없습니다.");
		}
	}
	public void roomCasting(String msg, String roomTitle) {
		//insert here
		//방의 갯수를 어떻게 알 수 있을까?
		for(int i=0; i<ts.roomList.size(); i++) {
			//Room에 대한 주소번지를 가져옴.
			Room room = ts.roomList.get(i);
			if(roomTitle.equals(room.title)){
				for(int j = 0; j<room.userList.size(); j++) {
					TalkServerThread tst = room.userList.get(j);
					try {
						tst.send(msg);
					} catch(Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
		//그 방 중에 방이름은 어떻게 비교하지?
		//그 방에 있는 사람 수는 어떻게 알아내는 거야?
	}
	public static void main(String[] args) {
		//2개의 단톡방 만들기 : 농구부 , 축구부
		Room broom = new Room(); //농구부
		broom.title = "농구부";
		broom.nameList.add("이순신");
		broom.nameList.add("김유신");
		broom.nameList.add("이성계");
		broom.nameList.add("강감찬");
		broom.nameList.add("김춘수");
		
		Room froom = new Room(); //축구부
		froom.title = "축구부";
		froom.nameList.add("유재석");
		froom.nameList.add("강호동");
		froom.nameList.add("김종국");
		
		RoomTest rt = new RoomTest();
		rt.printFList(froom.nameList);
	}

}
