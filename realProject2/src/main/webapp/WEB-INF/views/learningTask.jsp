<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 과제</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$("#tableText").hide();

		if ("${checkContent}" == 1) {
			$("#tableText").show();
		}
		;

	});

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

	function insertPage() {

		createForm("learningTaskInsertPageform", "learningTaskInsertPage",
				"post");

		var identity = document.getElementsByName("identity")[0];

		var form = document.getElementsByName("learningTaskInsertPageform")[0];

		form.appendChild(identity);

		form.submit();
	}
	function questionCXT(boardCode){
		  createinput("hidden", "boardCode", boardCode);
	      createForm("learningTaskCXTform", "learningTaskCXT", "post");
	      var form = document.getElementsByName("learningTaskCXTform")[0];      
	      var boardCode = document.getElementsByName("boardCode")[0];
	      
	      form.appendChild(boardCode);


	      form.submit();
		
	}
	
	function update(a,b){
		alert(a);
		alert(b);
	}
	function deleteCXT(boardCode,roomCode){
		 createinput("hidden", "boardCode", boardCode);
		 createinput("hidden", "roomCode", roomCode);
	      createForm("learningTaskCXTDeleteform", "learningTaskCXTDelete", "post");
	      var form = document.getElementsByName("learningTaskCXTDeleteform")[0];      
	      var boardCode = document.getElementsByName("boardCode")[0];
	      
	      form.appendChild(boardCode);


	      form.submit();
	}
</script>
<body onLoad="${message}">
	<input type="hidden" value="${identity }" name="identity" />;
	<table>
		<tr>
			<td><input type="button" value="로그아웃" onClick="" /></td>
		</tr>
		<tr>
			<td><input type="button" value="내 메인으로" onClick="menu('1')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="홈" onClick="menu('2')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="공지사항" onClick="menu('3')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="질문게시판" onClick="menu('4')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="토론게시판" onClick="menu('5')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="과제" onClick="menu('6')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="오답노트" onClick="menu('7')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="성적" onClick="menu('8')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="학생관리" onClick="menu('9')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="자료실" onClick="menu('10')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="쪽지" onClick="menu('11')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="강의계획서" onClick="menu('12')" /></td>
		</tr>
		<tr>
			<td><input type="button" value="문제코드" onClick="menu('13')" /></td>
		</tr>
	</table>
	<!-- 과제 리스트 -->
	<table>
		<tr>
			<td>제목</td>
			<td>날짜</td>
		</tr>
		${taskList }
	</table>

	<input type="button" value="과제 생성" onClick="insertPage()" />

	<!-- 과제 보여주기(제목,내용,날짜 -->
	<table id='tableText'>
		
		<tr>
			<td>제목</td>
			<td>날짜</td>
		</tr>
		<tr>
			<td>${title }</td>
			<td>${date }</td>
		</tr>
		<tr>
			<td>내용</td>
		</tr>
		<tr>
			<td>${content }</td>
		</tr>
		${inputButton }
	</table>

	<!-- 댓글 : 학생이름,파일다운(클릭)) -->
	${tagcontent }

</body>
</html>