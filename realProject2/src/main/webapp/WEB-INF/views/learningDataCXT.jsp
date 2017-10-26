<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
	function deleteData(boardTitle,boardData,roomCode,boardId) {
		alert(boardTitle);
		alert(boardData);
		alert(roomCode);
		alert(boardId);
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);
		
		createForm("learningDataDeleteform", "learningDataDelete", "post");

		var form = document.getElementsByName("learningDataDeleteform")[0];
		
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
	
	
	
	
	<form name="datactx">
		<table>
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
				<td><input type="hidden" name="boardData" value= ${roomcode}></td>
			</tr>
			<tr>
				<td><input type="hidden" name="boardData" value=${writeId }>글쓴이:${writeId }</td>
			</tr>
			<tr>
				<td>경로:${route }</td>
			</tr>
			<tr>
				<td><input type="button" name="update" value="삭제"
					onClick="deleteData('${theme }','${date }','${roomcode}','${writeId }')"></td>
			</tr>
			
			
			<tr>
				<td><input type="button" name="delete" value="수정" onClick=""></td>
			</tr>
		</table>
		
	</form>






</body>
</html>