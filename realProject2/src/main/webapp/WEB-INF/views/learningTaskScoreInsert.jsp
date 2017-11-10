<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 과제 점수 등록</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

	 
	//form 생성
	function createForm(formname, formaction, formmethod) {

		var form = document.createElement("form");

		form.name = formname;
		form.action = formaction;
		form.method = formmethod;

		document.body.appendChild(form);

	}
	
	//input 생성
	function createinput(itype, iname, ivalue) {
		var input = document.createElement("input");
		input.type = itype;
		input.name = iname;
		input.value = ivalue;

		document.body.appendChild(input);
	}

	function sexualInsert(){
		
		var go = document.getElementsByName("number")[0].value;

		createinput("hidden", "typeSum", go);
		
		var tagCode = document.getElementsByName("tagCode")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var typeSum = document.getElementsByName("typeSum")[0];
		var studentCode = document.getElementsByName("studentCode")[0];
		
		createForm("scoreInsertgoform", "scoreInsertgo", "post");
		
		var form = document.getElementsByName("scoreInsertgoform")[0];
		
		form.appendChild(typeSum);
		form.appendChild(tagCode);
		form.appendChild(roomCode);
		form.appendChild(studentCode);
		
		form.submit();
		
	}
	
	function sexualUpdate(){
		
		var go = document.getElementsByName("number")[0].value;

		createinput("hidden", "typeSum", go);
		
		var tagCode = document.getElementsByName("tagCode")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var typeSum = document.getElementsByName("typeSum")[0];
		
		createForm("scoreUpdateform", "scoreUpdate", "post");
		
		var form = document.getElementsByName("scoreUpdateform")[0];
		
		form.appendChild(typeSum);
		form.appendChild(tagCode);
		form.appendChild(roomCode);
		
		form.submit();
		
	}
	
	function onload(){
		${reload};
		${windowclose};
	}
	
	
</script>
<body onLoad="onload()">
<input type="hidden" value="${tagCode }" name="tagCode" />
<input type="hidden" value="${roomCode }" name="roomCode" />
<input type="hidden" value="${studentCode }" name="studentCode" />

<h1>과제 점수</h1>
${content }
</body>
</html>