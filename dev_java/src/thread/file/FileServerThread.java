package thread.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

//class조립하기
public class FileServerThread extends Thread{

	FileServer fs = null;
	
	public FileServerThread() {
	
	}
	
	public FileServerThread(FileServer fs) {
		this.fs  = fs;
	}
	
	@Override
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String fileName ="src\\design\\book\\emoticon\\lion11.png"; //이클립스 안에있어서 project까지는 알고있다.
				InputStream is = new FileInputStream(fileName);
				//서버는 이미지의 정보를 읽고 써야된다.
				OutputStream out = fs.socket.getOutputStream();
				int readcount = 0;
				byte buffer[] = new byte[512];
				while((readcount = is.read(buffer))!=-1) {
					out.write(buffer, 0, readcount);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
