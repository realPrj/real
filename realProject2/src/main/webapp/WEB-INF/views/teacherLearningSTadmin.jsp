<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공조 || 학생 관리</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
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
<script>
$(document).ready(function() {
	/* 복사 */
	$("tbody[name *='tbody']").hide();
	var tableList = $("#tableList");
	tableList.append($("#tbody0").show());

});
/*복사  */
function pageNumber(value) {
	$("tbody[name *='tbody']").hide();
	var tableList = $("#tableList");
	tableList.append($("#tbody" + value).show());
}
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
function menu(ivalue, identity) {

   createinput("hidden", "caCode", ivalue);

   var caCode = document.getElementsByName("caCode")[0];
   if(identity == '1'){
	   createForm("menuform", "tcmenu", "post");
   }else{
	   createForm("menuform", "stmenu", "post");
   }
   
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

 function sendMail(studentEmail) {

	 
    createinput("hidden", "email", studentEmail);

    createForm("sendmailForm","sendMail", "post");

    var form = document.getElementsByName("sendmailForm")[0];

    var email = document.getElementsByName("email")[0];

    form.appendChild(email);

    form.submit();

 }
 
 function sendMessage(studentCode, identity){

	 createForm("message", "sendMessagePage", "post");
	 
	 var form = document.getElementsByName("message")[0];
	 
	 createinput("hidden", "studentCode", studentCode);
	 createinput("hidden", "identity", identity);
	 
	 var studentCode = document.getElementsByName("studentCode")[0];
	 var identity = document.getElementsByName("identity")[0];
	 
	 form.appendChild(studentCode);
	 form.appendChild(identity);
	 
	 form.submit();
	 
	
 }
 function byeStudent(studentCode){

	 createForm("byeStudentForm", "byeStudent", "post");	 
	 var form = document.getElementsByName("byeStudentForm")[0];	 
	 createinput("hidden", "studentCode", studentCode);	 
	 var studentCode = document.getElementsByName("studentCode")[0];
	 form.appendChild(studentCode);	 
	 form.submit();
	 
 }
 

</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
.CTX:hover{
text-decoration : underline; cursor:pointer
}

.CTX:active{
color:#3669CF;
}
</style>
</head>
<body onLoad="${message}">
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
               <li class="active"><a onClick="menu('15','${identity}')"> <i class="ti-user" style="color:#999"></i>
                     <p style="color:#999">마이페이지</p>
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
               <li><a onClick="menu('9','${identity}')"> <i class="ti-settings" style="color:#C90000"></i>
                     <p style="color:#C90000">수강생</p>
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


			<!-- 질문게시판 -->
			<div class="col-lg-35 col-md-12">

				
					${teacherInfo }
					
					<div class="card">
					<h2 style="font-family: 'Nanum Gothic', sans-serif">
					<br/>
						<b>수강생</b>
					</h2><br/>
			
					${teacherLearningSTadmin } ${button }
					<hr />
					</div>

					

				</div>
			</div>
		</div>

	</div>



</body>
</html>