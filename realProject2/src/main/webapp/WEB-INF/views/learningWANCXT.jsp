<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
   href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
   href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 오답노트 선생님 코멘트</title>
<meta
   content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
   name='viewport' />
<meta name="viewport" content="width=device-width" />

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
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.CTX{
border-radius:4px; font-size:15pt; height:50px; padding:0 10px; width:140px; background:#D6C8A1; color:#424242; font-family: 'Noto Sans KR', sans-serif; text-align:center
}
.CTX:hover{
background:#C4B68F; cursor:pointer
}
img{
margin:10px; margin-bottom:45px; display:bolck
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(document).ready(function() {
	   
	if("${checkCM}" == 1){
		 $("#tableText").hide();		
	};
	   
	
});
 
//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

//input 생성
function createinput(itype, iname, ivalue){
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}


function commentInsertPage(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANInsertPageform","learningWANInsertPage","POST");
	
	var form = document.getElementsByName("learningWANInsertPageform")[0];
	
	form.appendChild(boardCode);
	
	form.submit();
}

function learningWANCMUpdatePage(valueCode){
	
	alert(valueCode);

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANCMUpdatePageform","learningWANCMUpdatePage","POST");
	
	var form = document.getElementsByName("learningWANCMUpdatePageform")[0];
	
	form.appendChild(boardCode);
	
	form.submit();
	
}

function learningWANCMDelete(boardcode,roomcode){

	createinput("hidden", "boardCode", boardcode);
	createinput("hidden", "roomCode", roomcode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	var roomCode = document.getElementsByName("roomCode")[0];
	
	createForm("learningWANCMDeleteform","learningWANCMDelete","POST");
	
	var form = document.getElementsByName("learningWANCMDeleteform")[0];
	
	form.appendChild(boardCode);
	form.appendChild(roomCode);
	
	form.submit();
	
}

 function openerReload(){
	${message};
	${windowclose};
}

function windowcloseClick(){
	${windowcloseClick};
}

</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.title{
font-family: 'Nanum Gothic', sans-serif; text-align:center; font-size:20pt;
}
.info{
font-family: 'Noto Sans KR', sans-serif; text-align:center; font-size:11pt;
}
.content{
overflow:auto; border:1px solid black; border-radius:7px; font-family: 'Noto Sans KR', sans-serif; font-size:11pt; width:75%; height:150px; padding:2%; text-align:left
}
.file{
font-size:12pt; font-family: 'Noto Sans KR', sans-serif; 
}
.button{
font-size:11pt; font-family: 'Noto Sans KR', sans-serif; background:white; padding:4px 10px; margin:0px 8px;
border-radius:4px; color:black; 
}
</style>
<body onLoad="openerReload()">
 <br />
 <center>
   <img src="assets/img/gong_logo.png" alt="공조" width="150" height="70">
   </center>

<center>
${InsertButton }
</center>
      <%-- <table id="tableText">
         <tr>
            <td><input type="hidden" name="boardContent" value="${content }">내용:${content }</td>
         </tr>
         <c:forEach var="file" items="${list }">
            <tr>
               <td><a href="download.action?name=${file}">${file}</a></td>
            </tr>
         </c:forEach>

         <tr>
            <td><input type="hidden" name="boardData" value="${date }">날짜:${date }</td>
         </tr>
         <tr>
            <td><input type="hidden" name="boardData" value="${writeId }">글쓴이:${writeId }</td>
         </tr>

	${inputButton }
      </table> --%>
      <div id="tableText">
      <div class="title">
      	${comment}
      </div><br/>
     <div class="info">
     <input type="hidden" name="boardData" value="${writeId }">${writeId } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
     <input type="hidden" name="boardData" value="${date }">${date }
     </div><br/>
     <center>
     <div class="content">
     ${content }
     </div><br/>
     </center>
     <center>
     <div class="file">
     <c:forEach var="file" items="${list }">
            <tr>
               <td><a href="download.action?name=${file}">${file}</a></td>
            </tr>
         </c:forEach>
     </div><br/>
     </center>
     <center><div class="button">
     ${inputButton }
     </div></center>
     </div>

</body>
</html>