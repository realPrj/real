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

<title>공조 || 과제학생</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$("#tableText").hide();

		if ("${checkContent}" == 1) {
			$("#tableText").show();
		}
		;

	});

	//form 생성
	function createForm(formname, formaction, formmethod) {

		var form = document.createElement("form");

		form.name = formname;
		form.action = formaction;
		form.method = formmethod;

		document.body.appendChild(form);

	}
	function createForm1(formname,formaction,ta) {

		var form = document.createElement("form");
		form.target=ta;
		form.name = formname;
		form.action = formaction;

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

	function questionCXT(boardCode) {
		createinput("hidden", "boardCode", boardCode);
		createForm("learningTaskCXTStudentform", "learningTaskCXTStudent",
				"post");
		var form = document.getElementsByName("learningTaskCXTStudentform")[0];
		var boardCode = document.getElementsByName("boardCode")[0];

		form.appendChild(boardCode);

		form.submit();

	}
	function confirm(boardTitle, roomCode, boardCode) {
		 alert(boardTitle);
		 alert(roomCode);
		 alert(boardCode);
	      createinput("hidden", "boardTitle", boardTitle);
	      createinput("hidden", "roomCode", roomCode);
	      createinput("hidden", "boardCode", boardCode);
	      var boardTitle = document.getElementsByName("boardTitle")[0];
	      var roomCode = document.getElementsByName("roomCode")[0];
	      var boardCode = document.getElementsByName("boardCode")[0];
	      
	      

	      createForm1("learningSubjectMMinsertForm", "learningSubjectMMinsert", "POP");

	      var form = document.getElementsByName("learningSubjectMMinsertForm")[0];
	      window.open('', 'POP',"width=450, height=400, resizable = no, scrollbars = no");
	      form.appendChild(boardTitle);
	      form.appendChild(roomCode);
	      form.appendChild(boardCode);
	      

	      form.submit();
	   }

	function checkFile(roomCode, boardCode) {
		
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardCode", boardCode);
		
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardCode = document.getElementsByName("boardCode")[0];

		createForm1("checkFileForm", "checkFile", "POP");

		var form = document.getElementsByName("checkFileForm")[0];
		window.open('', 'POP',
				"width=300px, height=300px, resizable = no, scrollbars = no");

		form.appendChild(roomCode);
		form.appendChild(boardCode);

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
	 
	function taskscorepage(){	// 과제성적
			
		createForm("sttaskScorePageform","sttaskScorePage","post");

		var form = document.getElementsByName("sttaskScorePageform")[0];
			
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
border:none; font-sieze:12pt; background:white
}
.taskCTX:hover{
text-decoration : underline
}

.taskCTX:active{
color:#6D6CFF
}
.insert{
border-radius:4px; height:30px; padding:0 10px; background:#D6C8A1; color:#424242; font-family: 'Noto Sans KR', sans-serif; 
}
.insert:hover{
background:#C4B68F;
}
.CTX:hover{
text-decoration : underline; cursor:pointer
}

.CTX:active{
color:#3669CF;
}
</style>
</head>
<body onLoad="${message}">
	<input type="hidden" value="${identity }" name="identity" />
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
					<li class="active">
					<a onClick="menu('15')">
					 <i class="ti-user"></i>
							<p >마이페이지</p>
					</a></li><!-- 마이페이지로 가기만들기 -->
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
							<i class="ti-clipboard"></i>
							<p>과제</p>
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
								<li><a onClick="menu('11')">쪽지함</a></li>
								<li><a onClick="menu('14')">로그아웃</a></li>
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
					<input style="margin-left:90%" class="print" type="button" value="과제 성적" onClick="taskscorepage()" />
					<br/><br/>

					${taskList }
					${button2 }
					
					<hr />
					<h3 style="padding-left:10px">과제정보</h3>
					<table  style="text-align:center" class="table table-hover">
				</div>
				<div class="card">
				<br/>
				<table id="ctx" class="table table-hover">
					<h3 style="padding-left:10px; font-family: 'Nanum Gothic', sans-serif">과제정보</h3>
						<tr>
							<td><b>제목</b></td>
							<td><b>내용</b></td>
							<td><b>날짜</b></td>
							
						</tr>
						<tr>
							<td>${title }</td>
							<td>${content }</td>
							<td>${date }</td>
							
						</tr>

					</table>
					<div style="margin-left: 85%; height:40px">
						${inputButton }
						</div>
					</div>
					
					<div class="card">
					
					${tagcontent }
					</div>
					
					</div>
					
				</div>
		</div>

	</div>



</body>
</html>