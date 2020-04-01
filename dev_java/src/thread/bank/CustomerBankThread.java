package thread.bank;

import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class CustomerBankThread extends Thread {
	CustomerBank cb = null;
	Socket client = null;
	public CustomerBankThread(CustomerBank cb) {
		this.cb = cb;
	}
	@Override
	public void run() {
		String msg = null;
		boolean isStop = false;
		while(!isStop) {
			try {
				//100#닉네임1
				msg = (String)cb.ois.readObject();
				cb.jta_clog.append(msg+"\n");
				StringTokenizer st =null;
				int protocol = 0;
				if(msg != null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());
				}
				switch (protocol) {
				case 100:{
					String nickName = st.nextToken();
					
					cb.jta_clog.append(nickName+"님이 입장하였습니다.\n");
					Vector<String> v = new Vector<String>(); //정보를 담을 거야
					v.add(nickName); //정보들을 벡터에 추가
					cb.dtm_nickName.addRow(v); //추가된 닉네임들을 행에 추가됨.나 다음에 들어온 사람들이 행에 추가될거야.
											   //근데 지금 들어온 나는 전에 들어온 사람들이 누가있는지는 모름 
				}
				break;

				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}////////end try...catch
		}////////////end while
	}////////////////end run()
	
	
}
