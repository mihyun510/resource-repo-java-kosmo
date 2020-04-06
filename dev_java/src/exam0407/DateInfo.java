package exam0407;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInfo {
	public long getDifferenceDates(String sDate, String eDate, String pattern, char delim) {
		long diff = 0L;
		try {
			SimpleDateFormat sd = new SimpleDateFormat(pattern);
			Date sdate = sd.parse(sDate);
			Date edate = sd.parse(eDate);
			long sTime = sdate.getTime();
			long eTime = edate.getTime();
			diff = Math.abs(sTime-eTime);
			diff = (diff/(60*60*1000));
			//System.out.println("diff:"+diff/(60*60*1000)); //60[분]*60[초]*1000[밀리sec] : 하루를 나타내는 밀리sec단위의 초.
		} catch (ParseException e) {
			System.out.println("[ParseException]:"+e.toString());
		}
		return diff;
	}
	public static void main(String[] args) {
		DateInfo di = new DateInfo();
		long diff = di.getDifferenceDates("2020-04-07", "2020-04-05", "yyyy-MM-dd", 'H');
		System.out.println("diff:"+diff);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sd.parse("2020-04-06"); //날짜타입으로 날짜를 가져온다.
			System.out.println("d:"+d); 
										//"yyyy-MM-dd hh:mm:ss"과 "2020-04-06"의 타입이 맞지않음. 맞춰줘야됨.
										//[ParseException]:java.text.ParseException: Unparseable date: "2020-04-06"
										//java.text.ParseException: Unparseable date: "2020-04-06" -> 날짜타입이 맞지않음.
			String s = sd.format(Calendar.getInstance().getTime()); // 현재날짜와 현재시간을 가져옴., 스트링타입으로 날짜를 가져옴.
			System.out.println("s:"+s);
		} catch (ParseException e) {
			System.out.println("[ParseException]:"+e.toString());
			e.printStackTrace();
		}
		
		
	}
}
