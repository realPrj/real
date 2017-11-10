<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 과제성적</title>
</head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

	
	 $("#selectid").click(function() {
			var stcode = $(this).val();
			scoreShow(stcode);
		});
	
	 });
	
	function createinput(itype, iname, ivalue) {
	   	 var input = document.createElement("input");
	   	 input.type = itype;
	   	 input.name = iname;
	    input.value = ivalue;

	   	 document.body.appendChild(input);

		}


		//form 생성
		function createForm(formname, formaction, formmethod) {

			var form = document.createElement("form");

			form.name = formname;
			form.action = formaction;
			form.method = formmethod;

			document.body.appendChild(form);

		}
	
	function scoreShow(stcode){
		
		$.ajax({
	        type: "post",
	        url: "taskScoreShow",
	        data: { studentCode : stcode},             		
	        dataType: "json",                            
	        timeout : "5000",                              
	        success : function(data) {  
	        	$("#scoreId").empty();
	        	var lengthNum = data.length;
	        	
	        	var html = "";

	        	html += "</br><table align=center>"
		        html += "<tbody align=center>";
	        	html += "<tr>";
	        	html += "<td colspan='2'>";
	        	html += "<h3>"+data[0].studentName+"학생의 과제 성적</h3>";
	        	html += "</td>";
	        	html += "</tr>";
	        	html += "</tbody>";
	        	html += "<tbody align=center>";
	        	html += "<tr>";
	        	html += "<td>";
	        	html += "게시글 제목";
	        	html += "</td>";
	        	html += "<td>";
	        	html += "점수";
	        	html += "</td>";
	        	html += "</tr>";
	        	html += "</tbody>";
	        	for(var i = 0; i < lengthNum; i++ ){
	        		html += "<tbody align=center>";
	    			html += "<tr>";
	    			html += "<td>"+data[i].boardTitle+"</td>";
	    			html += "<td>"+data[i].typeSum+"</td>";
	    			html += "</tr>";
	        		html += "</tbody>";
	        	}

	        	html += "</table>"
	        	
	        	$("#scoreId").append(html);
	           console.log(data);    	   
	           
	        },
	        error : function( error ) { 
	           console.log(error);
	        }
	     }); 
	
	}
	
	
</script>
<body>
${select }
<div id="scoreId"></div>
</body>
</html>