package view.q3_junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
	//[문자열이 숫자 형식이면 true,아니면 false반환]
	public static boolean isNumber(String value) {		
		for(int i=0;i< value.length();i++) {
			if(!(value.codePointAt(i)>='0' && value.codePointAt(i)<='9'))
				return false;
		}
		return true;
	}///////////////isNumber
	//두 날짜 차이를 반환하는 메소드]
	//반환타입:long
	//매개변수:String타입의 두 날짜,날짜패턴,구분자(단위)
	public static long getDifferenceDates(
			String strFDate,
			String strSDate,
			String pattern,
			char delim
			) throws ParseException {
		//1]매개변수에 전달된 pattern으로 SimpleDateFormat객체생성
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		//2]String -> Date:parse()
		Date fDate=dateFormat.parse(strFDate);
		Date sDate=dateFormat.parse(strSDate);
		//3]시간차 구하기:getTime()
		long fTime=fDate.getTime();
		long sTime=sDate.getTime();
		long diff = Math.abs(fTime-sTime);
		//4]매개변수 delim에 따른 날짜 차이 반환
		switch(Character.toUpperCase(delim)) {
			case 'D':return diff/(24*60*60*1000);
			case 'H':return diff/(60*60*1000);	
			case 'M':return diff/(60*1000);
			default:return diff/(1000);
		}
		
	}//////////getDifferenceDates
	//이름에서 초성을 구해 초성을 반환하는 메소드]
	public static char getFirstCharacter(String name) {		
		char[] names=name.trim().toCharArray();		
		char[] startChar= {'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar  = {'낗','닣','띻','맇','밓','삫','앃','잏','찧','칳','킿','팋','핗','힣'};
		char[] returnChar= {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i =0;i < startChar.length;i++)
			if(names[0]>=startChar[i] && names[0] <=endChar[i])
				return returnChar[i];		
		//초성이 한글이 아닌 경우
		return '0';
	}////////////////
	
}//////////class
