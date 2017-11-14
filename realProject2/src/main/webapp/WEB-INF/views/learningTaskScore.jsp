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
<title>공조 || 과제성적</title>
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

div {
scrollbar-highlight-color:#6B66FF; 
scrollbar-3dlight-color:#6B66FF; 
scrollbar-face-color:#6B66FF; 
scrollbar-shadow-color:#6B66FF; 
scrollbar-darkshadow-color:#6B66FF; 
scrollbar-track-color:#6B66FF; 
scrollbar-arrow-color:#6B66FF;
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

.box:hover span{ top:-10%; }

td{
font-size:12pt; padding:0 20px; padding-bottom:1px; 
}

</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

	
	 $("#selectid").click(function() {
			var stcode = $(this).val();
			scoreShow(stcode);
		});
	
	 });
	
	function createinput(itype, iname, ivalue) {
	   	 var input = document.createElement("input");
	   	 input.type = itype;
	   	 input.name = iname;
	    input.value = ivalue;

	   	 document.body.appendChild(input);

		}


		//form 생성
		function createForm(formname, formaction, formmethod) {

			var form = document.createElement("form");

			form.name = formname;
			form.action = formaction;
			form.method = formmethod;

			document.body.appendChild(form);

		}
	
	function scoreShow(stcode){
		
		$.ajax({
	        type: "post",
	        url: "taskScoreShow",
	        data: { studentCode : stcode},             		
	        dataType: "json",                            
	        timeout : "5000",                              
	        success : function(data) {  
	        	$("#scoreId").empty();
	        	$("#scoreId2").empty();
	        	var lengthNum = data.length;
	        	var html = "";

	        	html += "<table style='height:100%' align=center>"
		        html += "<tbody align=center>";
	        	html += "<tr>";
	        	html += "<td colspan='2'>";
	        	html += "<h4>"+data[0].studentName+"학생의 과제 성적</h4>";
	        	html += "</td>";
	        	html += "</tr>";
	        	html += "</tbody>";
	        	html += "<tbody align=center>";
	        	html += "<tr>";
	        	html += "<td>";
	        	html += "게시글 제목";
	        	html += "</td>";
	        	html += "<td>";
	        	html += "점수";
	        	html += "</td>";
	        	html += "</tr>";
	        	html += "</tbody>";
	        	for(var i = 0; i < lengthNum; i++ ){
	        		html += "<tbody align=center>";
	    			html += "<tr>";
	    			html += "<td>"+data[i].boardTitle+"</td>";
	    			html += "<td>"+data[i].typeSum+"</td>";
	    			html += "</tr>";
	        		html += "</tbody>";

	        	}

	        	html += "</table>"
	        	
	        	$("#scoreId").append(html);
	        	
	        	
	        	/* 2 */
	        	var html = "";
	        	
	        	html += "<table style='height:100%' align=center>"

		        		html += "<table align=center>"
				        html += "<tbody align=center>";
			        	html += "<tr>";
			        	html += "<td colspan='4'>";
			        	html += "<h4>과제 평가</h4>";
			        	html += "</td>";
			        	html += "</tr>";
			        	html += "</tbody>";
			        	html += "<tbody align=center>";
			        	html += "<tr>";
			        	html += "<td>";
			        	html += "총점";
			        	html += "</td>";
			        	html += "<td>";
			        	html += "평균";
			        	html += "</td>";
			        	html += "<td>";
			        	html += "반등수";
			        	html += "</td>";
			        	html += "<td>";
			        	html += "백분율";
			        	html += "</td>";
			        	html += "</tr>";
			        	html += "</tbody>";
			        	
			        	html += "<tbody align=center>";
			        	html += "<tr>";
			        	html += "<td>";
			        	html += data[0].allSum;
			        	html += "</td>";
			        	html += "<td>";
			        	html += data[0].average;
			        	html += "</td>";
			        	html += "<td>";
			        	html += data[0].rank+"/"+data[0].stNumber;
			        	html += "</td>";
			        	html += "<td>";
			        	html += data[0].percentage;
			        	html += "</td>";
			        	html += "</tr>";
			        	html += "</tbody>";
	        	
	        	
	        	html += "</table>"
	        	$("#scoreId2").append(html);
	        	
	        	console.log(data);  
	          
	           
	        },
	        error : function( error ) { 
	           console.log(error);
	        }
	     }); 
	
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
                           <li><a href="http://localhost/real/studentLearningMSG.jsp">쪽지함</a></li>
                           <li><a href="http://localhost/real/first_join.jsp" >로그아웃</a></li>
                        </ul></li>

                  </ul>

               </div>
            </div>
         </nav>
         
         <!-- 소개글 -->
         
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
   <span><div id="scoreId"></div></span></a>
      </div>
    
     <div style='height:300px; overflow-y:auto;' class="box services">
   <a href="#services"><h3>평가</h3>
   <span><div id="scoreId2"></div></span></a>
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

</body>
</html>