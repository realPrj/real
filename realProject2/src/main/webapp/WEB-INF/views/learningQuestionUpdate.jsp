<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 자료실 글 수정하기</title>
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

	// 자료실 form
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
		<input type="button" value="질문게시판" onClick="menu('4')" />
	</div>



	<!-- 자료실 글쓰기  -->
	<form name="updateForm" action="learningQuUpdate" method="post">
		<br> 제목 :<input type="text" name="boardTitle"
			value="${boardTitle }"> <br> 내용
		<textarea name="boardContent" cols=50 rows=20 maxlength=500>${ boardContent}</textarea>
		<input type="hidden" name="roomCode" value="${roomCode }"> <input
			type="hidden" name="boardDate" value="${boardDate }"> <input
			type="hidden" name="boardId" value="${boardId }">


		<BUTTON type="SUBMIT">보내기</BUTTON>
	</form>

</body>
</html>