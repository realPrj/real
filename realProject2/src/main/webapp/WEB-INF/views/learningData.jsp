<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 자료실</title>
</head>
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
		
		return input;
	}

	function eventClick(formname, formaction, formmethod) {

		createForm(formname, formaction, formmethod);

		var form = document.getElementsByName(formname)[0];

		form.submit();

	}
	//메뉴선택
	function menu(ivalue) {

		createinput("hidden", "caCode", ivalue);

		var caCode = document.getElementsByName("caCode")[0];

		createForm("menuform", "tcmenu", "post");

		var form = document.getElementsByName("menuform")[0];
		form.appendChild(caCode);
		
		form.submit();

	}

	function viewData(referCode, referTitle, referDate) {
		alert(referCode);
		alert(referTitle);
		alert(referDate);
		createinput("hidden", "boardTitle", referTitle);
		createinput("hidden", "boardDate", referDate);
		createinput("hidden", "roomCode", referCode);
		
		createForm("learningDataCXTform", "learningDataCXT", "post");

		var form = document.getElementsByName("learningDataCXTform")[0];
		
		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		
	
		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);

		form.submit();
		
	}
	
</script>
<body onLoad="${message}">
	<h1>공조</h1>
	<table>
		<tr>
			<td><input type="button" value="로그아웃"
				onClick="eventClick('logoutform','logout','post')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="나의정보"
				onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')" /></td>
		</tr>
	</table>
	<div>
		<input type="button" value="자료실" onClick="menu('10')" /> <input
			type="button" value="자료실 글쓰러가기"
			onClick="eventClick('dataform','DataInsert','post')" />
	</div>
	<div>${content}</div>
	${datalist }

</body>
</html>