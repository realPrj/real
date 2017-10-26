<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항 내용</title>
<script>
//메뉴선택
function menu(ivalue){
 	var ff = document.createElement("form");
	ff.name = "menuform";
	ff.method = "post";
	ff.action = "tcmenu";
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
</script>
</head>
<body>
공지사항 내용
${content }
</body>
</html>