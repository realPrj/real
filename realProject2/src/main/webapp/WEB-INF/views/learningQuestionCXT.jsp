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
<title>공조 ||질문게시판</title>
</head>
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

		createForm("menuform", "tcmenu", "post");

		var form = document.getElementsByName("menuform")[0];
		form.appendChild(caCode);

		form.submit();

	}
	function deleteQuestion(boardTitle, boardData, roomCode, boardId) {

		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);

		createForm("learningQuestionDeleteform", "learningQuestionDelete",
				"post");

		var form = document.getElementsByName("learningQuestionDeleteform")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);

		form.submit();
	}
	function updateQuestion(boardTitle, boardData, roomCode, boardId,
			boardContent) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);
		createinput("hidden", "boardContent", boardContent);

		createForm("learningQuestionUpdateform", "learningQuestionUpdatePage",
				"post");

		var form = document.getElementsByName("learningQuestionUpdateform")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];
		var boardContent = document.getElementsByName("boardContent")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);
		form.appendChild(boardContent);

		form.submit();
	}
	function tag(boardTitle, boardData, roomCode, boardId) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardData);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);
		createinput("hidden", "tagContent", tagContent);

		createForm("learningQuestionTagForm", "learningQuestionTag", "post");

		var form = document.getElementsByName("learningQuestionTagForm")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];
		var tagContent = document.getElementsByName("tagContent")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);
		form.appendChild(tagContent);

		form.submit();
	}
	function tagDelete(boardTitle, boardDate, roomCode, boardId, tagDate, tagId) {
		createinput("hidden", "boardTitle", boardTitle);
		createinput("hidden", "boardDate", boardDate);
		createinput("hidden", "roomCode", roomCode);
		createinput("hidden", "boardId", boardId);
		createinput("hidden", "tagId", tagId);
		createinput("hidden", "tagDate", tagDate);

		createForm("learningQuestionTagDeleteForm",
				"learningQuestionTagDelete", "post");

		var form = document.getElementsByName("learningQuestionTagDeleteForm")[0];

		var boardTitle = document.getElementsByName("boardTitle")[0];
		var boardDate = document.getElementsByName("boardDate")[0];
		var roomCode = document.getElementsByName("roomCode")[0];
		var boardId = document.getElementsByName("boardId")[0];
		var tagDate = document.getElementsByName("tagDate")[0];
		var tagId = document.getElementsByName("tagId")[0];

		form.appendChild(boardTitle);
		form.appendChild(boardDate);
		form.appendChild(roomCode);
		form.appendChild(boardId);
		form.appendChild(tagDate);
		form.appendChild(tagId);

		form.submit();

	}
	function init() {

		var accessForm = document.getElementById("accessForm");

		var write = "${writeId }"; // 값을 던질떄 문자 0 or 1로준다
		var user = "${stCode }";
		if (write == user) {

			accessForm.style.display = "block";

		} else {
			accessForm.style.display = "none";

		}

		// 화면을 동적으로 만들어 준다
	}
	function resize(obj){ // 글의 높이에 맞게 textarea조정
		obj.style.height="1px";
		obj.style.height=(20+obj.scrollHeight)+"px";
	}
</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
 @import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.title{
font-size:25pt; padding-top:5%; padding-left:10%; padding-bottom:2%; font-family: 'Noto Sans KR', sans-serif;
}
.date{
font-size:11pt; padding-left:10%; padding-bottom:1%; font-family: 'Noto Sans KR', sans-serif; color:#858585; 
}
.content{
font-size:11pt; margin:50px 0px 50px 100px;  padding-bottom:1%; font-family: 'Noto Sans KR', sans-serif; color:#505050; 
}
.file{
font-size:13pt; padding-left:10%; font-family: 'Noto Sans KR', sans-serif; 
}
.filetitle{
font-size:12pt; padding-left:10%; padding-bottom:1%; font-family: 'Noto Sans KR', sans-serif; color:#858585;
}
.CTXbtn{
border:none; border-right:1px black solid; background:#F6F6F6; font-size:11pt;
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
.tag{
background:#F6F6F6; width:80%; margin-left:10%;
}
.tag_id{
font-size:11pt; font-family: 'Noto Sans KR', sans-serif; color:#090909; width:12%; padding-left:1%;
}
.tag_date{
font-family: 'Noto Sans KR', sans-serif; color:#757575; width:83%; 
}
.tag_delete{
font-family: 'Noto Sans KR', sans-serif; color:#3F3F3F; 
}
.tag_delete:hover{
cursor:pointer; text-decoration : underline;
}
.tag_content{
font-family: 'Noto Sans KR', sans-serif; color:#505050; padding:10px;
}
table{
width:95%; margin-left:3%; font-family: 'Noto Sans KR', sans-serif; border-bottom:1px solid #CFCFCF; margin-bottom:1%;
}
table:hover{
background:#E6E6E6;
  transition: 1s ease;
}
table:not(hover){
background:#F6F6F6;
  transition: 1s ease;
}

textarea{
font-family: 'Noto Sans KR', sans-serif; width:90%; margin-left:4%; resize:none; overflow:hidden; display:block;
}
.insert{
border-radius:4px; height:30px; padding:0 10px; margin:1% 4% 1% 85%; background:#D6C8A1; color:#fafafa;
}
.insert:hover{
background:#C4B68F;
}
</style>	
<body onLoad="init()">

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

								<li><a onClick="menu('14')">로그아웃</a></li>
							</ul></li>

					</ul>

				</div>
			</div>
			</nav>



			<!-- 질문게시판 내용확인 -->

			<div class="col-lg-35 col-md-12">
            <div class="card">
			<br/>
               <h2 style="font-family: 'Nanum Gothic', sans-serif">
                  <b>질문게시판</b>
               </h2>

					
					 <input type="hidden" name="pageNum" value="${pageNum}">
               	<div class="title">${theme }</div>
               	<div class="date">작성자 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${writeId }</div>
               	<div class="date">등록일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${date }</div>
               	<hr/>
               
               	<div id="ok" class="content">${content }</div>
               	<hr/>
               	<div class="filetitle">첨부파일</div>
               	<div class="file">
              	<c:forEach var="file" items="${list }">
                <a href="download.action?name=${file}">${file}</a>
                </c:forEach>
                </div>
                <div class="date" style="margin-top:5%">댓글</div>
                <div class="tag">
                	${taglists }
                	
                	<textarea onkeydown="resize(this)" name="tagContent" placeholder="댓글을 입력하세요"></textarea> 
                	<input class="insert" type="button" value="댓글입력" id="sendBtn" onClick="tag('${theme }','${date }','${roomcode}','${writeId }')"/>
                </div>
               	<br/> 
				</div>
			</div>
		</div>
	</div>







</body>
</html>