<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생과제제출</title>
<!-- <style>
.upload {  
  opacity: 0;       /*input type="file" tag 투명하게 처리*/
  position: relative;
  padding : 0px;
  margin : 0px;
  width:0px;height:28px;filter:alpha(opacity=0);cursor:pointer
}
.input_file{
   display: block;
   width: 60%;
   height: 22px;
   padding: 6px 12px;
   font-size: 14px;
   line-height: 1.42857143;
   color: #5D5D5D;
   background-color: #F6F6F6;
   background-image: none;
   border: 1px solid #ccc;
   border-radius: 4px;
   -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
   box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
   -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
      ease-in-out .15s;
   -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
      .15s;
   transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}
</style>
<script>
function fileNameInput(){
     
    var fName = $('#file').val().split("\\");
       $('#load').val($(fName)[2]);
     
}
</script> -->
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