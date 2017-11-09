<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 강의계획서 수정 페이지</title>
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

function planupdate(){

    var boardCode = document.getElementsByName("boardCode")[0];
    var boardContent = document.getElementsByName("boardContent")[0];
    var boardTitle = document.getElementsByName("boardTitle")[0];
    var roomCode = document.getElementsByName("roomCode")[0];

    createForm("learningPlanUpdateform", "learningPlanUpdate", "post");

    var form = document.getElementsByName("learningPlanUpdateform")[0];
    
    form.appendChild(boardCode);
    form.appendChild(boardContent);
    form.appendChild(boardTitle);
    form.appendChild(roomCode);
    
    form.submit();

}

</script>
<body>
<input type="hidden" value="${roomcode }" name="roomCode" />
<input type="hidden" value="${boardcode }" name="boardCode" />
<div style="width:'50px'">
<table border="2" width="100%" height="300px"  >
</br>
	<thead class="table table-striped table-bordered">
		<tr>
			<th >제목</th>
			<td><input type="text" size="64%" value="${title }" name="boardTitle" class="form-control"></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="2"><textarea class="form-control" cols="71" rows="20" name="boardContent">${content }</textarea></td>
		</tr>
	</tbody>
</table>
</br>
<div align="center"><input type="button" value="등록" style="width:50pt;height:30pt;" onClick="planupdate()" /></div>
</div>
</body>
</html>