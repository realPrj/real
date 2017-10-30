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
<body>
<input type="hidden" value="${boardCode }" name="boardCode" />
<table>
	<tr>
		<td>내용</td>
		<td><input type="text" value="${boardContent }" name="boardContent" /></td>
	</tr>
	<tr>
		<td><input type="button" value="수정" onClick="learningWANCMUpdate()" /></td>
	</tr>
</table>
</body>
</html>