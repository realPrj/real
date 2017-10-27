<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항 내용</title>
<script>
//메뉴선택
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
	f.name = "noticeUpdate";
	f.method = "post";
	f.action = "NoticeUpdatePage";
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
	
	document.noticeUpdate.submit();
}

function boardDelete(roomCode, boardDate){
	var ff = document.createElement("form");
	ff.name = "noticeDelete";
	ff.method = "post";
	ff.action = "NoticeDelete";
	document.body.appendChild(ff);

	var code = document.createElement("input");
	code.type = "hidden";
	code.name = "roomCode";
	code.value = roomCode;
	ff.appendChild(code);
	alert(code.value);
	
	var date = document.createElement("input");
	date.type = "hidden";
	date.name = "boardDate";
	date.value = boardDate;
	ff.appendChild(date);

	document.noticeDelete.submit();
	
	alert("삭제되었습니다");
}
</script>
</head>
<body>
공지사항 내용
${content }
	<%-- <c:forEach var="file" items="${list }">
			<a href="download.action?name=${file}">${file}</a>
	</c:forEach> --%>

</body>
</html>