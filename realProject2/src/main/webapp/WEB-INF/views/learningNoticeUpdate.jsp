<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 공지사항 수정</title>
<script>
function NoticeUpdateOk(){
	/* var f = document.createElement("form");
	f.name = "noticeUpdate";
	f.method = "post";
	f.action = "NoticeUpdate";
	document.body.appendChild(f);
	
	var date = document.getElementsByName("boardDate")[0];
	var content = document.getElementsByName("boardContent")[0];
	var title = document.getElementsByName("boardTitle")[0];
	
	f.appendChild(date);
	f.appendChild(content);
	f.appendChild(file);
	f.appendChild(title); */
	
	var f = document.getElementsByName("noticeUpdate");
	
	document.noticeUpdate.submit();
	
}
</script>
</head>
<body>
<form name="noticeUpdate" method="post" action="NoticeUpdate" enctype="multipart/form-data">
 <div>
      제목<input type="text" name="boardTitle" value="${boardTitle }"/>
   </div>
   <div>
      내용<textarea name="boardContent">${boardContent}</textarea>
   </div>
   <div>
      <input multiple="multiple" type="file" name="file" /> 
   	  <input type="hidden" name="boardroute" />
   	  <input type="hidden" name="load" value="Notice" />
   </div>
   <div>
      <input type="button" value="작성완료" onClick="NoticeUpdateOk()"/>
   </div>

   ${boardDate }
</form>


</body>
</html>