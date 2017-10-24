<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항</title>
<script>
function confirm(boardTitle) {
	var f = document.createElement("form");
	f.name = "boardTitle";
	f.method = "post";
	f.action = "NoticeConfirm";
	document.body.appendChild(f);

	var i = document.createElement("input");
	i.type = "hidden";
	i.name = "boardTitle";
	i.value = boardTitle;
	f.appendChild(i);
	//var bonum = document.getElementsByName("botitle");
	/* var bopsid = document.getElementsByName("bopsid");
	var psid = document.getElementsByName("psid"); */

	/* f.appendChild(psid[count]);
	f.appendChild(bopsid[count]); */
	//f.appendChild(botitle[count]);

	document.boardTitle.submit();
} 
</script>
</head>
<body>
공지사항 페이지
${content }
</body>
</html>