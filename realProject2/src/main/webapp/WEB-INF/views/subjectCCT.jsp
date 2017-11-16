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

<title>공조 || 코드표</title>
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
<link href="assets/css/css.css" rel="stylesheet" />


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
<link href="assets/js/demo.js" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	   
	$("tbody").click(function() {
		var yearcode = $(this).attr('id');
	    $.ajax({
	        type: "post",
	        url: "subjectCCT1",
	        data: { yearCode : yearcode},             		
	        dataType: "json",                            
	        timeout : "5000",                              
	        success : function(data) {  
	        	$("#number").empty();
	        	var html = "";
	        	html += "<table style=\"margin-top:25px\">"
	        	for(var i =0; i < data[0].allSum; i++){
	        		html += "<tbody id='"+data[i].numberCode+"'>";
	        		html += "<tr>";
	        		html += "<td ><input style=\"margin-left:30px\"class=\"btnn\" type='button' class='btn-sm' value='"+data[i].numberCode+". "+data[i].typeName+"' /></td>";
	        		html += "</tr>";
	        		html += "</tbody>";
	        	}
	        	html += "</table>";
	        		$("#number").append(html);
	           console.log(data);
	           
	        },
	        error : function( error ) {                   
	           console.log(error);
	        }
	     });    
	   
	});
	   
	 });
	 
	 
	 function printClick(){
		 
	/* initBody = document.body.innerHTML;
	     document.body.innerHTML = printtest.innerHTML;
	     */

		 window.print(); 
		 
/* 		 var mywindow = window.open('', 'my div', 'height=400,width=600');
		 mywindow.document.write('<html><head><title>my div</title>');
		 mywindow.document.write('</head><body >');
		 mywindow.document.write(printtest);
		 mywindow.document.write('</body></html>');
		 mywindow.document.close(); // IE >= 10에 필요
		 mywindow.focus(); // necessary for IE >= 10
		 mywindow.print();
		 mywindow.close();
		  */
		 
	 }
	 
	  function createinput(itype, iname, ivalue) {
		      var input = document.createElement("input");
		      input.type = itype;
		      input.name = iname;
		      input.value = ivalue;

		      document.body.appendChild(input);
		      
		      return input;
		 }
	   
	   //form 생성
	 function createForm(formname,formaction,formmethod){

	      var form = document.createElement("form");

	      form.name = formname;
	      form.action = formaction;
	      form.method = formmethod;

	      document.body.appendChild(form);

	  }
	   
	 function menu(ivalue) {
			
		      createinput("hidden", "caCode", ivalue);

		      var caCode = document.getElementsByName("caCode")[0];

		      createForm("menuform", "tcmenu", "post");

		      var form = document.getElementsByName("menuform")[0];
		      form.appendChild(caCode);
		      
		      form.submit();

		 }
	 
	 
	 
</script>

 <style>
 @import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
 @import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

   




.ctt{
margin:15px; float:left; border:dashed #AEAEAE; border-radius:50px 15px 15px 50px; width:290px; height:500px}

h4{
text-align:right; margin:10px
}

.btnn{
background-color : white;
border : none;
border-radius : 5px;
margin : 5px;
height : 30px
}

.btnn:hover{
background: #FAECC5
}

.print{
margin-left:71%; border:none; font-size:12pt; background:#FFF2E6; 
}

.print:hover{
background : #FFCC97
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
					<a href="teacher_main.html" class="simple-text"> <img
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
					<li><a onClick="menu('13')"> <i class="ti-bookmark-alt " style="color:#C90000"></i>
							<p style="color:#C90000">과목코드</p>
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
									<li><a  onClick="menu('14')">로그아웃</a></li>
								</ul></li>

						</ul>

					</div>
				</div>
			</nav>

			
	
			<!-- 과목코드표 -->

<div class="col-lg-60 col-md-12">
				<div class="card">
				
				<div class="container">
				<br/>
								<h2 style="font-family: 'Nanum Gothic', sans-serif"><b>과목코드표</b></h2>
								
								<br/><br/>
							

    <br>
    <input style="font-family: 'Noto Sans KR', sans-serif; font-size:13pt" type="button" class="print" value="과목코드 프린트" onClick="printClick()" />	
    <div>
    	<div class="ctt">
    	<div><h4>과목</h4></div>
    	${sbName }</div>	
    	<div class="ctt">
    	<div><h4>년도</h4></div>
    	${yearCode }</div>
    	<div class="ctt" style="overflow:auto">
    	<div><h4>문제번호</h4></div>
    	<div id="number"></div>
    	</div>
    </div>

    </div>
    
    </div>
    
    </div>
    </div>

</body>
</html>