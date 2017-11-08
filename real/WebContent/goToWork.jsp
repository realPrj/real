<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
</script>
<body>
<%@ page import="controller.GoToWork" %>

<%
GoToWork gtw = new GoToWork();	// 출근
System.out.print(request.getParameter("rfCode"));
System.out.print(request.getParameter("commute"));

String rfCode = request.getParameter("rfCode");
String commuteString = request.getParameter("commute");

int commute = Integer.parseInt(commuteString);

gtw.goToWork(rfCode,commute);

%>
</body>
</html>