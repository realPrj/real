<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
   href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
   href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공조 || 과제</title>
</head>
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
<meta name="viewport" content="width=device-width" />
<link href="assets/css/css.css" rel="stylesheet" />

<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="assets/css/paper-dashboard.css" rel="stylesheet" />

<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />

<!--  Fonts and icons     -->
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href="assets/css/themify-icons.css" rel="stylesheet">

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

   function insertPage() {
	   
      createForm("learningTaskInsertPageform", "learningTaskInsertPage",
            "post");

      var identity = document.getElementsByName("identity")[0];

      var form = document.getElementsByName("learningTaskInsertPageform")[0];

      form.appendChild(identity);

      form.submit();
   }
   
   function questionCXT(boardCode) {
	   
      createinput("hidden", "boardCode", boardCode);
      createForm("learningTaskCXTform", "learningTaskCXT", "post");
      var form = document.getElementsByName("learningTaskCXTform")[0];
      var boardCode = document.getElementsByName("boardCode")[0];

      form.appendChild(boardCode);

      form.submit();
   }
   
   function createForm1(formname, formaction, ta) {

      var form = document.createElement("form");
      form.target = ta;
      form.name = formname;
      form.action = formaction;

      document.body.appendChild(form);

   }
   
   function checkFilec(boardCode, roomCode, studentCode) {

      createinput("hidden", "boardCode", boardCode);
      createinput("hidden", "roomCode", roomCode);
      createinput("hidden", "studentCode", studentCode);

      var boardCode = document.getElementsByName("boardCode")[0];
      var roomCode = document.getElementsByName("roomCode")[0];
      var studentCode = document.getElementsByName("studentCode")[0];

      createForm1("learningTaskForm", "learningTesk",
            "POP");

      var form = document.getElementsByName("learningTaskForm")[0];
      window.open('', 'POP',"width=300px, height=300px, resizable = no, scrollbars = no");
      form.appendChild(boardCode);
      form.appendChild(roomCode);
      form.appendChild(studentCode);

      form.submit();

   }

   function update(boardcode, roomcode, title, content) {
	   
      createinput("hidden", "boardCode", boardcode);
      createinput("hidden", "roomCode", roomcode);
      createinput("hidden", "boardTitle", title);
      createinput("hidden", "boardContent", content);

      var boardCode = document.getElementsByName("boardCode")[0];
      var roomCode = document.getElementsByName("roomCode")[0];
      var boardTitle = document.getElementsByName("boardTitle")[0];
      var boardContent = document.getElementsByName("boardContent")[0];

      createForm("learningTaskUpdatePageform", "learningTaskUpdatePage","post");

      var form = document.getElementsByName("learningTaskUpdatePageform")[0];

      form.appendChild(boardCode);
      form.appendChild(roomCode);
      form.appendChild(boardTitle);
      form.appendChild(boardContent);

      form.submit();
   }

   function deleteCXT(boardCode, roomCode) {
	   
      createinput("hidden", "boardCode", boardCode);
      createinput("hidden", "roomCode", roomCode);
      createForm("learningTaskCXTDeleteform", "learningTaskCXTDelete", "post");
      var form = document.getElementsByName("learningTaskCXTDeleteform")[0];
      var boardCode = document.getElementsByName("boardCode")[0];
      var roomCode = document.getElementsByName("roomCode")[0];

      form.appendChild(boardCode);
      form.appendChild(roomCode);

      form.submit();
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
   
   function taskscorepage(){   // 과제성적
      
      createForm("taskScorePageform","taskScorePage","post");

      var form = document.getElementsByName("taskScorePageform")[0];
      
      form.submit();
   }
   
   function scorePage(tagcode,roomcode,stcode){

      createinput("hidden", "tagCode", tagcode);
      createinput("hidden", "roomCode", roomcode);
      createinput("hidden", "studentCode", stcode);
      
      var tagCode = document.getElementsByName("tagCode")[0];
      var roomCode = document.getElementsByName("roomCode")[0];
      var studentCode = document.getElementsByName("studentCode")[0];

      createForm1("scoreInsertPageform", "scoreInsertPage","POP");

      var form = document.getElementsByName("scoreInsertPageform")[0];
      window.open('', 'POP',"width=300px, height=300px, resizable = no, scrollbars = no");
      
      form.appendChild(tagCode);
      form.appendChild(roomCode);
      form.appendChild(studentCode);
      
      form.submit();
   }
</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.print{
 border:none; font-size:13pt; background:#FFF2E6;font-family: 'Noto Sans KR', sans-serif; 
}

.print:hover{
background : #FFCC97
}

.taskCTX{
border:none; font-sieze:12pt; background:#FFFFFF
}

.taskCTX:hover{
text-decoration : underline; background:#FFFFFF
}

.taskCTX:active{
color:#3669CF
}

.CTXbtn{
border:none; border-right:1px black solid; background:#FFFFFF; font-size:11pt; 
}

.CTXbtn_end{
border:none; background:#FFFFFF;  font-size:11pt; 
}

.CTXbtn:hover{
text-decoration : underline; cursor:pointer
}

.CTXbtn:active{
color:#3669CF;
}

.CTXbtn_end:hover{
text-decoration : underline; cursor:pointer
}

.CTXbtn_end:active{
color:#3669CF;
}

.CTX:hover{
text-decoration : underline; cursor:pointer
}

.CTX:active{
color:#3669CF;
}

.title{
padding:10px 25px 10px 5px; border-bottom:1px solid #8C8C8C
}

</style>
<body onLoad="${message}">
<input type="hidden" value="${identity }" name="identity" />
   <div class="wrapper">
      <div class="sidebar" data-background-color="white" data-active-color="danger">
      
         <!-- 왼쪽메뉴바 영역 -->
         <div class="sidebar-wrapper">
            <div class="logo">
               <a onClick="menu('15')" class="simple-text"> 
               <img src="assets/img/gong_logo.png" alt="공조" width="150*100">
               </a>
            </div>
         
            <ul class="nav">
               <li></li>
               <li class="active">
               <a onClick="menu('15')">
                <i class="ti-user" style="color:#999"></i>
                <p style="color:#999">마이페이지</p>
               </a>
               </li>
               <li><a onClick="menu('1')"> <i
                     class="ti-home"></i>
                     <p>홈</p>
               </a></li>
               <li><a onClick="menu('3')"> 
                     <i class="ti-star"></i>
                     <p>공지사항</p>
               </a></li>
               <li><a onClick="menu('4')"> 
               <i class="ti-help"></i>
                     <p>질문게시판</p>
               </a></li>
               <li><a onClick="menu('5')"> <i
                     class="ti-pencil-alt2"></i>
                     <p>토론게시판</p>
               </a></li>
               <li><a onClick="menu('6')">
                     <i class="ti-clipboard" style="color:#C90000"></i>
                     <p style="color:#C90000">과제</p>
               </a></li>
               <li><a
                  onClick="menu('7')"> <i
                     class="ti-book"></i>
                     <p>오답노트</p>
               </a></li>
               <li><a onClick="menu('9')">
                     <i class="ti-settings"></i>
                     <p>수강생</p>
               </a></li>
               <li><a onClick="menu('10')">
                     <i class="ti-shopping-cart-full "></i>
                     <p>자료실</p>
               </a></li>
               <li></li>
               <li><a onClick="menu('12')" >
                     <i class="ti-pencil-alt "></i>
                     <p>강의계획서</p>
               </a></li>
               <li><a onClick="menu('13')">
                     <i class="ti-bookmark-alt "></i>
                     <p>과목코드</p>
               </a></li>

            </ul>
         </div>
      </div>
      <!-- 상단바 영역 -->


      <div class="main-panel">
         <nav class="navbar navbar-default">
         <div class="container-fluid">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle">
                  <span class="sr-only">Toggle navigation</span> <span
                     class="icon-bar bar1"></span> <span class="icon-bar bar2"></span>
                  <span class="icon-bar bar3"></span>
               </button>
               <a class="navbar-brand" href="#"></a>
            </div>
            <div class="collapse navbar-collapse">
               <ul class="nav navbar-nav navbar-right">

                  <li class="dropdown"><a href="#" class="dropdown-toggle"
                     data-toggle="dropdown"> <i class="ti-bell"></i>
                        <p class="notification"></p>
                        <p>마이메뉴</p> <b class="caret"></b>
                  </a>
                     <ul class="dropdown-menu">
                        <li><li><a onClick="menu('11')">쪽지함</a></li></li>
                        <li><li><a onClick="menu('14')">로그아웃</a></li></li>
                     </ul></li>

               </ul>

            </div>
         </div>
         </nav>


         <!-- 질문게시판 -->
         <div class="col-lg-35 col-md-12">

            <div class="card">
            <br/>
               <h2 style="font-family: 'Nanum Gothic', sans-serif">
                  <b>과제 게시판</b>
               </h2>
         
               <input style="margin-left:80%" class="print" type="button" value="과제 성적" onClick="taskscorepage()" />
               <input type="button" class="print" style="margin-left:1%" value="과제 생성" onClick="insertPage()" />
                <br/>* 과제내용과 제출자명단을 보려면 제목을 클릭하세요
               <br/><br/>

               ${taskList }
               ${button2 }
               <hr />
               
            
               

            </div>
            <div class="card">
           <%--  <br/>
            <h3 style="padding-left:10px; font-family: 'Nanum Gothic', sans-serif">과제정보</h3>
<center>
            <table id="ctx" class="taskinfo">
            
                  <tr>
                     <td><b>제목</b></td>
                     <td><b>내용</b></td>
                     <td><b>날짜</b></td>
                     
                  </tr>
                  <tr>
                     <td>${title }</td>
                     <td style="height:200px">${content }</td>
                     <td>${date }</td>
                     
                  </tr>
                  
                  <tr>
                  <td class="title">제목</td>
                  <td class="title" style="width:550px">${title }</td>
                  </tr>
                  <tr>
                  <td class="title">등록일</td>
                  <td class="title">${date }</td>
                  </tr>
                  <tr>
                  <td class="title" style="border:none"></td>
                  <td colspan="2" class="title" style="padding:20px 3px; border:none">${content }</td>
                  </tr>

               </table>
        </center> --%>

        		${taskInfo }      
                  <div id="button"style="margin-left: 85%">

                 <%--  <button class="CTXbtn" onClick="update('${boardCode}','${roomCode }','${boardTitle }','${boardContent }')">수정</button>
                  <button class="CTXbtn_end" onClick="deleteCXT('${boardCode}','${roomCode }')"></button>
                  <input type="hidden"value="${boardCode }" name="boardCode" /> 
      			  <input type="hidden"value="${roomCode }" name="roomCode" />
      			  <input type="hidden"value="${boardTitle }" name="boardTitle" />
      			  <input type="hidden"value="${boardContent }" name="boardContent" />	 --%>
      			  <%-- ${inputButton } --%>
      			   ${inputButton }


                  </div>
               </div>
               
               <div class="card">
               
               ${taskLists }
              
               </div>
            </div>
         </div>
      </div>

</body>
</html>