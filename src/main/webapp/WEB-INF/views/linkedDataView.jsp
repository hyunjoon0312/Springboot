<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/resources/css/style_linkedDataView.css" rel="stylesheet" type="text/css" media="all" />
<title>Insert title here</title>
</head>
<body>

	<%
	String IRB = request.getParameter("IRB"); 
	String rName = (String)request.getAttribute("rName");	
	String requestORG = (String)request.getAttribute("requestORG");
	String linkedORG = (String)request.getAttribute("linkedORG");
	%>
	<h1>연계 데이터</h1>
	<p style="font-size:20px"><span>IRB 번호 :</span> <%=IRB %> &emsp; <span>연구자 :</span> <%=rName %> &emsp; <span>연계참여기관 :</span> <%=requestORG %>, <%=linkedORG %> </p>
	<br><br>

<div>
	<table id='table1' border="1" cellspacing="0"
			style="margin: 0 auto; text-align: center;">

			<tr>
				<th>No</th>
				<th id=cdc>REPORT_YMD</th>
				<th id=cdc>ADDRESS</th>
				<th id=cdc>GENDER</th>
				<th id=cdc>DEATH_YMD</th>
				<th id=cdc>DEATH_TIME</th>
				<th id=cdc>DEATH_PLACE</th>
				<th id=cdc>DEATH_JOB</th>
				<th id=cdc>MARRY</th>
				<th id=cdc>EDU</th>
				<th id=cdc>DEATH_CAU1</th>
				<th id=cdc>DEATH_CAU1_Parent</th>
				<th id=cdc>DEATH_AGE</th>

				<th id=nhis>STND_Y</th>
				<th id=nhis>YKIHO_ID</th>
				<th id=nhis>YKIHO_GUBUN_CD</th>
				<th id=nhis>ORG_TYPE</th>
				<th id=nhis>YKIHO_SIDO</th>
				<th id=nhis>SICKBED_CNT</th>
				<th id=nhis>DR_CNT</th>
				<th id=nhis>CT_YN</th>
				<th id=nhis>MRI_YN</th>
				<th id=nhis>PET_YN</th>
				
			</tr>
			<tbody>
			<c:forEach items="${linkedDataList}" var="linkedData">
			<tr>
					<td>${linkedData.No }</td>
					<td>${linkedData.PERSON_ID }</td>
					<td>${linkedData.REPORT_YMD}</td>
					<td>${linkedData.ADDRESS}</td>
					<td>${linkedData.GENDER}</td>
					<td>${linkedData.DEATH_YMD}</td>
					<td>${linkedData.DEATH_TIME}</td>
					<td>${linkedData.DEATH_PLACE}</td>
					<td>${linkedData.DEATH_JOB}</td>
					<td>${linkedData.MARRY}</td>
					<td>${linkedData.EDU}</td>
					<td>${linkedData.DEATH_CAU1}</td>
					<td>${linkedData.DEATH_CAU1_Parent}</td>
					<td>${linkedData.DEATH_AGE}</td>
				
					<td>${linkedData.STND_Y}</td>
					<td>${linkedData.YKIHO_ID}</td>
					<td>${linkedData.YKIHO_GUBUN_CD}</td>
					<td>${linkedData.ORG_TYPE}</td>
					<td>${linkedData.YKIHO_SIDO}</td>
					<td>${linkedData.SICKBED_CNT}</td>
					<td>${linkedData.DR_CNT}</td>
					<td>${linkedData.CT_YN}</td>
					<td>${linkedData.MRI_YN}</td>
					<td>${linkedData.PET_YN}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</div>
			
			
			


</body>
</html>