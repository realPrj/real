<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생별 정보</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(document).ready(function() {
	
	
	$("#stClick").click(function() {
	
		var selectValue = $("#stClick").val();
	    $.ajax({
	        type: "get",
	        url: "learningSTInformationPage",
	        data: {studentCode : selectValue},             // 전달 값
	        dataType: "html",                              // json, xml, html(text): 안쓰면 html
	        timeout : "5000",                              // 타임아웃
	        success : function(data) {                     // 성공
	           console.log(data);
	           $("#ajax_div").append(data);
	        },
	        error : function( error ) {                     // 실패
	           alert( "error" );
	           console.log(error);
	        }
	     });

	});

	

/* 	var sizee = ${size};
	var dateCode = ${lowest};

	for(var i = 0; i < parseInt(sizee) ; i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) + 1;
	};
	
	
	$("#yearSelect").click(function() {
	var selectValue = $("#yearSelect").val();
	var dateCode = ${lowest};
	for(var i = 0; i < parseInt(sizee); i++){
		$("#"+dateCode).hide();
		dateCode = parseInt(dateCode) + 1;
	};
		$("#"+selectValue).show();
		var divbox = $("#divbox");
		divbox.append($("#"+selectValue));
	}); */
	
	
 });
 
//form 생성
function createForm(formname,formaction,ta){

	var form = document.createElement("form");
	form.target=ta;
	form.name = formname;
	form.action = formaction;

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


function eventClick(valueCode){

	createinput("hidden", "boardCode", valueCode);
	
	var boardCode = document.getElementsByName("boardCode")[0];
	
	createForm("learningWANCMCXTPageform","learningWANCMCXTPage","POP");
	
	var form = document.getElementsByName("learningWANCMCXTPageform")[0];
	window.open('', 'POP',"width=570, height=350, resizable = no, scrollbars = no");
	form.appendChild(boardCode);
	
	form.submit();
}
 
</script>
<body>
<input type="button" value="전체" onClick="" />
<input type="button" value="학생별" onClick="" />
<div id="graph">
<%@include file="learningWANgraph.jsp"%>
</div>
${studentList }
<div id="ajax_div"></div>
</body>
</html>