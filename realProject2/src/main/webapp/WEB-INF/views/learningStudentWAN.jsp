<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 오답노트(학생)</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

 		$("#chart_div2").hide();
		
		
		   var sizee = ${size};
		   var dateCode = ${lowest};

		   for(var i = 0; i < parseInt(sizee) ; i++){
		      $("#"+dateCode).hide();
		      dateCode = parseInt(dateCode) + 100;
		   };
		   
		   
		   $("#yearSelect").click(function() {
		   var selectValue = $("#yearSelect").val();
		   var dateCode = ${lowest};
		   for(var i = 0; i < parseInt(sizee); i++){
		      $("#"+dateCode).hide();
		      dateCode = parseInt(dateCode) + 1;
		   };
		      $("#"+selectValue).show();
		      var divbox = $("#divbox");
		      divbox.append($("#"+selectValue));
		      
		      $("#"+selectValue).show();
		      $("#"+selectValue).hide();
		      $("#"+selectValue).show(1000); // 1000ms -> 1초
		      
		   });
		   
		   $("tbody[name *='tbody']").hide(); 
		   var tableList = $("#tableList");
		   tableList.append($("#tbody0").show());
		

		   /* 코멘트 */
			$("input[name = cmClick]").click(function() {
				var valueCode = $(this).attr('id');
				commentCheck(valueCode);
				$("input[name = boardCode]").remove();
			});
		   
	});

	//form 생성
	function createForm(formname, formaction, ta) {

		var form = document.createElement("form");
		form.target = ta;
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
	 function createForm1(formname, formaction, formmethod) {

	      var form = document.createElement("form");

	      form.name = formname;
	      form.action = formaction;
	      form.method = formmethod;

	      document.body.appendChild(form);

	   }
	//메뉴선택
	function menu(ivalue) {

		createinput("hidden", "caCode", ivalue);

		var caCode = document.getElementsByName("caCode")[0];

		createForm1("menuform", "stmenu", "post");

		var form = document.getElementsByName("menuform")[0];
		form.appendChild(caCode);

		form.submit();

	}

	function commentCheck(valueCode) {

		createinput("hidden", "boardCode", valueCode);

		var boardCode = document.getElementsByName("boardCode")[0];

		createForm("learningWANCMCXTPageform", "learningWANCMCXTPage", "POP");

		var form = document.getElementsByName("learningWANCMCXTPageform")[0];
		window.open('', 'POP',
				"width=590, height=440, resizable = no, scrollbars = no");
		form.appendChild(boardCode);

		form.submit();
	}
	
    function pageNumber(value){
 	   $("tbody[name *='tbody']").hide(); 
 	   var tableList = $("#tableList");
 	   tableList.append($("#tbody"+value).show());
 	
    }
	
</script>
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
					<li class="active">
					<a onClick="menu('15')">
					 <i class="ti-user"style="color:#999"></i>
							<p style="color:#999">마이페이지</p>
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
							class="ti-book"style="color:#C90000"></i>
							<p style="color:#C90000">오답노트</p>
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
					<br />
					<h2>
						&nbsp;&nbsp;<b>오답 노트</b>
					</h2>
					<table id="stInformation" class="table table-hover">
						<tr>
							<td>이름</td>
							<td>반</td>
							<td>번호</td>
							<td>총 물어본 문제수</td>
						</tr>
						<tr>
							<td>${studentName }</td>
							<td>${stHalf }</td>
							<td>${stNumber }</td>
							<td>${allSum }</td>
							<td>년도 선택${yearSelect }</td>
						</tr>

					</table>
					 	${typeSumb }
					<div id="divbox"></div>

					<div style="margin-left: 300px" class="table table-hover">

						<%@include file="learningWANgraph.jsp"%>
					</div>
					
					${content3 } ${content } ${content2 } ${average }
					<hr />

				</div>
			</div>
		</div>

	</div>
	<div id="studentInformation"></div>
</body>
</html>