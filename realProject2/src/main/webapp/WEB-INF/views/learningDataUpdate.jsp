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
<title>공조 || 자료실 글 업데이트</title>
</head>
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
      
      createForm("menuform","tcmenu","post");
      

      var form = document.getElementsByName("menuform")[0];
      form.appendChild(caCode);
      
      form.submit();
      
   }
   
   function checking(boardTitle, boardContent, roomCode, boardDate, boardId) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardContent", boardContent);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardDate", boardDate);
		createinput("hidden", "boardId", boardId);

		createForm("DataupdateForm", "learningDataUpdate", "post");

		var form = document.getElementsByName("DataupdateForm")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardContent = document.getElementsByName("boardContent")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var boardId = document.getElementsByName("boardId")[0];
		
		form.appendChild(boardTitle);
		form.appendChild(boardContent);
		form.appendChild(roomCode);
		form.appendChild(boardDate);
		form.appendChild(boardId);
		

		form.submit();

	}

   // 자료실 form
</script>
<body onLoad="${message}">
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
					<li><a onClick="menu('8')"> <i class="ti-bar-chart"></i>
							<p>성적</p>
					</a></li>
					<li><a onClick="menu('9')"> <i class="ti-settings"></i>
							<p>우리반학생</p>
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
									<li><a href="http://localhost/real/first_join.jsp">로그아웃</a></li>
								</ul></li>

						</ul>

					</div>
				</div>
			</nav>

			<!-- 자료실게시판수정 -->

			<div class="col-lg-35 col-md-12"
				style="display: inline-block; text-align: center;">
				<div class="card">
					<div class="container">
						<form>
							<h2>
								<b>자료실 수정게시판</b>
							</h2>
					</div>

					<form>
						<div id="content"
							style="display: inline-block; text-align: center;">
							<input type="hidden" name="pageNum" value="${pageNum}"> <input
								type="hidden" name="articleNumber"
								value="${article.articleNumber}">

							<div class="input-group input-group-md" role="group"
								aria-label="...">
								<table border="2" width="700px" height="300px">

									<thead class="table table-striped table-bordered">

										<tr>
											<th style="padding-top: 15px">제목</th>
											<td><input type="text" name="boardTitle"
												value="${boardTitle }" class="form-control"
												aria-describedby="basic-addon1"></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2"><textarea class="form-control" rows="20"
													name="boardContent">${boardContent}</textarea>
											<input type="hidden" name="roomCode" value="${roomCode }">
											<input type="hidden" name="boardDate" value="${boardDate }">
											<input type="hidden" name="boardId" value="${boardId }">
											</td>
										</tr>

									</tbody>
								</table>
							</div>
							<div>

								<br /> <br /> <input type="button" class="btn" value="수정 하기"
									onClick="checking('${boardTitle }','${boardContent}','${roomCode }','${boardDate }','${boardId }')">

							</div>
						</div>
				</div>
			</div>

			<hr>

		</div>



	</div>


   <!-- 자료실 글쓰기  -->
 <!--  <form name="updateForm" action="learningDataUpdate" method="post">
      <br> 제목 :<input type="text" name="boardTitle"
         value="${boardTitle }"> <br> 내용
      <textarea name="boardContent" cols=50 rows=20 maxlength=500>${ boardContent}</textarea>
      <input type="hidden" name="roomCode" value="${roomCode }"> <input
         type="hidden" name="boardDate" value="${boardDate }"> <input
         type="hidden" name="boardId" value="${boardId }">


      <input type="SUBMIT">보내기
   </form>  -->

</body>
</html>