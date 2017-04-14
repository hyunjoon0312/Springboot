<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>연구자</title>
</head>
<body>
<form  method="post" action="linkRequest">

	<fieldset>
		<h3><legend>데이터 연계 요청</legend></h3>
		<ul>
			<li>연구자 이름 : <input type="text" name="rName"></li><br>
			<li>IRB 번호 : <input type="text" name="IRB"/></li><br>
			<li>연계 요청 기관
			<select  name ="request_ORG">
				<option value="cdc" label="권역심뇌혈관센터"></option>
				<option value="nhis" label="건강보험공단"></option>
				<option value="stat" label="통계청"></option>
			</select>	
			</li><br>
			<li>연계 희망 기관</li><br>
			<input type="checkbox" name="nhis" value="1" />건강보험공단
			<input type="checkbox" name="stat" value="1" />통계청
			<input type="checkbox" name="cdc" value="1" />권역심뇌혈관센터
		</ul>
		&nbsp;&nbsp;&nbsp;<input type="submit" value="전송" /> 
	</fieldset>
</form>


		<!-- <input type="button" value="전송" onclick="javascript:dataRequest(this.form);"/> -->
<!-- <script type="text/javascript">
function dataRequest(frm)
{
  var url    ="./requestDataLink.jsp";
  var title  = "연계요청";
  var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,status=no,menubar=no,width=240, height=200, top=0,left=20"; 
  window.open("", title,status); //window.open(url,title,status); window.open 함수에 url을 앞에와 같이
                                            //인수로  넣어도 동작에는 지장이 없으나 form.action에서 적용하므로 생략
                                            //가능합니다.
  frm.target = title;                    //form.target 이 부분이 빠지면 form값 전송이 되지 않습니다. 
  frm.action = url;                    //form.action 이 부분이 빠지면 action값을 찾지 못해서 제대로 된 팝업이 뜨질 않습니다.
  frm.method = "post";
  frm.submit();     
  location.reload(true);
  
  }
</script>
 -->





</body>
</html>