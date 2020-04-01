package mailing;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	  public static void main(String[] args) {
          //SMTP 서버 정보를 설정
		Properties props = new Properties();
		//발송 STMP 서버
		props.put("mail.smtp.host", "smtp.naver.com");
		//SMTP서버의 포트
		props.put("mail.smtp.port", 465);		
		props.put("mail.smtp.auth", "true");
		//SSL 활성화
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		
		//보내는 쪽 정보
		final String sendId = "dkfmaqkd33@naver.com"; //네이버 계정 아이디
		final String sendPass = "mihyun510+";//네이버 계정 비번
		String sendEmailAddress = "dkfmaqkd33@naver.com";
		
		//SMTP 서버 정보와 사용자 계정를 기반으로 
		//Session 클래스의 인스턴스를 생성
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(sendId, sendPass);
			}
		});	
		//받는 쪽 정보
		String receiveEmailAddress = "k696366k@gmail.com";
		String subject = "안녕하세요. 이순신 입니다.";
		String content = "학습용 이메일 보내기 연습입니다.";
		//메일 발신자와 수신자, 제목 그리고 내용 작성을 위한 MimeMessage객체 생성
		MimeMessage message = new MimeMessage(session);
		
		String url = null;
		
		try {
			//발신자 설정
			message.setFrom(new InternetAddress(sendEmailAddress));
			//수신자 설정
			message.setRecipient(Message.RecipientType.TO, 
								new InternetAddress(receiveEmailAddress));
			//제목 설정
			message.setSubject(subject);
			//메일 내용 설정
			//일반 테스트 형태
			message.setText(content);
			//이메일 보내기
			Transport.send(message);
			System.out.println("메일이 전송 되었습니다.");
			url =  URLEncoder.encode("한글인코딩","UTF-8");
		} 
		catch (MessagingException | UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		

}////////////

}
