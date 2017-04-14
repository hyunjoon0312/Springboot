<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>한국보건의료 연구원 데이터 요청 목록</h1>

	<table id="tb" border="1" cellspacing="0"
		style="margin: 0 auto; text-align: center;">


		<tr>
			<th>IRB 번호</th>
			<th>연구자 이름</th>
			<th>요청기관</th>
			<th>연계기관</th>
			<th>연결번호</th>
		</tr>
		
		
		<c:forEach items="${necaList}" var="necaList">
			
		<tr>
			<td>${necaList.IRB}</td>
			<td>${necaList.rName}</td>
			<td>${necaList.requestORG }</td>
			<td>${necaList.linkedORG }</td>
		
			<td>
			<form action="sendLinknum" method="post">
			
			<input type="hidden" name="IRB" value="${necaList.IRB}" />
			<input type="hidden" name="requestORG" value="${necaList.requestORG }"/>
			<input type="hidden" name="linkedORG" value="${necaList.linkedORG }"/>
			<c:if test="${necaList.sendLinknum == '0' }">
			<input type="submit" value="전송" />
			</c:if>
			<c:if test="${necaList.sendLinknum == '1' }">
			<input type="submit" value="전송완료" disabled="disabled" />
			</c:if>
			</form>
			</td>
		</tr>
		</c:forEach>
		</table>
		
</body>
</html>