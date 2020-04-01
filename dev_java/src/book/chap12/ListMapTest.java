package book.chap12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListMapTest {
	public List<Map<String,Object>> getDeptList(){
		List<Map<String,Object>> deptList = new ArrayList<Map<String,Object>>();
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("deptno", 10); 
		pMap.put("dname", "ACCOUNTING");
		pMap.put("loc", "NEW YORK");
		deptList.add(pMap);
		
		pMap = new HashMap<>();
		pMap.put("deptno", 20); 
		pMap.put("dname", "RESEARCH");
		pMap.put("loc", "DALLAS");
		deptList.add(pMap);
		
		pMap = new HashMap<>();
		pMap.put("deptno", 30); 
		pMap.put("dname", "SALES");
		pMap.put("loc", "CHICAGO");
		deptList.add(pMap);
		return deptList;
	}
	public static void main(String[] args) {
		ListMapTest lmt = new ListMapTest();
		List<Map<String, Object>> deptList = lmt.getDeptList();
		for (int i = 0; i < deptList.size(); i++) { //리스트의 방이 3개
			Map<String, Object> pMap = deptList.get(i);
			Object[] keys = pMap.keySet().toArray(); //key값을 꺼내자 Map타입의 pMap의 주소로 접근해서 '.' ketSet으로 키값을 꺼내오자 그리고 그 키값을 배열로 받아오자. 
			for (int j = 0; j < keys.length; j++) {
				System.out.print(pMap.get(keys[j])+" ");
			}
			System.out.println();//개행처리 - 줄바꿈처리
		}

		
		
		
		
//		Object[] keys = lmt.getDeptList().get(0).keySet().toArray();
//		int deptno = 0;
//		String dname = null;
//		String loc = null;
//		for (int i = 0; i < keys.length; i++) {
//			if("deptno".equals(keys)) {
//				deptno = Integer.parseInt(keys.toString());
//			}
//			if("dname".equals(keys)) {
//				dname = keys.toString();
//			}
//			if("loc".equals(keys)) {
//				loc = keys.toString();
//			}
//		}
//		System.out.println(deptno+", "+dname+", "+loc);
	}/////////////end main
}
