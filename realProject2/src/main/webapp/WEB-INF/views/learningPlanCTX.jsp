<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 강의 계획서 보기</title>
<link href="assets/css/css.css" rel="stylesheet" />
<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="assets/css/paper-dashboard.css" rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />


<!--  Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link href="assets/css/themify-icons.css" rel="stylesheet">
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("#tableShow").hide(); 
	if(${check } != 0){
		$("#tableShow").show();
	}
	
});

function createinput(itype, iname, ivalue) {
    var input = document.createElement("input");
    input.type = itype;
    input.name = iname;
    input.value = ivalue;

    document.body.appendChild(input);
    
    return input;
}

//form 생성
function createForm(formname,formaction,formmethod){

var form = document.createElement("form");

form.name = formname;
form.action = formaction;
form.method = formmethod;

document.body.appendChild(form);

}

function planInsert(boardcode){	// 등록 페이지

    createinput("hidden", "boardCode", boardcode);

    var boardCode = document.getElementsByName("boardCode")[0];

    createForm("learningPlanInsertPageform", "learningPlanInsertPage", "post");

    var form = document.getElementsByName("learningPlanInsertPageform")[0];
    
    form.appendChild(boardCode);
    
    form.submit();
	
    $("input[name = boardCode]").remove();
}

function planUpdatePage(boardcode,roomcode){	// 수정 페이지

    createinput("hidden", "boardCode", boardcode);
    createinput("hidden", "roomCode", roomcode);

    var boardCode = document.getElementsByName("boardCode")[0];
    var boardTitle = document.getElementsByName("boardTitle")[0];
    var boardContent = document.getElementsByName("boardContent")[0];
    var roomCode = document.getElementsByName("roomCode")[0];

    createForm("learningPlanUpdatePageform", "learningPlanUpdatePage", "post");

    var form = document.getElementsByName("learningPlanUpdatePageform")[0];
    
    form.appendChild(boardCode);
    form.appendChild(boardTitle);
    form.appendChild(boardContent);
    form.appendChild(roomCode);
    
    form.submit();
	
    $("input[name = boardCode]").remove();
    $("input[name = boardTitle]").remove();
    $("input[name = boardContent]").remove();
    $("input[name = roomCode]").remove();
   
}


function planDelete(boardcode,roomcode){	// 삭제
	
	 createinput("hidden", "boardCode", boardcode);
	 createinput("hidden", "roomCode", roomcode);

    var boardCode = document.getElementsByName("boardCode")[0];
    var roomCode = document.getElementsByName("roomCode")[0];

    createForm("learningPlanDeleteform", "learningPlanDelete", "post");

    var form = document.getElementsByName("learningPlanDeleteform")[0];
    
    form.appendChild(boardCode);
    form.appendChild(roomCode);
    
    form.submit();
	
    $("input[name = boardCode]").remove();
}

</script>
<body>

<div style="margin-left: 32%;margin-top: 15%;">
		<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	</div>
	<div style="margin-left: 40%;margin-top: 10%;">
	<table id="tableShow">
		<tr>
			<td>제목: ${title }</td>
			
		</tr>
		<tr>
			<td>내용:<textarea class="form-control" cols="71"
								rows="20" name="boardContent" style="margin-left: 70px">${content }</textarea></td>

		</tr>
		<c:forEach var="file" items="${list }">
			<tr>
				
				<td>올린 파일:<a href="download.action?name=${file}">${file}</a></td>
			</tr>
		</c:forEach>
	</table>
	
	${button }
	</div>
</body>
</html>