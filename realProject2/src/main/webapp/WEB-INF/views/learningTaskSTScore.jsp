<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
   href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
   href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 학생 과제 성적</title>

<meta
   content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
   name='viewport' />
<meta name="viewport" content="width=device-width" />


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

<style>

.portfolio {
scrollbar-highlight-color:#FFD8D8; 
scrollbar-3dlight-color:#FFD8D8; 
scrollbar-face-color:#FFD8D8; 
scrollbar-shadow-color:#FFD8D8; 
scrollbar-darkshadow-color:#FFD8D8; 
scrollbar-track-color:#FFD8D8; 
scrollbar-arrow-color:#FFD8D8;
}

.services {
scrollbar-highlight-color:#E6C8AA; 
scrollbar-3dlight-color:#E6C8AA; 
scrollbar-face-color:#E6C8AA; 
scrollbar-shadow-color:#E6C8AA; 
scrollbar-darkshadow-color:#E6C8AA; 
scrollbar-track-color:#E6C8AA; 
scrollbar-arrow-color:#E6C8AA;
}


.portfolio::-webkit-scrollbar {width: 12px; height: 12px;  }
.portfolio::-webkit-scrollbar-button:start:decrement, 
.portfolio::-webkit-scrollbar-button:end:increment {display: block; width: 12px;height: 12px; background: url() #FFD8D8;}
.portfolio::-webkit-scrollbar-track {     background: #FFD8D8; }
.portfolio::-webkit-scrollbar-thumb {  background: #FFD8D8;  }


.services::-webkit-scrollbar {width: 12px; height: 12px;  }
.services::-webkit-scrollbar-button:start:decrement, 
.services::-webkit-scrollbar-button:end:increment {display: block; width: 12px;height: 12px; background: url() #E6C8AA;}
.services::-webkit-scrollbar-track {     background: #E6C8AA; }
.services::-webkit-scrollbar-thumb {  background: #E6C8AA;  }


.box
{
   display: inline-block;
   /* float:left; */
   height:200px;
   overflow: hidden;
   width:25%;

   -webkit-transition: width 1s;
   -moz-transition: width 1s;
        transition: width 1s;
}
.box.home      { background-color: #2d89ef; }
.box.about     { background-color: #00a300; }
.box.portfolio { background-color: #EDC6C6; }
.box.services  { background-color: #D6BCB0; }
.box.contact   { background-color: #ee1111; }

.box a
{
   color:#FFF;
   text-decoration: none;
   text-align: center;
   vertical-align: middle;
   height:20%;
   display:block;
   padding-top: 15px;
}

.box span
{
    display:block;
    position:relative;
    top:500%;
    text-align: center;

    -webkit-transition: top 1s;
    -moz-transition: top 1s;
    transition: top 1s;
}

.nav:hover .box { width:10%; }

.nav .box:hover { width: 40%; }
<<<<<<< HEAD

.box:hover span{ top:-10%; }

td{
font-size:12pt; padding:0 20px; padding-bottom:1px; 
}

=======

.box:hover span{ top:-10%; }

td{
font-size:12pt; padding:0 20px; padding-bottom:1px; 
}
>>>>>>> 27d89c7d223022001c4d3c51f7faf327395d8067
</style>
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
//메뉴선택
function menu(ivalue) {

	createinput("hidden", "caCode", ivalue);

	var caCode = document.getElementsByName("caCode")[0];

	createForm("menuform", "stmenu", "post");

	var form = document.getElementsByName("menuform")[0];
	form.appendChild(caCode);

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
					<li class="active"><a onClick="menu('15')"> <i
							class="ti-user" style="color:#999"></i>
							<p style="color:#999">마이페이지</p>
					</a></li>
					<!-- 마이페이지로 가기만들기 -->
					<li><a onClick="menu('1')"> <i class="ti-home"></i>
							<p>홈</p>
					</a></li>
					<li><a onClick="menu('3')"> <i class="ti-star"></i>
							<p>공지사항</p>
					</a></li>
					<li><a onClick="menu('4')"> <i class="ti-help"></i>
							<p>질문게시판</p>
					</a></li>
					<li><a onClick="menu('5')"> <i class="ti-pencil-alt2"></i>
							<p>토론게시판</p>
					</a></li>
					<li><a onClick="menu('6')"> <i class="ti-clipboard" style="color:#C90000"></i>
							<p style="color:#C90000">과제</p>
					</a></li>
					<li><a onClick="menu('7')"> <i class="ti-book"></i>
							<p>오답노트</p>
					</a></li>
					<li><a onClick="menu('9')"> <i class="ti-settings"></i>
							<p>수강생</p>
					</a></li>
					<li><a onClick="menu('10')"> <i
							class="ti-shopping-cart-full "></i>
							<p>자료실</p>
					</a></li>
					<li></li>
					<li><a onClick="menu('12')"> <i class="ti-pencil-alt "></i>
							<p>강의계획서</p>
					</a></li>
					<li><a onClick="menu('13')"> <i class="ti-bookmark-alt "></i>
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
								<li><a onClick="menu('11')">쪽지함</a></li>
								<li><a onClick="menu('14')">로그아웃</a></li>
							</ul></li>

					</ul>

				</div>
			</div>
			</nav>
         
         <!-- 소개글 -->

        <%--  <div class="col-lg-50 col-md-12" style="position:absolute; ">
=======
         <%-- <div class="col-lg-50 col-md-12" style="position:absolute; ">
>>>>>>> 27d89c7d223022001c4d3c51f7faf327395d8067
            <div class="card" >

               <div class="content" >
                  <form>
  

<div class="nav">
  
      <div class="box portfolio" >
   <a style="text-align:center;" href="#portfolio"><h1>성적</h1>
   <span style="overflow:auto;">${scoreId }</span></a>
      </div>
     <div class="box services">
   <a href="#services"><h1>평가</h1>
   <span>${scoreId2 }</span></a>
     </div>
</div>

               </form>
               
            </div>
            
         </div>
         
      </div> --%>
      <div class="col-lg-50 col-md-12" style="position:absolute; ">
            <div class="card" >

               <div class="content" >
               <h2>과제성적</h2><br/><br/>
                  <form>
<input type="button" value="과제" class='btn btn-primary' onClick="menu('6')" />  
${select }
<center>
<div class="nav" style="margin-top:10%">
      <div style='height:300px; overflow-y:auto;' class="box portfolio" >
   <a style="text-align:center;" href="#portfolio"><h3>성적</h3>
   <span><div id="scoreId">${scoreId }</div></span></a>
      </div>
    
     <div style='height:300px; overflow-y:auto;' class="box services">
   <a href="#services"><h3>평가</h3>
   <span><div id="scoreId2">${scoreId2 }</div></span></a>
     </div>
</div><br/><br/>
</center>


               </form>
               
            </div>
            
         </div>
         
      </div>
      <hr>

   </div>


</body>

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

</html>