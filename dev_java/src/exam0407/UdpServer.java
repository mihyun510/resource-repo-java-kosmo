package exam0407;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
	public UdpServer() {
		connect_process();
	}
	public void connect_process() {
		System.out.println("connect_process호출성공.");
		try {
			DatagramSocket socket = new DatagramSocket(7000); //서버소켓을 따로 가질 필요가없다. 같이 실어서 보낸다.
			DatagramPacket inDP, outDP = null;
			byte[] inMsg = new byte[10];
			byte[] outMsg = null;
			while(true) {
				inDP = new DatagramPacket(inMsg,inMsg.length); //data수신정보, data수신정보 길이
				socket.receive(inDP); //data을 읽어들인다.
				//수신한 패킷에서 사용자의 ip와 port을 얻을 수 있다.
				InetAddress iaddr = inDP.getAddress();
				int port = inDP.getPort();
				//서버의 현재 시간정보
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				String time =sdf.format(new Date()); //날짜정보는 util안에 있음. data는 오라클쪽의 날짜정보을 가져는 것
				outMsg = time.getBytes(); //시간정보를 byte배열로 변환
				//패킷을 생성 client전송 , 읽었으니 내보내자
				//UDP는 전송할때 주소와 port번호를 같이 보낸다.
				outDP = new DatagramPacket(outMsg, outMsg.length, iaddr, port); //전송패킷안에다가 전송내용과 ip주소와 port번호를 함꼐 보낸다.
				socket.send(outDP);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new UdpServer();
		
	}
}
