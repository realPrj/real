<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 자료실 글 수정하기</title>
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
	function menu(ivalue) {

		createinput("hidden", "caCode", ivalue);

		var caCode = document.getElementsByName("caCode")[0];

		createForm("menuform", "stmenu", "post");

		var form = document.getElementsByName("menuform")[0];
		form.appendChild(caCode);

		form.submit();

	}
	function check(boardTitle, boardContent, roomCode, boardDate, boardId) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardContent", boardContent);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardDate", boardDate);
		createinput("hidden", "boardId", boardId);

		createForm("updateForm", "learningQuUpdate", "post");

		var form = document.getElementsByName("updateForm")[0];

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
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
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
					<li class="active"><a onClick="menu('15')"> <i class="ti-user"style="color:#999"></i>
							<p style="color:#999">마이페이지</p>
					</a></li>
					<!-- 마이페이지로 가기만들기 -->
					<li><a onClick="menu('1')"> <i class="ti-home"></i>
							<p>홈</p>
					</a></li>
					<li><a onClick="menu('3')"> <i class="ti-star"></i>
							<p>공지사항</p>
					</a></li>
					<li><a onClick="menu('4')"> <i class="ti-help" style="color:#C90000"></i>
							<p style="color:#C90000">질문게시판</p>
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
									<li><li><a onClick="menu('14')">로그아웃</a></li></li>
								</ul></li>

						</ul>

					</div>
				</div>
			</nav>


			<!-- 질문게시판수정 -->

			<div class="col-lg-35 col-md-12">
				<div class="card">
				
					<br/>
						<h2 style="font-family: 'Nanum Gothic', sans-serif">
							<b>질문게시판</b>
						</h2>
						<br/>
              

					<%-- <form>
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
													name="boardContent">${boardContent}</textarea></td>
											<input type="hidden" name="roomCode" value="${roomCode }">
											<input type="hidden" name="boardDate" value="${boardDate }">
											<input type="hidden" name="boardId" value="${boardId }">
										</tr>

									</tbody>
								</table>
							</div>
							<div>

								<br /> <br /> <input type="button" class="btn" value="수정 하기"
									onClick="check('${boardTitle }','${boardContent}','${roomCode }','${boardDate }','${boardId }')">

							</div>
						</div> --%>
						
						<div style="margin:20px;">
							<p>제목</p>
							<input type="text" name="boardTitle" class="inputTitle" value="${boardTitle }">
						</div>
						<div style="margin:20px;">
							<p>내용</p>
							<textarea rows="20" id="ok" name="boardContent">${boardContent }</textarea>
							
							</div>
							<div style="margin:20px;">
      						
      						<br/>
      						<input type="hidden" name="roomCode" value="${roomCode }">
							<input type="hidden" name="boardDate" value="${boardDate }">
							<input type="hidden" name="boardId" value="${boardId }">
      						<input class="btn" type="button" value="작성완료" onClick="check('${boardTitle }','${boardContent}','${roomCode }','${boardDate }','${boardId }')"/><br/>
      						<br/>
      						
							</div>
							 
				</div>
			</div>

			<hr>

		</div>



	</div>




</body>
</html>