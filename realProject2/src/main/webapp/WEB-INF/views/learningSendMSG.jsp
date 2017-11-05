<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조</title>

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

</head>
<body>

	<div class="wrapper">
		<div class="sidebar" data-background-color="white"
			data-active-color="danger">


			<!-- 왼쪽메뉴바 영역 -->


			<div class="sidebar-wrapper">
				<div class="logo">
					<a href="teacher_main.html" class="simple-text"> <img
						src="assets/img/gong_logo.png" alt="공조" width="150*100">
					</a>
				</div>

				<ul class="nav">
					<li></li>
					<li class="active"><a
						href="http://localhost/real/student_info.jsp"> <i
							class="ti-user"></i>
							<p>마이페이지</p>
					</a></li>
					<li><a href="http://localhost/real/studentMain.jsp"> <i
							class="ti-home"></i>
							<p>홈</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningNotice.jsp">
							<i class="ti-star"></i>
							<p>공지사항</p>
					</a></li>
					<li><a
						href="http://localhost/real/studentLearningQuestion.jsp"> <i
							class="ti-help"></i>
							<p>질문게시판</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningDebateMain.jsp"> <i
							class="ti-pencil-alt2"></i>
							<p>토론게시판</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningTask.jsp">
							<i class="ti-clipboard"></i>
							<p>과제</p>
					</a></li>
					<li><a
						href="http://localhost/real/studentLearningWrongAnswer.jsp"> <i
							class="ti-book"></i>
							<p>오답노트</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningGrade.jsp">
							<i class="ti-bar-chart"></i>
							<p>성적</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningClass.jsp">
							<i class="ti-settings"></i>
							<p>우리반학생</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningData.jsp">
							<i class="ti-shopping-cart-full "></i>
							<p>자료실</p>
					</a></li>
					<li></li>
					<li><a href="http://localhost/real/studentLearningPlan.jsp">
							<i class="ti-pencil-alt "></i>
							<p>강의계획서</p>
					</a></li>
					<li><a href="http://localhost/real/studentLearningSJcode.jsp">
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
									<li><a href="http://localhost/real/studentLearningMSG.jsp">쪽지함</a></li>
									<li><a href="http://localhost/real/first_join.jsp">로그아웃</a></li>
								</ul></li>

						</ul>

					</div>
				</div>
			</nav>
		<!-- 받는사람,쪽지내용 -->
			<div class="col-lg-35 col-md-12">
				<div class="card">
					
					<div class="content">
						<form>
						<h2 ><b>쪽지함</b></h2>
							<div class="row">
								
								<div class="col-md-3">
							
									<br /> <br /><br /> <br />  <label>받는사람</label> <input type="text"
										class="form-control border-input" placeholder="Username"
										value="michael23">

								</div>
								<br /> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn"
									OnClick="location.href='http://localhost/real/studentLearningMSG2.jsp'">보내기</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn"
									OnClick="location.href='http://localhost/real/studentLearningMSG3.jsp'">받은쪽지</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn"
									OnClick="location.href='http://localhost/real/studentLearningMSG5.jsp'">보낸쪽지</button>
								
							</div>
					</div>
<!-- 작성취소,보내기 버튼 -->
					<div class="row">
						<div class="col-md-8">
							<div class="container">
								<textarea rows="10" class="form-control border-input"
									placeholder="Here can be your description" value="Mike">쪽지내용</textarea>

							</div>
							<br /> &nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn">작성취소</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn">보내기</button>
							<br /><br />
						</div>
						
					</div>

					</form>
				</div>
			</div>
		</div>
		
		<hr>
		
	</div>
	
	
	</div>
	</li>
	</ul>
	</div>
	</div>
	</div>



	</div>
	</div>
	</div>

	</div>
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
