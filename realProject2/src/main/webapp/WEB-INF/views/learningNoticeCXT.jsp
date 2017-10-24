<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항 내용</title>
<script>
 	function confirm(count) {
		var f = document.createElement("form");
		f.name = "ex";
		f.method = "post";
		f.action = "NoticeConfirm";
		document.body.appendChild(f);

		var bonum = document.getElementsByName("botitle");
		/* var bopsid = document.getElementsByName("bopsid");
		var psid = document.getElementsByName("psid"); */

		/* f.appendChild(psid[count]);
		f.appendChild(bopsid[count]); */
		f.appendChild(botitle[count]);

		document.ex.submit();
	} 
</script>
</head>
<body>
${content }
</body>
</html>