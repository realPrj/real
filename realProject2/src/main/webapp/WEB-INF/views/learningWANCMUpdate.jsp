<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 코멘트 수정</title>
</head>
<script>
 
//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

function learningWANCMUpdate(){
	
	var boardCode = document.getElementsByName("boardCode")[0];
	var boardContent = document.getElementsByName("boardContent")[0];
	
	createForm("learningWANCMUpdateform","learningWANCMUpdate","POST");
	
	var form = document.getElementsByName("learningWANCMUpdateform")[0];
	
	form.appendChild(boardCode);
	form.appendChild(boardContent);
	
	form.submit();
	
}


</script>
<style>
textarea{
	display: block;
	width: 80%;
	height: 150px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #5D5D5D;
	background-color: #F6F6F6;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	resize:none;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
} 
.button{
border-radius:4px; height:27px; padding:0 10px; background:#D6C8A1; color:#424242; font-family: 'Noto Sans KR', sans-serif; 
cursor:pointer
}
</style>
<body>
<input type="hidden" value="${boardCode }" name="boardCode" />
<%-- <table>
	<tr>
		<td>내용</td>
		<td><textarea name="boardContent" />${boardContent }</textarea></td>
	</tr>
	<tr>
		<td><input type="button" value="수정" onClick="learningWANCMUpdate()" /></td>
	</tr>
</table> --%>

<div class="title">
내용
</div><br/>
<textarea name="boardContent">${boardContent }</textarea><br/>
<input class="button" type="button" value="수정완료" onClick="learningWANCMUpdate()" />
</body>
</html>