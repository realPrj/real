<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 오답노트 코멘트 등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css); // 타이틀
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.CTX{
border-radius:4px; height:27px; padding:0 10px; background:#D6C8A1; color:#424242; font-family: 'Noto Sans KR', sans-serif; 
}
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
	width: 500px;
	height: 100px;
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
	width: 200px;
	height: 22px;
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
.title2{
font-family: 'Nanum Gothic', sans-serif; font-size:20pt; margin-left:3%
}
</style>
</head>
<script>
 
//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

/* function commentInsert(){

	var boardCode = document.getElementsByName("boardCode")[0];
	var boardContent = document.getElementsByName("boardContent")[0];
	var boardRoute = document.getElementsByName("boardRoute")[0];
	
	createForm("learningWANCommentInsertform","learningWANCommentInsert","POST");
	
	var form = document.getElementsByName("learningWANCommentInsertform")[0];
	
	form.appendChild(boardCode);
	form.appendChild(boardContent);
	form.appendChild(boardRoute);
	
	form.submit();
} */
function fileNameInput(){
	  
	 var fName = $('#file').val().split("\\");
	    $('#load').val($(fName)[2]);
	  
}
</script>
<body>

<!-- 자료실 글쓰기  -->
   <%-- <form name="fileForm" action="learningWANCommentInsert" method="post"
      enctype="multipart/form-data">
      <input type="hidden" value="${boardCode }" name="boardCode" />
     <br> 내용 :
      <textarea name="boardContent" cols=50 rows=20 maxlength=500></textarea>

      <br> <input multiple="multiple" type="file" name="file" /><input type="hidden"
         name="load" value="Notice" />
      <BUTTON type="SUBMIT">보내기</BUTTON>
   </form> --%>
    <br/><center>
   <img src="assets/img/gong_logo.png" alt="공조" width="150" height="70">
   </center>
  <br/><br/>
   <div>
					
						<div class="title2">
						코멘트 등록
						</div>
						<br/>
						<form name="fileForm" action="learningWANCommentInsert" method="post" enctype="multipart/form-data">
						<input type="hidden" value="${boardCode }" name="boardCode" />
						<div style="margin:20px;">
							<p>내용</p>
							<textarea name="boardContent"></textarea>
							</div>
							<div style="margin:20px;">
							
							<div>
							
							<table>
							<tr><td style="padding-top:5px; padding-right:10px"><p>첨부파일</p></td>
							<td style="width:400px; padding-right:10px">
							<input id="load" type="text" class="input_file" readonly/>
							
							</td>
							
								<td><input id="file" multiple="multiple" type="file" name="file" onchange="fileNameInput()"
							 class="upload"/><input type="hidden" name="load" value="Notice" />
							<label style="font-size:10pt; height:10px; margin-left:0px; border-radius:4px; background-color:#FFF2E6; padding:4px 5px; color:424242; border:1px solid #989898; border-radius:6px; cursor:pointer; font-family: 'Noto Sans KR', sans-serif; " for="file">찾아보기..</label></td>
							 </tr>
							</table>
							
							 </div>
							 
      						
      						<br/>
      						<BUTTON style="border-radius:4px; height:27px; padding:0 10px; background:#D6C8A1; color:#424242; font-family: 'Noto Sans KR', sans-serif; cursor:pointer" type="SUBMIT">등록</BUTTON><br/>
      						<br/>
      						
							</div>
							</form>
							</div>
							
</body>
</html>