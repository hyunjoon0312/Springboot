<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="position:absolute;
  top:10%;
  left:45%;
  width:500px;
  height:500px;
  margin:-50px 0 0 -50px;">
	<h2>암호키생성</h2>
	<h3>암호키 : ${secretKey }</h3>
	
</div>
<div style="float:right">
	<input style="width:100px; height:30px; font-size:15px;" id ="back" type="button" value="뒤로가기" onclick="javascript:location.href='./requestList';"/>
</div>
</body>
</html>