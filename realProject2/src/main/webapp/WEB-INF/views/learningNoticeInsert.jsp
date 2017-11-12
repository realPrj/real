
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
<title>공조 || 공지사항 등록</title>
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

function NoticeInsertOk(){
   
   var f = document.getElementsByName("noticeInsert")[0];
   if(confirm("공지사항을 등록하시겠습니까?")){
	   document.noticeInsert.submit();
	   alert("공지사항이 등록되었습니다");   
   }else{
	   
   }
   
}

function fileNameInput(){
	  
	 var fName = $('#file').val().split("\\");
	    $('#load').val($(fName)[2]);
	  
}

	
</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css); // 타이틀
 
.title{
	border : gray 1px solid;
	width : 970px;
	height : 200px;
	text-color : gray
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

.file_input_hidden
{
font-size: 45px;
position: absolute;
right: 0px;
top: 0px;
opacity: 0;
 
filter: alpha(opacity=0);
-ms-filter: "alpha(opacity=0)";
-khtml-opacity: 0;
-moz-opacity: 0;
}


.upload {  
  opacity: 0;       /*input type="file" tag 투명하게 처리*/
  position: relative;
  padding : 0px;
  margin : 0px;
  width:0px;height:28px;filter:alpha(opacity=0);cursor:pointer
}
.replace {    /*button tag 에 원하는 스타일 적용*/
  position: absolute;
  width: 80px;
  height: 50px;
  border-radius: 3px;
  font-weight: 10;
  border-color: transparent;
  font-size: 11pt;
  background: #FFF2E6;
  color: #747474;
  cursor: pointer;
}

</style>
</head>
<body>

<div class="wrapper">
      <div class="sidebar" data-background-color="white"
         data-active-color="danger">


         <!-- 왼쪽메뉴바 영역 -->


         <div class="sidebar-wrapper">
            <div class="logo">
               <a onClick="menu('15',${identity})" class="simple-text"> <img
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
								<li><a onClick="menu('11',${identity})">쪽지함</a></li>
								<li><a onClick="menu('14',${identity})">로그아웃</a></li>
							</ul></li>
						 </ul>

     	    </div>
         </div>
         </nav>
         
         
          <!-- <div class="col-lg-35 col-md-12"
            style="display: inline-block; text-align: center;">
            <div class="card">
               <div class="container">
              
                     <h2>
                        <b>공지사항</b>
                     </h2>
               </div>

   <form name="noticeInsert" method="post" action="learningBoardNoticeInsert" enctype="multipart/form-data">
      <br> 제목 : <input type="text" name="boardTitle" size=50
         maxlength=70> <br> 내용 :
      <textarea name="boardContent" cols=50 rows=20 maxlength=500></textarea>
      <br> <input multiple="multiple" type="file" name="file" />
      <input type="hidden" name="load" value="Notice" />
       <input type="button" value="작성완료" onClick="NoticeInsertOk()"/>
   </form>

            </div>
         </div>
      </div> -->
      
      <!-- 글쓰기 -->
     <%--  <div class="col-lg-35 col-md-12"
				style="display: inline-block; text-align: center;">
				<div class="card">
					<div class="center">
					<br/>
						<h2>
							<b>공지사항</b>
						</h2>
					</div>
					
					

					  <form name="noticeInsert" method="post" action="learningBoardNoticeInsert" enctype="multipart/form-data">
						<div id="content"
							style="display: inline-block; text-align: center;">


							<div class="input-group input-group-md" role="group"
								aria-label="...">
								<table border="2" width="700px" ;
	height="300px";  >
									<br />
									<thead class="table table-striped table-bordered">

										<tr>
											<th style="width:13%; padding:15px">제목</th>
											<td><input type="text" name="boardTitle"
												value="${article.title}" class="form-control"
												aria-describedby="basic-addon1"></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2"><textarea class="form-control" rows="20"
													name="boardContent">${article.content}</textarea></td>
										</tr>
										<tr>
											<th style="width:13%; padding:15px">첨부파일</th>
											<td><input multiple="multiple" type="file" name="file" />
      											<input type="hidden" name="load" value="Notice" />
      											

										</tr>


									</tbody>
								</table>
							</div>
							<div>
								  <input class="btn" type="button" value="작성완료" onClick="NoticeInsertOk()"/></td>
								<br /> <br />
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>  --%>
		
		<div class="col-lg-35 col-md-12">
				<div class="card">
				<div class="center">
					<br/>
						<h2 style="font-family: 'Nanum Gothic', sans-serif">
							<b>공지사항</b>
						</h2>
						<br/>
						<form name="noticeInsert" method="post" action="learningBoardNoticeInsert" enctype="multipart/form-data">
						<div style="margin:20px;">
							<p>제목</p>
							<input type="text" name="boardTitle" class="inputTitle">
						</div>
						<div style="margin:20px;">
							<p>내용</p>
							<textarea id="ok" rows="20" name="boardContent"></textarea>
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
      						<input class="btn" type="button" value="작성완료" onClick="NoticeInsertOk()"/><br/>
      						<br/>
      						
							</div>
							</form>
							</div>
							</div>
							</div>
							
			
		</div>



<!-- <form name="noticeInsert" method="post" action="learningBoardNoticeInsert" enctype="multipart/form-data">

   <div>
      제목<input type="text" name="boardTitle"/>
   </div>
   <div>
      내용<textarea name="boardContent"></textarea>
   </div>
   <div>
   <input multiple="multiple" type="file" name="file" /> 
   <input type="hidden" name="boardroute" />
   <input type="hidden" name="load" value="Notice" />
      <input id="imageCall" type="file" name="boardRoute" onChange="loadImageFile()" />
      <input type="button" value="업로드" onClick="imageUpload()"/>
   </div>
   <div>
      <input type="button" value="작성완료" onClick="NoticeInsertOk()"/>
   </div>
</form> -->

</body>
</html>