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


	<h1>건강보험공단 연계 요청 받은 데이터 목록</h1>

	<table id="tb" border="1" cellspacing="0"
		style="margin: 0 auto; text-align: center;">


		<tr>
			<th>IRB 번호</th>
			<th>연구자 이름</th>
			<th>요청기관</th>
			<th>연계기관</th>
			<th>데이터전송</th>
		</tr>
		
		
		<c:forEach items="${nhisList_sendData}" var="nhisList_sendData">
			
		<tr>
			<td>${nhisList_sendData.IRB}</td>
			<td>${nhisList_sendData.rName}</td>
			<td>${nhisList_sendData.requestORG }</td>
			<td>${nhisList_sendData.linkedORG }</td>
		
			
			<td>
			<form action="nhis_sendData" method="post">
			<input type="hidden" name="IRB" value="${nhisList_sendData.IRB}" />
			<input type="hidden" name="requestORG" value="${nhisList_sendData.requestORG }"/>
			<input type="hidden" name="linkedORG" value="${nhisList_sendData.linkedORG }"/>
			<c:if test="${nhisList_sendData.sendData == '0' && nhisList_sendData.takeLinkNum == '1'}">
			<input type="submit" value="전송" />
			</c:if>
			<c:if test="${nhisList_sendData.sendData == '1' }">
			<input type="submit" value="전송완료" disabled="disabled" />
			</c:if>
			<c:if test="${nhisList_sendData.sendData == '0' && nhisList_sendData.takeLinkNum == '0' }" >
			<input type="submit" value="연결번호없음" disabled="disabled" />
			</c:if>
			</form>
			</td>
		</tr>
		</c:forEach>
		</table>
		
		

</body>
</html>