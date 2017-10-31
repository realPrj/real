<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 과제 수정</title>
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

	function updateTask(){
		
		var boardCode = document.getElementsByName("boardCode")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardContent = document.getElementsByName("boardContent")[0];
		
		createForm("learningTaskUpdateform", "learningTaskUpdate", "post");
		
	    var form = document.getElementsByName("learningTaskUpdateform")[0];   
	    
	    form.appendChild(boardCode);
	    form.appendChild(roomCode);
	    form.appendChild(boardTitle);
	    form.appendChild(boardContent);

	    form.submit();
		
	}
	
</script>
<body>
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
<input type="hidden" value="${boardCode }" name="boardCode" />
<input type="hidden" value="${roomCode }" name="roomCode" />
<table id='tableText'>
	<tr>
		<td>
			제목
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" name="boardTitle" value="${boardTitle }" />
		</td>
	</tr>
	<tr>
		<td>
			내용
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" name="boardContent" value="${boardContent }" />
		</td>
	</tr>
	<tr>
		<td>
			<input type="button" value="수정" onClick="updateTask()" />
		</td>
	</tr>
</table>
</body>
</html>