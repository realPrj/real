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
<title> 공조 || 선생님 토론게시판 수정페이지</title>
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
function DebateUpdateOk(){
	var f = document.getElementsByName("debateUpdate");
	
	document.debateUpdate.submit();
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
               <a href="teacher_main.html" class="simple-text"> <img
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
               <li><a onClick="menu('8','${identity}')"> <i class="ti-bar-chart"></i>
                     <p>성적</p>
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
						<a onClick="menu('11')">쪽지함</a>
                        <li><a onClick="menu('14')">로그아웃</a></li>
                     </ul></li>

               </ul>

            </div>
         </div>
         </nav>
         <!-- 글쓰기 -->
      <div class="col-lg-35 col-md-12"
				style="display: inline-block; text-align: center;">
				<div class="card">
					<div class="container">

						<h2>
							<b>공지사항 글쓰기</b>
						</h2>
					</div>

					 <form name="debateUpdate" method="post" action="DebateUpdate">
						<div id="content"
							style="display: inline-block; text-align: center;">


							<div class="input-group input-group-md" role="group"
								aria-label="...">
								<table border="2" width="700px" ;
	height="300px";  >
									<br />
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
										</tr>
										


									</tbody>
								</table>
							</div>
							<div>
								  <input type="button" value="작성완료" onClick="DebateUpdateOk()"/>
								   ${boardDate }
								<br /> <br />
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
<%-- <form name="debateUpdate" method="post" action="DebateUpdate">
 <div>
      제목<input type="text" name="boardTitle" value="${boardTitle }"/>
   </div>
   <div>
      내용<textarea name="boardContent">${boardContent}</textarea>
   </div>
   <div>
      <input type="button" value="작성완료" onClick="DebateUpdateOk()"/>
   </div>

   ${boardDate }
</form> --%>
</body>
</html>