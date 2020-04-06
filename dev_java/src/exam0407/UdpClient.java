package exam0407;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	
	public UdpClient() {
		connect_process();
	}
	public void connect_process() {
		System.out.println("Client connect_process호출 성공");
		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress siAddr = InetAddress.getByName("127.0.0.1"); //내서버에 접속
			//데이터가 저장될 공간 확보
			byte[] msg = new byte[100];
			DatagramPacket outDP = new DatagramPacket(msg,1,siAddr,7000);
			DatagramPacket inDP = new DatagramPacket(msg, msg.length);
			//전송하기 - 한번실행하고 끝난다. 지속적으로 사용할 수 없다. 그래서 thread가 필요가 없다. <==> 지속적으로 반복하는 프로세스를 구현하고 싶다면 Thread를 사용하자.
			ds.send(outDP);
			//수신하기
			ds.receive(inDP);
			System.out.println("현재서버시간:"+new String(inDP.getData()));
			ds.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new UdpClient();
	}
}
