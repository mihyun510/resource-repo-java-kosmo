<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function search(){
		alert("검색해주세요."); //알림창이 뜸..이벤트 처리.,.
	}
</script>
</head>
<body>
Tomcat Server Start<br>
여기는 김미현  <b>웹서버</b> 페이지 입니다.<br>
WAS(Web Application Server)를 설치 하였습니다.<br>
방문해주셔서 감사합니다.
<%-- <%
	 String img = "http://192.168.0.17:8000/images/고양이.png"; //남의 사진을 가져오는 것.. 
%> --%>
<%-- <img src="<% out.print(img);%>images/unnamed.png" width="500px" height="400px"/> <!-- 다른 사람의 서버에 있는 사진의 것을 출력 --> --%>
<img src="images/unnamed.png" width="500px" height="400px"/>  <!-- 내가 가지고 있는 이미지 출력 -->
<br>
<input type="text" size="20" values="동이름을 입력하세요." onClick="search()"> 
<input type="button" values ="검색"> <!--  -->
<img src="\images\unnamed.png"/>
</body>
</html>