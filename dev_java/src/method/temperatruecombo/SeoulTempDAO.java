package method.temperatruecombo;

import com.util.DBConnectionMgr;

public class SeoulTempDAO {
	/*
	 * 서울 기상통계 정보로 부터 최근 10년 년도 가져오기
	 */
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public String[] getYearList() {
		String years[] = null;
//		String years[] = {"2019","2018","2017"}; // 단위 테스트 
		//years = new String[10];
		return years;
	}

}
