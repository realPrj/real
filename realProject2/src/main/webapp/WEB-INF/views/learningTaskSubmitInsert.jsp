<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생과제제출</title>

<script>

function onload(){

	${message};
	${reload};
	${windowclose};

}

</script>

</head>
<body onLoad="onload()">

   <!-- 자료실 글쓰기  -->
   <form name="fileForm" action="learningSubmitTaskInsert" method="post"
      enctype="multipart/form-data">
      <br> <input multiple="multiple" type="file" name="file" /><input
         type="hidden" name="load" value="Notice" /> <input type="hidden"
         name="boardCode" value="${boardCode }"> <input type="hidden"
         name="roomCode" value="${roomCode }"> <input type="hidden"
         name="title" value="${title }">
      <BUTTON type="SUBMIT">보내기</BUTTON>
   </form>

</body>
</html>