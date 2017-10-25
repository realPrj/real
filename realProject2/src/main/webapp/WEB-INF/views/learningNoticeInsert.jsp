<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항 등록</title>
<script>

</script>
</head>
<body>

<form name="noticeInsert" method="post" action="NoticeInsert">

	<div>
		제목<input type="text" name="boardTitle"/>
	</div>
	<div>
		내용<textarea name="boardContent"></textarea>
	</div>
	<div>
		<input id="imageCall" type="file" name="boardRoute" onChange="loadImageFile()" />
		<input type="button" value="업로드" onClick="imageUpload()"/>
	</div>
	<div>
		<input type="button" value="작성완료" onClick="insertOk()"/>
	</div>
</form>


</body>
</html>