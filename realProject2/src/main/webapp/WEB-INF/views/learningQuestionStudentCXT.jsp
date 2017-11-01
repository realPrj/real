<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공조 ||질문게시판</title>
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

		createForm("menuform", "stmenu", "post");

		var form = document.getElementsByName("menuform")[0];
		form.appendChild(caCode);

		form.submit();

	}
	function deleteQuestion(boardTitle, boardData, roomCode, boardId) {
	
		
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);

		createForm("learningQuestionDeleteform", "learningQuestionDelete", "post");

		var form = document.getElementsByName("learningQuestionDeleteform")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);

		form.submit(); 
	}
	function updateQuestion(boardTitle, boardData, roomCode, boardId, boardContent) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);
		createinput("hidden", "boardContent", boardContent);

		createForm("learningQuestionUpdateform", "learningQuestionUpdatePage", "post");

		var form = document.getElementsByName("learningQuestionUpdateform")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];
		var boardContent = document.getElementsByName("boardContent")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);
		form.appendChild(boardContent);

		form.submit();
	}
	function init() {

		var accessForm = document.getElementById("accessForm");
	
		var write = "${writeId }"; // 값을 던질떄 문자 0 or 1로준다
		var user = "${stCode }";
		if (write == user) {

			accessForm.style.display = "block";
			

		} else {
			accessForm.style.display = "none";
		
		}
		
		// 화면을 동적으로 만들어 준다
	}

</script>
<body onLoad="init()">
	<h1>공조</h1>
	${stCode }
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
		<input type="button" value="질문게시판" onClick="menu('4')" /> <input
			type="button" value="질문게시판 글쓰러가기"
			onClick="eventClick('dataform','questionInsert','post')" />
	</div>





	<form name="datactx">
		<table>
			<tr id=accessForm>
				<td><input type="button" name="delete" value="삭제"
					onClick="deleteQuestion('${theme }','${date }','${roomcode}','${writeId }')">

					<input type="button" name="update" value="수정"
					onClick="updateQuestion('${theme }','${date }','${roomcode}','${writeId }','${content }')"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="boardTitle" value=${theme }>제목:${theme }</td>
			</tr>
			<tr>
				<td><input type="hidden" name="boardContent" value=${content }>내용:${content }</td>
			</tr>
			<c:forEach var="file" items="${list }">
				<tr>
					<td><a href="download.action?name=${file}">${file}</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td><input type="hidden" name="boardData" value=${date }>날짜:${date }</td>
			</tr>
			<tr>
				<td><input type="hidden" name="boardData" value=${writeId }>글쓴이:${writeId }</td>
			</tr>
			<tr>
				<td><input type="hidden" name="boardData" value=${roomCode }></td>
			</tr>


		</table>
	</form>
	${taglists }




</body>
</html>