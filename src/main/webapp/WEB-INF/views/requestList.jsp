<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.*"%>
<%@page import="org.bigdatacenter.datalink.dto.LinkRequest"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사회보장정보원</title>
</head>
<body>
	<h1>사회보장정보원 요청 데이터 목록</h1>
	<br>
	<br>

	
	<table id="tb" border="1" cellspacing="0"
		style="margin: 0 auto; text-align: center;">


		<tr>
			<th>요청시간</th>
			<th>IRB 번호</th>
			<th>연구자 이름</th>
			<th>요청기관</th>
			<th>연계기관</th>
			<th>암호키 생성</th>
			<th>암호키 전송</th>
		</tr>
	
		
		<c:forEach items="${requestList}" var="requestList">
		<tr>
			<td>${requestList.requestTime }</td>
			<td>${requestList.IRB }</td>
			<td>${requestList.rName }</td>
			<td>
			<c:if test="${requestList.request_ORG == 'cdc' }">
			권역심뇌혈관센터
			<c:set var = "request_ORG" value="권역심뇌혈관센터" />
			</c:if>
			<c:if test="${requestList.request_ORG == 'stat' }">
			통계청
			<c:set var = "request_ORG" value="통계청" />
			</c:if>
			<c:if test="${requestList.request_ORG == 'nhis' }">
			건강보험공단
			<c:set var = "request_ORG" value="건강보험공단" />
			</c:if>
			</td>
			
			<td>
			<c:choose>
				<c:when test="${requestList.nhis == '1' && requestList.stat == '1' && requestList.cdc == '1' }">
				건강보험공단, 통계청, 권역심뇌혈관센터
				<c:set var = "linkedORG" value="건강보험공단, 통계청, 권역심뇌혈관센터" />
				</c:when>
				<c:when test="${requestList.nhis == '1' && requestList.stat == '0' && requestList.cdc == '0' }">
				건강보험공단
				<c:set var = "linkedORG" value="건강보험공단" />
				</c:when>
				<c:when test="${requestList.nhis == '1' && requestList.stat == '1' && requestList.cdc == '0' }">
				건강보험공단, 통계청
				<c:set var = "linkedORG" value="건강보험공단, 통계청" />
				</c:when>
				<c:when test="${requestList.nhis == '1' && requestList.stat == '0' && requestList.cdc == '1' }">
				건강보험공단, 권역심뇌혈관센터
				<c:set var = "linkedORG" value="건강보험공단, 권역심뇌혈관센터" />
				</c:when>
				<c:when test="${requestList.nhis == '0' && requestList.stat == '1' && requestList.cdc == '1' }">
				통계청, 권역심뇌혈관센터
				<c:set var = "linkedORG" value="통계청, 권역심뇌혈관센터" />
				</c:when>
				<c:when test="${requestList.nhis == '0' && requestList.stat == '1' && requestList.cdc == '0' }">
				통계청
				<c:set var = "linkedORG" value="통계청" />
				</c:when>
				<c:when test="${requestList.nhis == '0' && requestList.stat == '0' && requestList.cdc == '1' }">
				권역심뇌혈관센터
				<c:set var = "linkedORG" value="권역심뇌혈관센터" />
				</c:when>
			</c:choose>
			</td>
			<td>
				<form action="createSecretKey" method="post" >
					<input type="hidden" name="IRB" value="${requestList.IRB }" />
					<c:if test="${requestList.createKey == '0' }"><input type="submit" value="생성" /></c:if>
					<c:if test="${requestList.createKey == '1' }"><input type="button" value="생성완료" disabled="disabled"/></c:if>
				</form>
			</td>
			
			
			<td>
				<form action="sendSecretKey" method="post">
					<input type="hidden" name="requestTime" value="${requestList.requestTime }"/>
					<input type="hidden" name="Rname" value="${requestList.rName}" />
					<input type="hidden" name="IRB" value="${requestList.IRB}" />
					<input type="hidden" name="secretKey" value="${requestList.secretKey}" />
					<input type="hidden" name="requestORG" value="${request_ORG}" />
					<input type="hidden" name="linkedORG" value="${linkedORG}" />
					<c:if test="${requestList.createKey == '1' && requestList.sendKey == '0'}" ><input type="submit" value="전송" /></c:if>
					<c:if test="${requestList.createKey == '0' && requestList.sendKey == '0'}" ><input type="submit" value="암호키미생성" disabled="disabled" /></c:if>
					<c:if test="${requestList.sendKey == '1'}" ><input type="submit" value="전송완료" disabled="disabled" /></c:if>
				</form>
			</td>
		</tr>
		</c:forEach>

		


	</table>
</body>
</html>