<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
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
	function sendmail(studentEmail) {
		createinput("hidden", "email", studentEmail);
		createForm("mailSenderForm", "mailSender", "post");

		var form = document.getElementsByName("mailSenderForm")[0];
		var email = document.getElementsByName("email")[0];
		var boardTitle = document.getElementsByName("boardTitle")[0];

		var boardContent = document.getElementsByName("boardContent")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardContent);
		form.appendChild(email);

		form.submit();
	}
</script>
<style>
table {
	margin: auto;
}
button {
	background-color: #808080;
	border: 1px solid transparent;
	font-color: ;
}
</style>
</head>

<body>
	<div class="logo">
		<a href="teacher_main.html" class="simple-text"> <img
			src="assets/img/gong_logo.png" alt="공조" width="150*100">
		</a>
	</div>
	<table>
		<br />
		<thead class="table table-striped table-bordered">

			<tr>
				<td>이메일 : <input type="text" name="studentEmail"
					value="${email }">
				</td>
			</tr>
			<tr>
				<td>제목 :&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
					name="boardTitle">
				</td>
			</tr>

		</thead>
		<tbody>
			<tr>
				<td colspan="2">내용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea
						class="form-control" rows="15"
						style="background-color: white; color: black;" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" value="발송하기" class="btn"
					onClick="sendmail('${email }')" />
			</tr>
		</tbody>

	</table>
</body>
</html>