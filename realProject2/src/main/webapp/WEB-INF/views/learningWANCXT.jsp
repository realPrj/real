<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 오답노트 선생님 코멘트</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	
	var sizee = ${size};
	var dateCode = 2017;

	for(var i = 0; i < parseInt(sizee) ; i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) - 1;
	};
	
	
	$("#yearSelect").click(function() {
	var selectValue = $("#yearSelect").val();
	var dateCode = 2017;
	for(var i = 0; i < parseInt(sizee); i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) - 1;
	};
		$("#"+selectValue).show();
		var divbox = $("#divbox");
		divbox.append($("#"+selectValue));
	});
	
	
 });
 
//form 생성
function createForm(formname,formaction,ta){

	var form = document.createElement("form");
	form.target=ta;
	form.name = formname;
	form.action = formaction;

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


function commentCheck(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANCXTPageform","learningWANCXTPage","POP");
	
	var form = document.getElementsByName("learningWANCXTPageform")[0];
	window.open('', 'POP',"width=570, height=350, resizable = no, scrollbars = no");
	form.appendChild(boardCode);
	
	form.submit();
}

function openerReload(){
	opener.location.reload(true); 
}

</script>
<body onLoad="openerReload()">
${content }
</body>
</html>