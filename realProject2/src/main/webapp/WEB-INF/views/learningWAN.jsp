<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 오답노트(전체)</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	
	var sizee = ${size};
	var dateCode = 2017;

	for(var i = 0; i < parseInt(sizee) ; i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) - 1;
	};
	
	
	$("#yearSelect").click(function() {
	var selectValue = $("#yearSelect").val();
	var dateCode = 2017;
	for(var i = 0; i < parseInt(sizee); i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) - 1;
	};
		$("#"+selectValue).show();
		var divbox = $("#divbox");
		divbox.append($("#"+selectValue));
	});
	
	
 });
 
 function test(valueCode){
	 alert(valueCode);
	 opener.location.reload();
 }
</script>
<body>
<input type="button" value="전체" onClick="" />
<input type="button" value="학생별" onClick="" /><br>
년도 선택${yearSelect }
${typeSumb }
<div id="divbox"></div>
${content }
</body>
</html>