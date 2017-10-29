<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공조 || 선생님 토론게시판 수정페이지</title>
<script>
function DebateUpdateOk(){
	var f = document.getElementsByName("debateUpdate");
	
	document.debateUpdate.submit();
}
</script>
</head>
<body>
<form name="debateUpdate" method="post" action="DebateUpdate">
 <div>
      제목<input type="text" name="boardTitle" value="${boardTitle }"/>
   </div>
   <div>
      내용<textarea name="boardContent">${boardContent}</textarea>
   </div>
   <div>
      <input type="button" value="작성완료" onClick="DebateUpdateOk()"/>
   </div>

   ${boardDate }
</form>
</body>
</html>