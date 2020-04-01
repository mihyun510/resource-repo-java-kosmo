package thread.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileClientThread extends Thread{
	
	FileClient fc = null;
	
	public FileClientThread() {
	
	}

	public FileClientThread(FileClient fc) {
		this.fc = fc;
	}
	
	@Override
	public void run() {
		try {
			String fileName ="src\\design\\book\\emoticon\\lion1.png"; //이클립스 안에있어서 project까지는 알고있다.
			OutputStream out = new FileOutputStream(fileName);
			//서버는 이미지의 정보를 읽고 써야된다.
			InputStream is = fc.socket.getInputStream();
			int readcount = 0;
			byte buffer[] = new byte[512];
			while((readcount = is.read(buffer))!=-1) {
				out.write(buffer, 0, readcount);
			}
			out.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
