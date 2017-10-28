<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공조 || 토론게시판 </title>
<script>
function confirm(boardTitle, boardDate, identity) {
	var f = document.createElement("form");
	f.name = "boardConfirm";
	f.method = "post";
	if(identity == '1'){
		f.action = "tcDebateConfirm";
	}else{
		f.action = "stDebateConfirm"
	}
	document.body.appendChild(f);

	var title = document.createElement("input");
	title.type = "hidden";
	title.name = "boardTitle";
	title.value = boardTitle;
	f.appendChild(title);
	
	
	var date = document.createElement("input");
	date.type = "hidden";
	date.name = "boardDate";
	date.value = boardDate;
	f.appendChild(date); 
	
	/* var caCode = document.createElement("input");
	caCode.type = "hidden";
	caCode.name = "caCode";
	caCode.value = caCode;
	f.appendChild(caCode); */  
	//var bonum = document.getElementsByName("botitle");
	/* var bopsid = document.getElementsByName("bopsid");
	var psid = document.getElementsByName("psid"); */

	/* f.appendChild(psid[count]);
	f.appendChild(bopsid[count]); */
	//f.appendChild(botitle[count]);

	document.boardConfirm.submit();
} 

function debateInsert(){
	var ff = document.createElement("form");
	ff.name = "debateInsert";
	ff.method = "post";
	ff.action = "DebateInsert";
	document.body.appendChild(ff);
	
	document.debateInsert.submit();
	
}
</script>
</head>
<body>
토론게시판 페이지
${content }
</body>
</html>