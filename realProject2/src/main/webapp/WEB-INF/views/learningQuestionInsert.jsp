<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 질문 게시판  글 등록</title>
</head>
<script>
   //form 생성
   function createForm(formname, formaction, formmethod) {

      var form = document.createElement("form");

      form.name = formname;
      form.action = formaction;
      form.method = formmethod;

      document.body.appendChild(form);

   }

   //input 생성
   function createinput(itype, iname, ivalue) {
      var input = document.createElement("input");
      input.type = itype;
      input.name = iname;
      input.value = ivalue;

      document.body.appendChild(input);
   }

   function eventClick(formname, formaction, formmethod) {


      createForm(formname, formaction, formmethod);

      var form = document.getElementsByName(formname)[0];

      form.submit();

   }
   //메뉴선택
   function menu(ivalue){
      
      createinput("hidden", "caCode", ivalue);
      
      var caCode = document.getElementsByName("caCode")[0];
      
      createForm("menuform","stmenu","post");
      

      var form = document.getElementsByName("menuform")[0];
      form.appendChild(caCode);
      
      form.submit();
      
   }

   // 자료실 form
</script>
<body onLoad="${message}">
   <h1>공조</h1>
   <table>
      <tr>
         <td><input type="button" value="로그아웃"
            onClick="eventClick('logoutform','logout','post')" /></td>
      </tr>
      <tr>
         <td><input type="button" value="나의정보"
            onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')" /></td>
      </tr>
   </table>
   <div>
      <div>
      <input type="button" value="질문게시판" onClick="menu('4')" /> 
   </div>
   </div>



   <!-- 자료실 글쓰기  -->
   <form name="learningQuestionInsertForm" action="learningQuestionInsert" method="post"
      enctype="multipart/form-data">
      <br> 제목 : <input type="text" name="boardTitle" size=50
         maxlength=70> <br> 내용 :
      <textarea name="boardContent" cols=50 rows=20 maxlength=500></textarea>
      
      <br> <input multiple="multiple" type="file" name="file" /><input type="hidden"
         name="load" value="Notice" />
      <BUTTON type="SUBMIT">보내기</BUTTON>
      
   </form>

</body>
</html>