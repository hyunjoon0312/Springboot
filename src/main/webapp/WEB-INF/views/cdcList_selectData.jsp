<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type='text/javascript' src="http://www.seph.dk/jquery-dragcheck/jquery.dragcheck.js"></script>

<script type="text/javascript">
	$(document).ready(
			function() {
				$('#table1 input[type=checkbox]').dragCheck();

				$("#checkAll").change(
						function() {
							$('tbody input[type=checkbox]').prop('checked',
									$(this).prop('checked'));

							if ($(this).prop('checked')) {
								$('tbody tr')
										.css("background-color", "#E4E9F6");
							} else {
								$('tbody tr').css("background-color", "");
							}

						});

				$('tbody input[type=checkbox]').change(
						function() {
							if ($(this).is(":checked")) {
								$(this).closest('tr').css("background-color",
										"#E4E9F6");
							} else {
								$(this).closest('tr').css("background-color",
										"");
							}
						});

			})
</script>
</head>
<body>
	<div style="text-align:center">
		<h1 style="display:inline;">권역심뇌혈관센터 데이터 목록</h1>
		<div style="float:right">
			<input style="width:100px; height:30px; font-size:15px;" id ="back" type="button" value="뒤로가기" onclick="javascript:location.href='./cdc_main';"/>
		</div>
	</div>

	<div style="margin:50px 0 20px 240px;">
		<form method="post" action="sendSelectData" name='f1'>
			<span style="font-size:20px;">IRB 번호 :</span> <select style="width:100px; height:22px; font-size:18px;" name="IRB_Num">
				<c:forEach items="${IRBList}" var="IRBList">
					<option value="${IRBList.sIRB}" label="${IRBList.sIRB }"></option>
				</c:forEach>
			</select> <input type="submit" value="전송" />
	</div>


	<table id='table1' border="1" cellspacing="0"
			style="margin: 0 auto; text-align: center;">

			<tr>
				<th>선택<input name='checks' id="checkAll" class="checkBox"
					type='checkbox'></th>
				<th>주민등록번호</th>
				<th>REPORT_YMD</th>
				<th>ADDRESS</th>
				<th>GENDER</th>
				<th>DEATH_YMD</th>
				<th>DEATH_TIME</th>
				<th>DEATH_PLACE</th>
				<th>DEATH_JOB</th>
				<th>MARRY</th>
				<th>EDU</th>
				<th>DEATH_CAU1</th>
				<th>DEATH_CAU1_Parent</th>
				<th>DEATH_AGE</th>

			</tr>
			<tbody>
			<c:forEach items="${cdcList_selectData}" var="cdcList_selectData">
			<tr>
					<td><input name='checks' class="checkBox" type='checkbox' value="${cdcList_selectData.PERSON_ID }"></td>
						<input type="hidden" name="REPORT_YMD" value="${cdcList_selectData.REPORT_YMD}" />
						<input type="hidden" name="ADDRESS" value="${cdcList_selectData.ADDRESS}" />
						<input type="hidden" name="GENDER" value="${cdcList_selectData.GENDER}" />
						<input type="hidden" name="DEATH_YMD" value="${cdcList_selectData.DEATH_YMD}" />
						<input type="hidden" name="DEATH_TIME" value="${cdcList_selectData.DEATH_TIME}" />
						<input type="hidden" name="DEATH_PLACE" value="${cdcList_selectData.DEATH_PLACE}" />
						<input type="hidden" name="DEATH_JOB" value="${cdcList_selectData.DEATH_JOB}" />
						<input type="hidden" name="MARRY" value="${cdcList_selectData.MARRY}" />
						<input type="hidden" name="EDU" value="${cdcList_selectData.EDU}" />
						<input type="hidden" name="DEATH_CAU1" value="${cdcList_selectData.DEATH_CAU1}" />
						<input type="hidden" name="DEATH_CAU1_Parent" value="${cdcList_selectData.DEATH_CAU1_Parent}" />
						<input type="hidden" name="DEATH_AGE" value="${cdcList_selectData.DEATH_AGE}" />


					<td>${cdcList_selectData.PERSON_ID }</td>
					<td>${cdcList_selectData.REPORT_YMD}</td>
					<td>${cdcList_selectData.ADDRESS}</td>
					<td>${cdcList_selectData.GENDER}</td>
					<td>${cdcList_selectData.DEATH_YMD}</td>
					<td>${cdcList_selectData.DEATH_TIME}</td>
					<td>${cdcList_selectData.DEATH_PLACE}</td>
					<td>${cdcList_selectData.DEATH_JOB}</td>
					<td>${cdcList_selectData.MARRY}</td>
					<td>${cdcList_selectData.EDU}</td>
					<td>${cdcList_selectData.DEATH_CAU1}</td>
					<td>${cdcList_selectData.DEATH_CAU1_Parent}</td>
					<td>${cdcList_selectData.DEATH_AGE}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
			
			
			
			
</body>
</html>