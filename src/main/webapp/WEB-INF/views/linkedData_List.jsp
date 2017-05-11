<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/resources/css/style_linkedDataView.css" rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안전저장소 데이터 목록</title>
</head>
<body>

	<h1>연계기관에서 연계 요청 받은 데이터 목록</h1>
<br>
<div id="linkedData_List">
	<table id="tb" border="1" cellspacing="0"
		style="margin: 0 auto; text-align: center;">


		<tr>
			<th>IRB 번호</th>
			<th>연구자 이름</th>
			<th>요청기관</th>
			<th>연계기관</th>
			<th>연계데이터 보기</th>
		</tr>
		
		
		<c:forEach items="${linkedDataList}" var="linkedData" >
			
		<tr>
			<td>${linkedData.IRB}</td>
			<td>${linkedData.rName}</td>
			<td>${linkedData.requestORG }</td>
			<td>${linkedData.linkedORG }</td>
		
			
			<td>
			<form action="linkedDataView" method="post">
			<c:if test="${linkedData.linkData == '0' }">
			<input type="submit" value="미연계" disabled="disabled" />
			</c:if>
			<c:if test="${linkedData.linkData == '1' }">
			<input type="hidden" name="IRB" value="${linkedData.IRB}" />
			<input type="hidden" name="rName" value="${linkedData.rName}" />
			<input type="hidden" name="requestORG" value="${linkedData.requestORG}" />
			<input type="hidden" name="linkedORG" value="${linkedData.linkedORG}" />
			
			<input type="submit" value="보기" />
			</c:if>
			</form>
			</td>
		</tr>
		</c:forEach>
		</table>
		
</div>
</body>
</html>