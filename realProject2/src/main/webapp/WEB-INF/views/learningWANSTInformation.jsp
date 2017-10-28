<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생별 정보</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(document).ready(function() {
	
	$("#chart_div2").hide();
	
 });

//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

//input 생성
function createinput(itype, iname, ivalue){
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}


function eventClick(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANCMCXTPageform","learningWANCMCXTPage","POP");
	
	var form = document.getElementsByName("learningWANCMCXTPageform")[0];
	window.open('', 'POP',"width=570, height=350, resizable = no, scrollbars = no");
	form.appendChild(boardCode);
	
	form.submit();
}


function stSearch(){
	
	var selectValue = $("#stClick").val();

	createinput("hidden", "studentCode", selectValue);
	
	var studentCode = document.getElementsByName("studentCode")[0];
	
	createForm("learningSTInformationform","learningSTInformation","post");
	
	var form = document.getElementsByName("learningSTInformationform")[0];

	form.appendChild(studentCode);
	
	form.submit();
	
	$("#studentInformation").show();
	$("#chart_div").show();
	$("#chart_div2").show();

}

//form 생성
function createForm1(formname, formaction, ta) {

	var form = document.createElement("form");
	form.target = ta;
	form.name = formname;
	form.action = formaction;

	document.body.appendChild(form);

}

function commentCheck(valueCode) {

	createinput("hidden", "boardCode", valueCode);

	var boardCode = document.getElementsByName("boardCode")[0];

	createForm1("learningWANCXTPageform", "learningWANCXTPage", "POP");

	var form = document.getElementsByName("learningWANCXTPageform")[0];
	window.open('', 'POP',
			"width=570, height=350, resizable = no, scrollbars = no");
	form.appendChild(boardCode);

	form.submit();
}


</script>
<body>
<input type="button" value="전체" onClick="" />
<input type="button" value="학생별" onClick="" />
${studentList }
<input type="button" value="검색" onClick="stSearch()" />

<div id="studentInformation">

<table id="stInformation">
	<tr>
		<td>이름</td>
		<td>반</td>
		<td>번호</td>
		<td>총 물어본 문제수</td>
	</tr>
	<tr>
		<td>${studentName }</td>
		<td>${stHalf }</td>
		<td>${stNumber }</td>
		<td>${allSum }</td>
	</tr>
</table>

<%@include file="learningWANgraph.jsp"%>

${content }
${average }

</div>

</body>
</html>