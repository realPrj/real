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
<title>공조 || 자료실</title>
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
      
      return input;
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

   function viewData(referCode, referTitle, referDate) {
      createinput("hidden", "boardTitle", referTitle);
      createinput("hidden", "boardDate", referDate);
      createinput("hidden", "roomCode", referCode);
      
      createForm("learningDataCXTform", "learningDataCXT", "post");

      var form = document.getElementsByName("learningDataCXTform")[0];
      
      var boardTitle = document.getElementsByName("boardTitle")[0];
      var boardDate = document.getElementsByName("boardDate")[0];
      var roomCode = document.getElementsByName("roomCode")[0];
      
   
      form.appendChild(boardTitle);
      form.appendChild(boardDate);
      form.appendChild(roomCode);

      form.submit();
      
   }
   
</script>
</head>
<body onLoad="${message}">
   <h1>공조</h1>
   <div>
      <input type="button" value="자료실" onClick="menu('10')" /> <input
         type="button" value="자료실 글쓰러가기"
         onClick="eventClick('dataform','DataInsert','post')" />
   </div>
   <div>${content}</div>
   ${datalist }

</body>
</html>