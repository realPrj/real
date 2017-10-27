<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공조 || 선생님 토론게시판 등록</title>
<script>
function debateInsertOk(){
	   alert("작성완료");
	   var f = document.getElementsByName("debateInsert");
	   
	   document.debateInsert.submit();
	}
</script>
</head>
<body>
<form name="debateInsert" method="post" action="learningDebateInsert">
   <div>
      제목<input type="text" name="boardTitle"/>
   </div>
   <div>
      내용<textarea name="boardContent"></textarea>
   </div>
   <div>
      <input type="button" value="작성완료" onClick="debateInsertOk()"/>
   </div>
</form>

</body>
</html>