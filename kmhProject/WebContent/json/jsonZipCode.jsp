<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.google.gson.Gson, java.util.Vector" %>
<%@ page import= "method.zipcode.ZipCodeSearchAppCombo, com.vo.ZipCodeVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% //작업코드을 쓸려면 이것을 곡 써주어야됨 이안에다가 자바코드를 쓸수있음 [스크립틀릿]
	ZipCodeSearchAppCombo zcApp = new ZipCodeSearchAppCombo();
	Vector<ZipCodeVO> v = zcApp.refreshData(null, "가산동");
	Gson g = new Gson();
	String temp = g.toJson(v);
	out.println(temp); //System은 콘솔에서 쓰겠다는 뜻 out은 브라우저에서 사용한다는 뜻 System 지우기

%>
</body>
</html>