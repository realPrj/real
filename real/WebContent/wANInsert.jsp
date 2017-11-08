<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="controller.WANInsert" %>
<%
WANInsert wan = new WANInsert();	// 오답노트 등록

System.out.print(request.getParameter("rfCode"));

System.out.print(request.getParameter("roomcode"));
System.out.print(request.getParameter("unit"));
System.out.print(request.getParameter("type"));
System.out.print(request.getParameter("question"));

String rfCode = request.getParameter("rfCode");
String roomcode = request.getParameter("roomcode");
String unit = request.getParameter("unit");
String type = request.getParameter("type");
String question = request.getParameter("question");

wan.wanInsert(rfCode, roomcode, unit, type, question);

%>

</body>
</html>