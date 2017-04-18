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


	<h1>연계기관에서 연계 요청 받은 데이터 목록</h1>

	<table id="tb" border="1" cellspacing="0"
		style="margin: 0 auto; text-align: center;">


		<tr>
			<th>IRB 번호</th>
			<th>연구자 이름</th>
			<th>요청기관</th>
			<th>연계기관</th>
			<th>데이터연계 및 저장</th>
		</tr>
		
		
		<c:forEach items="${linkerList}" var="linkerList">
			
		<tr>
			<td>${linkerList.IRB}</td>
			<td>${linkerList.rName}</td>
			<td>${linkerList.requestORG }</td>
			<td>${linkerList.linkedORG }</td>
		
			
			<td>
			<form action="linkData" method="post">
			<input type="hidden" name="IRB" value="${linkerList.IRB}" />
			<input type="hidden" name="requestORG" value="${linkerList.requestORG }"/>
			<input type="hidden" name="linkedORG" value="${linkerList.linkedORG }"/>
			<c:if test="${linkerList.linkData == '0' }">
			<input type="submit" value="연계 및 저장" />
			</c:if>
			<c:if test="${linkerList.linkData == '1' }">
			<input type="submit" value="연계 및 저장 완료" disabled="disabled" />
			</c:if>
			</form>
			</td>
		</tr>
		</c:forEach>
		</table>
		
		

</body>
</html>