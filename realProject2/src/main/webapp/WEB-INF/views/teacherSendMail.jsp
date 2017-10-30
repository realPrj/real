<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 학생 관리</title>

<script>
//form 생성
function createForm(formname,formaction,formmethod){

   var form = document.createElement("form");

   form.name = formname;
   form.action = formaction;
   form.method = formmethod;

   document.body.appendChild(form);

}

//input 생성
function createinput(itype, iname, ivalue){
   var input = document.createElement("input");
   input.type = itype;
   input.name = iname;
   input.value = ivalue;

   document.body.appendChild(input);
}

//메뉴선택
function menu(ivalue){
   
   createinput("hidden", "caCode", ivalue);
   
   var caCode = document.getElementsByName("caCode")[0];
   
   createForm("menuform","tcmenu","post");
   

   var form = document.getElementsByName("menuform")[0];
   form.appendChild(caCode);
   
   form.submit();
   
}
function stadmin(studentCode){

    createinput("hidden", "studentCode", studentCode);
 
    
    createForm("teacherLearningSTadminCXTform", "teacherLearningSTadminCXT", "post");

    var form = document.getElementsByName("teacherLearningSTadminCXTform")[0];
    
    var studentCode = document.getElementsByName("studentCode")[0];

    form.appendChild(studentCode);

    form.submit();
   
}
function sendmail(studentEmail) {
   createinput("hidden", "email", studentEmail);
   createForm("mailSenderForm", "mailSender", "post");

   var form = document.getElementsByName("mailSenderForm")[0];
   var email = document.getElementsByName("email")[0];
   var boardTitle = document.getElementsByName("boardTitle")[0];
   
   var boardContent = document.getElementsByName("boardContent")[0];
   var email = document.getElementsByName("email")[0];


   form.appendChild(boardTitle);
   form.appendChild(boardContent);
   form.appendChild(email);

   form.submit();
}

</script>
</head>
<body>
<div id="menu">
<table>
   <tr><td><input type="button" value="로그아웃" onClick="" /></td></tr>
   <tr><td><input type="button" value="내 메인으로" onClick="menu('1')" /></td></tr>
   <tr><td><input type="button" value="홈" onClick="menu('2')" /></td></tr>
   <tr><td><input type="button" value="공지사항" onClick="menu('3')" /></td></tr>
   <tr><td><input type="button" value="질문게시판" onClick="menu('4')" /></td></tr>
   <tr><td><input type="button" value="토론게시판" onClick="menu('5')" /></td></tr>
   <tr><td><input type="button" value="과제" onClick="menu('6')" /></td></tr>
   <tr><td><input type="button" value="오답노트" onClick="menu('7')" /></td></tr>
   <tr><td><input type="button" value="성적" onClick="menu('8')" /></td></tr>
   <tr><td><input type="button" value="학생관리" onClick="menu('9')" /></td></tr>
   <tr><td><input type="button" value="자료실" onClick="menu('10')" /></td></tr>
   <tr><td><input type="button" value="쪽지" onClick="menu('11')" /></td></tr>
   <tr><td><input type="button" value="강의계획서" onClick="menu('12')" /></td></tr>
   <tr><td><input type="button" value="문제코드" onClick="menu('13')" /></td></tr>
</table>
</div>


   <!-- 자료실 글쓰기  -->
   <form name="updateForm" action="learningQuUpdate" method="post">
      <br> 이메일 :<input type="text" name="studentEmail"
         value="${email }"> <br>제목 :<input type="text" name="boardTitle">
      <br> 내용
      <textarea name="boardContent" cols=50 rows=20 maxlength=500></textarea>

<br>

      <input type="button" value="발송하기" onClick="sendmail('${email }')" />
   </form>

</body>
</html>