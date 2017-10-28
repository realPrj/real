<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공조 || 토론게시판 내용확인</title>
<script>
function menu(ivalue, identity){
 	var ff = document.createElement("form");
	ff.name = "menuform";
	ff.method = "post";
	if(identity == '1'){
		ff.action = "tcmenu";
	}else{
		ff.action = "stmenu";
	}
	document.body.appendChild(ff);
	
	var i = document.createElement("input");
	i.type = "hidden";
	i.name = "caCode";
	i.value = ivalue;
	ff.appendChild(i);  
	
	document.menuform.submit();
	
}

function update(boardTitle, boardContent, boardDate){
	var f = document.createElement("form");
	f.name = "debateUpdate";
	f.method = "post";
	f.action = "DebateUpdatePage";
	document.body.appendChild(f);
	
	
	var title = document.createElement("input");
	title.type = "hidden";
	title.name = "boardTitle";
	title.value = boardTitle;
	f.appendChild(title);
	
	var content = document.createElement("input");
	content.type = "hidden";
	content.name = "boardContent";
	content.value = boardContent;
	f.appendChild(content);
	
	var date = document.createElement("input");
	date.type = "hidden";
	date.name = "boardDate";
	date.value = boardDate;
	f.appendChild(date);
	
	document.debateUpdate.submit();
}

function boardDelete(roomCode, boardDate){
	var ff = document.createElement("form");
	ff.name = "debateDelete";
	ff.method = "post";
	ff.action = "DebateDelete";
	document.body.appendChild(ff);

	var code = document.createElement("input");
	code.type = "hidden";
	code.name = "roomCode";
	code.value = roomCode;
	ff.appendChild(code);
	
	var date = document.createElement("input");
	date.type = "hidden";
	date.name = "boardDate";
	date.value = boardDate;
	ff.appendChild(date);

	document.debateDelete.submit();
	
	alert("삭제되었습니다");
}
</script>
</head>
<body>
${content }
</body>
</html>