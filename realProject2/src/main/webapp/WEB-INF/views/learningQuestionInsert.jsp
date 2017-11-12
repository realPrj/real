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
<title>공조 || 질문 게시판 글 등록</title>


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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<script>
function fileNameInput(){
	  
	 var fName = $('#file').val().split("\\");
	    $('#load').val($(fName)[2]);
	  
}
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
   function menu(ivalue) {

      createinput("hidden", "caCode", ivalue);

      var caCode = document.getElementsByName("caCode")[0];

      createForm("menuform", "stmenu", "post");

      var form = document.getElementsByName("menuform")[0];
      form.appendChild(caCode);

      form.submit();

   }
   
   function QuestionInsert(){
	   var f = document.getElementsByName("fileForm")[0];
	   
	   if(confirm("질문을 등록하시겠습니까?")){
		   f.submit();
		   alert("질문이 등록되었습니다");
	   }
	  
   }
</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
 @import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.upload {  
  opacity: 0;       /*input type="file" tag 투명하게 처리*/
  position: relative;
  padding : 0px;
  margin : 0px;
  width:0px;height:28px;filter:alpha(opacity=0);cursor:pointer
}

.inputTitle{
	display: block;
	width: 100%;
	height: 34px;
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

textarea{
	display: block;
	width: 100%;
	height: 200px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #5D5D5D;
	background-color: #F6F6F6;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	resize:none;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
} 

.input_file{
	display: block;
	width: 100%;
	height: 34px;
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
               <li class="active"><a onClick="menu('15')"> <i class="ti-user"></i>
                     <p>마이페이지</p>
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
               <li><a onClick="menu('6')"> <i class="ti-clipboard"></i>
                     <p>과제</p>
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


			<!-- 질문게시판 등록-->

			<div class="col-lg-35 col-md-12">
				<div class="card">
				<div class="center">
					<br/>
						<h2 style="font-family: 'Nanum Gothic', sans-serif">
							<b>질문게시판</b>
						</h2>
						<br/>
						
						<form name="fileForm" action="QuestionInsert123" method="post" enctype="multipart/form-data">
						<div style="margin:20px;">
							<p>제목</p>
							<input type="text" name="boardTitle" class="inputTitle">
						</div>
						<div style="margin:20px;">
							<p>내용</p>
							<textarea rows="20" name="boardContent"></textarea>
							</div>
							<div style="margin:20px;">
							
							<div>
							<table>
							<tr><td style="padding-top:5px; padding-right:10px"><p>첨부파일</p></td>
							<td style="width:400px; padding-right:10px">
							<input id="load" type="text" class="input_file"/>
							
							</td>
							
								<td><input id="file" multiple="multiple" type="file" name="file" onchange="fileNameInput()"
							 class="upload"/><input type="hidden" name="load" value="Notice" />
							<label style="background-color:#FFF2E6; padding:3px; margin-bottom:30px; color:black; border:1px solid #989898; border-radius:6px" for="file">찾아보기..</label></td>
							 </tr>
							</table>
							
							 </div>
      						
      						<br/>
      						<input class="btn" type="button" value="작성완료" onClick="QuestionInsert()"/><br/>
      						<br/>
      						
							</div>
							</form>
							</div>
							</div>
							</div>
							
			
		</div>
		</div>

	

  
</body>
</html>