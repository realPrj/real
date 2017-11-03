<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생과제제출</title>
</head>
<body>
    <!-- 자료실 글쓰기  -->
   <form name="fileForm" action="learningSubmitTaskInsert" method="post"
      enctype="multipart/form-data">
      <input type="hidden" name="boardCode" value=${boardCode }>${boardCode }
      <input type="hidden" name="roomCode" value=${roomCode }>${roomCode }
      <input type="hidden" name="boardTitle" value=${title }>${title }
    
     <br> 내용 :
      <textarea name="boardContent" cols=50 rows=20 maxlength=500></textarea>
   



      <br> <input multiple="multiple" type="file" name="file" /><input type="hidden"
         name="load" value="Notice" />
      <BUTTON type="SUBMIT">보내기</BUTTON>
   </form>
   
</body>
</html>