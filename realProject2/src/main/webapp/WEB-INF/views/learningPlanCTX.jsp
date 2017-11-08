<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 강의 계획서 보기</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("#tableShow").hide(); 
	if(${check} != 0){
		$("#tableShow").show();
	}
	
	
});

function createinput(itype, iname, ivalue) {
    var input = document.createElement("input");
    input.type = itype;
    input.name = iname;
    input.value = ivalue;

    document.body.appendChild(input);
    
    return input;
}

//form 생성
function createForm(formname,formaction,formmethod){

var form = document.createElement("form");

form.name = formname;
form.action = formaction;
form.method = formmethod;

document.body.appendChild(form);

}

function planInsert(boardcode){
	alert(boardcode);

/*     createinput("hidden", "boardCode", boardcode);

    var boardCode = document.getElementsByName("boardCode")[0];

    createForm("learningPlanInsertPageform", "learningPlanInsertPage", "post");

    var form = document.getElementsByName("learningPlanInsertform")[0];
    
    form.appendChild(boardCode);
    
    form.submit(); */
	
}

</script>
<body>
<table id="tableShow">
	<tr>
		<td>제목</td>
		<td>${title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${content }</td>
	</tr>
</table>
${insert }
</body>
</html>