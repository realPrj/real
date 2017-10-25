<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 오답노트 선생님 코멘트</title>
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

//input 생성
function createinput(itype, iname, ivalue){
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}


function commentInsertPage(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANInsertPageform","learningWANInsertPage","POST");
	
	var form = document.getElementsByName("learningWANInsertPageform")[0];
	
	form.appendChild(boardCode);
	
	form.submit();
}

function commentInsertPage(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANInsertPageform","learningWANInsertPage","POST");
	
	var form = document.getElementsByName("learningWANInsertPageform")[0];
	
	form.appendChild(boardCode);
	
	form.submit();
}

function learningWANCMUpdatePage(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANCMUpdatePageform","learningWANCMUpdatePage","POST");
	
	var form = document.getElementsByName("learningWANCMUpdatePageform")[0];
	
	form.appendChild(boardCode);
	
	form.submit();
	
}

function openerReload(){
	opener.location.reload(true); 
	${message};
	${windowclose};
}

</script>
<body onLoad="openerReload()">
${content }
</body>
</html>