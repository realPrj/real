
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
<title>공조 || 강의계획서</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	   
	$("#yearSelect").click(function() {
		var yearcode = $(this).val();
		
	    $.ajax({
	        type: "post",
	        url: "calendar",
	        data: { month : yearcode},             		
	        dataType: "json",                            
	        timeout : "5000",                              
	        success : function(data) {  
	   
	        	var lengthNum = data.length;
	        	$("#calendarId").empty();
	        	var dayName = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	        	var html = "";

	        	html += "</br><table align=center id='"+data[0].month+"'>"
	        		html += "<tbody align=center>";
        			html += "<tr>";
        			html += "<td><input type='button' style='color:#FF0000' class='btn' value="+dayName[0]+" /></td>";
        			html += "<td><input type='button' class='btn' value="+dayName[1]+" /></td>";
        			html += "<td><input type='button' class='btn' value="+dayName[2]+" /></td>";
        			html += "<td><input type='button' class='btn' value="+dayName[3]+" /></td>";
        			html += "<td><input type='button' class='btn' value="+dayName[4]+" /></td>";
        			html += "<td><input type='button' class='btn' value="+dayName[5]+" /></td>";
        			html += "<td><input type='button' style='color:#0100FF' class='btn' value="+dayName[6]+" /></td>";
        			html += "</tr>";
	        		html += "</tbody>";
	        	for(var i =0; i < data[0].allSize; i++){
	        		var Sunday;
	        		var Monday;
	        		var Tuesday;
	        		var Wednesday;
	        		var Thursday;
	        		var Friday;
	        		var Saturday;

	        		Sunday =((data[i].CheckSunday == 1)? "#FFBB00" : "transparent");
	        		Monday =((data[i].CheckMonday == 1)? "#FFBB00" : "transparent");
	        		Tuesday =((data[i].CheckTuesday == 1)? "#FFBB00" : "transparent");
	        		Wednesday =((data[i].CheckWednesday == 1)? "#FFBB00" : "transparent");
	        		Thursday =((data[i].CheckThursday == 1)? "#FFBB00" : "transparent");
	        		Friday =((data[i].CheckFriday == 1)? "#FFBB00" : "transparent");
	        		Saturday =((data[i].CheckSaturday == 1)? "#FFBB00" : "transparent");
	        		
	        		html += "<tbody align=center>";
	        		html += "<tr>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Sunday+"') name='day"+data[0].month+data[i].Sunday+"' style='color:#FF0000;background: "+Sunday+";' class='btn' value='"+data[i].Sunday+"' /></br><input name='day"+data[i].Sunday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Monday+"') name='day"+data[0].month+data[i].Monday+"' class='btn' style='color:#000000;background: "+Monday+";' value='"+data[i].Monday+"' /></br><input name='day"+data[i].Monday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Tuesday+"') name='day"+data[0].month+data[i].Tuesday+"' class='btn' style='color:#000000;background: "+Tuesday+";' value='"+data[i].Tuesday+"' /></br><input name='day"+data[i].Tuesday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Wednesday+"') name='day"+data[0].month+data[i].Wednesday+"' class='btn' style='color:#000000;background: "+Wednesday+";' value='"+data[i].Wednesday+"' /></br><input name='day"+data[i].Wednesday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Thursday+"') name='day"+data[0].month+data[i].Thursday+"' class='btn' style='color:#000000;background: "+Thursday+";' value='"+data[i].Thursday+"' /></br><input name='day"+data[i].Thursday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Friday+"') name='day"+data[0].month+data[i].Friday+"' class='btn' style='color:#000000;background: "+Friday+";' value='"+data[i].Friday+"' /></br><input name='day"+data[i].Friday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "<td><input type='button' onClick=planCXT('"+data[0].month+data[i].Saturday+"') name='day"+data[0].month+data[i].Saturday+"' style='color:#0100FF;background: "+Saturday+";' class='btn' value='"+data[i].Saturday+"' /></br><input name='day"+data[i].Saturday+"' style='border:none; background: transparent; text-align:center;width:100%;' type='text' value='' readonly></td>";
	        		html += "</tr>";
	        		html += "</tbody>";
	        		
	        	}
	        	
	        	html += "</table>"
	        	$("#calendarId").append(html);
	           console.log(data);
	           if($("input[value=undefined]")){
	        	   var dayName = $("input[value *=undefined]").attr('name');
	        	   $("input[name="+dayName+"]").remove();
	        	   $("input[value=undefined]").remove();
	        	   
	           }	    	   
	           
	        },
	        error : function( error ) {                    
	           alert( "error" );
	           console.log(error);
	        }
	     }); 
	   
	});
	
		
	 });
	 
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

//form 생성
function createForm1(formname, formaction, ta) {

   var form = document.createElement("form");
   form.target = ta;
   form.name = formname;
   form.action = formaction;

   document.body.appendChild(form);

}

<<<<<<< HEAD
function menu(ivalue) {
	
    createinput("hidden", "caCode", ivalue);

    var caCode = document.getElementsByName("caCode")[0];
=======
//메뉴선택
function menu(ivalue) {
	
	 createinput("hidden", "caCode", ivalue);

	 var caCode = document.getElementsByName("caCode")[0];
>>>>>>> 91704e4cc50e27071fdd1576f2b25ffbcc97726a

    createForm("menuform", "tcmenu", "post");

    var form = document.getElementsByName("menuform")[0];
    form.appendChild(caCode);
    
    form.submit();

}

function planCXT(value){

	  createinput("hidden", "boardCode", value);

      var boardCode = document.getElementsByName("boardCode")[0];

      createForm1("learningPlanCTXPageform", "learningPlanCTXPage", "POP");

      var form = document.getElementsByName("learningPlanCTXPageform")[0];
      
      window.open('', 'POP',
            "width=570, height=350, resizable = no, scrollbars = no");
      form.appendChild(boardCode);

      form.submit();
      
      $("input[name = boardCode]").remove();
	
}

	 
</script>

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
							class="ti-user"></i>
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
${select }
<div id="calendarId"></div>
</body>
</html>