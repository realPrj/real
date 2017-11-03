<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공조 || 토론게시판 내용확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

function debateTagInsert(boardTitle, boardDate, boardId){
	
	var tagForm = document.createElement("form");
	tagForm.name = "debateTagInsert";
	tagForm.method = "post";
	tagForm.action = "DebateTagInsert";
	document.body.appendChild(tagForm);
	
	var content = document.getElementsByName("tagContent")[0];
	tagForm.appendChild(content);
	
	/* var stcode = document.createElement("input");
	stcode.type = "hidden";
	stcode.name = "stCode";
	stcode.value = stCode;
	tagForm.appendChild(stcode);
	
	var roomcode = document.createElement("input");
	roomcode.type = "hidden";
	roomcode.name = "roomCode";
	roomcode.value = roomCode;
	tagForm.appendChild(roomcode); */
	
	var title = document.createElement("input");
	title.type = "hidden";
	title.name = "boardTitle";
	title.value = boardTitle;
	tagForm.appendChild(title);
	
	var date = document.createElement("input");
	date.type = "hidden";
	date.name = "boardDate";
	date.value = boardDate;
	tagForm.appendChild(date);
	
	var id = document.createElement("input");
	id.type = "hidden";
	id.name = "boardId";
	id.value = boardId;
	tagForm.appendChild(id);
	
	
	
	document.debateTagInsert.submit();
		
}

function TagUpdate(){
	
}

function TagDelete(tagDate, boardDate){
	
	var tagDeleteForm = document.createElement("form");
	tagDeleteForm.name = "debateTagDelete";
	tagDeleteForm.method = "post";
	tagDeleteForm.action = "DebateTagDelete";
	document.body.appendChild(tagDeleteForm);
	
	var boarddate = document.createElement("input");
	boarddate.type = "hidden";
	boarddate.name = "boardDate";
	boarddate.value = boardDate;
	tagDeleteForm.appendChild(boarddate); 
	
	var tagdate = document.createElement("input");
	tagdate.type = "hidden";
	tagdate.name = "tagDate";
	tagdate.value = tagDate;
	tagDeleteForm.appendChild(tagdate);
	
	document.debateTagDelete.submit();
}

</script>
</head>
<body>
${content }

${debateTagList }
</body>
</html>