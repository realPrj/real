<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
   href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
   href="assets/img/favicon.png">
<title>공조 || 공지사항 내용</title>
<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="assets/css/paper-dashboard.css" rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />


<!--  Fonts and icons     -->
<link
   href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
   rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
   rel='stylesheet' type='text/css'>
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
<script type="text/javascript"
   src="https://maps.googleapis.com/maps/api/js"></script>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
//메뉴선택
function menu(ivalue, identity){
    var ff = document.createElement("form");
   ff.name = "menuform";
   ff.method = "post";
   if(identity == '1'){
      ff.action = "tcmenu";
   }else{
      ff.action = "stmenu";
   }
   document.body.appendChild(ff);
   
   var i = document.createElement("input");
   i.type = "hidden";
   i.name = "caCode";
   i.value = ivalue;
   ff.appendChild(i);  
   
   document.menuform.submit();
   
}
function update(boardTitle, boardContent, boardDate){
   var f = document.createElement("form");
   f.name = "noticeUpdate";
   f.method = "post";
   f.action = "NoticeUpdatePage";
   document.body.appendChild(f);
   
   
   var title = document.createElement("input");
   title.type = "hidden";
   title.name = "boardTitle";
   title.value = boardTitle;
   f.appendChild(title);
   
   var content = document.createElement("input");
   content.type = "hidden";
   content.name = "boardContent";
   content.value = boardContent;
   f.appendChild(content);
   
   var date = document.createElement("input");
   date.type = "hidden";
   date.name = "boardDate";
   date.value = boardDate;
   f.appendChild(date);
   
   document.noticeUpdate.submit();
}

function boardDelete(roomCode, boardDate){
   var ff = document.createElement("form");
   ff.name = "noticeDelete";
   ff.method = "post";
   ff.action = "NoticeDelete";
   document.body.appendChild(ff);

   var code = document.createElement("input");
   code.type = "hidden";
   code.name = "roomCode";
   code.value = roomCode;
   ff.appendChild(code);
   alert(code.value);
   
   var date = document.createElement("input");
   date.type = "hidden";
   date.name = "boardDate";
   date.value = boardDate;
   ff.appendChild(date);

   document.noticeDelete.submit();
   
   alert("삭제되었습니다");
}
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
</script>
</head>
<body>
<div class="wrapper">
		<div class="sidebar" data-background-color="white"
			data-active-color="danger">


			<!-- 왼쪽메뉴바 영역 -->


			<div class="sidebar-wrapper">
				<div class="logo">
					<a onClick="menu('15')" class="simple-text"> <img
						src="assets/img/gong_logo.png" alt="공조" width="150*100">
					</a>
				</div>

            <ul class="nav">

               <li></li>
               <li class="active"><a onClick="menu('15','${identity}')"> <i class="ti-user"></i>
                     <p>마이페이지</p>
               </a></li>
               <!-- 마이페이지로 가기만들기 -->
               <li><a onClick="menu('1','${identity}')"> <i class="ti-home"></i>
                     <p>홈</p>
               </a></li>
               <li><a onClick="menu('3','${identity}')"> <i class="ti-star"></i>
                     <p>공지사항</p>
               </a></li>
               <li><a onClick="menu('4','${identity}')"> <i class="ti-help"></i>
                     <p>질문게시판</p>
               </a></li>
               <li><a onClick="menu('5','${identity}')"> <i class="ti-pencil-alt2"></i>
                     <p>토론게시판</p>
               </a></li>
               <li><a onClick="menu('6','${identity}')"> <i class="ti-clipboard"></i>
                     <p>과제</p>
               </a></li>
               <li><a onClick="menu('7','${identity}')"> <i class="ti-book"></i>
                     <p>오답노트</p>
               </a></li>
               <li><a onClick="menu('9','${identity}')"> <i class="ti-settings"></i>
                     <p>수강생</p>
               </a></li>
               <li><a onClick="menu('10','${identity}')"> <i class="ti-shopping-cart-full "></i>
                     <p>자료실</p>
               </a></li>
               <li></li>
               <li><a onClick="menu('12','${identity}')"> <i class="ti-pencil-alt "></i>
                     <p>강의계획서</p>
               </a></li>
               <li><a onClick="menu('13','${identity}')"> <i class="ti-bookmark-alt "></i>
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
						<li><a onClick="menu('11',${identity})">쪽지함</a></li>
                        <li><a onClick="menu('14',${identity})">로그아웃</a></li>
                     </ul></li>

               </ul>

            </div>
         </div>
         </nav>


         <!-- 질문게시판 내용확인 -->

         <div class="col-lg-35 col-md-12"
            style="display: inline-block; text-align: center;">
            <div class="card">

               <h2>
                  <b>질문게시판</b>
               </h2>


               <div id="content"
                  style="display: inline-block; text-align: center;">
                  <input type="hidden" name="pageNum" value="${pageNum}"> <input
                     type="hidden" name="articleNumber"
                     value="${article.articleNumber}">

                  <div class="input-group input-group-md" role="group"
                     aria-label="...">
                     <table border="2" width="700px" height="300px";  >
                        <br />
                        <thead class="table table-striped table-bordered">

                           <tr>
                              <th width="15%" style="padding-top: 15px">작성자</th>
                              <td width="80%"> ${boardId }</td>
                           </tr>
                           <tr>
                              <th width="15%" style="padding-top: 15px">제목</th>
                              <td width="80%">${boardTitle }</td>
                           </tr>
                           
                           <tr>
                              <th width=15% style="padding-top: 15px">날짜</th>
                              <td width="80%">${boardDate }</td>
                           </tr>
                        </thead>
                        <tbody>
                           <tr>
                              <td colspan="2"><textarea class="form-control" rows="15"
                                    style="background-color: white; color: black;"
                                    name="content" readonly>${boardContent }</textarea></td>
                           </tr>
                           <tr>
                              <th width="15%">다운로드</th>
                              <td width="80%"><c:forEach var="file" items="${list }">

                                    <a href="download.action?name=${file}">${file}</a>

                                 </c:forEach></td>
                           </tr>
                        </tbody>

                     </table>
               		${content } 
                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>




   
       

</body>
</html>