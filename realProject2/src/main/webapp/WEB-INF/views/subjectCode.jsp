<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 과목코드</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function startAjax(){
	   $.ajax({
	      type: "get",
	      url: "subjectCode",
	      data: { id : "taehwy",pw : "1234" },      // 전달 값
	      dataType: "json",                              // json, xml, html(text): 안쓰면 html
	      timeout : "5000",                              // 타임아웃
	      success : function( data ) {                  // 성공
	         alert(data);
	         console.log(data);
	         $("#ajax_div").append(data.mbid + ',');
	         $("#ajax_div").append(data.mbpwd);
	      },
	      error : function( error ) {                     // 실패
	         alert( "error" );
	         console.log(error);
	      }
	   });
	}
</script>
<body>

</body>
</html>