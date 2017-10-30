<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 오답노트 선생님 코멘트</title>
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
	opener.location.reload(true); 
	${message};
	${windowclose};
}

function windowcloseClick(){
	opener.location.reload(true); 
	${windowcloseClick};
}

</script>
<body onLoad="openerReload()">
${InsertButton }
      <table id="tableText">
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
         <tr>
            <td>경로:${route }</td>
         </tr>
	${inputButton }
      </table>

</body>
</html>