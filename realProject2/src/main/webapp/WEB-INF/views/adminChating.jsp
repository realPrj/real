<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>채팅</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	//웹 소켓 객체를 저장할 변수를 선언
	var websocket;


	function test() {
		websocket = new WebSocket("ws://localhost:8080/web/chat");
		//웹 소켓 이벤트 처리

		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;
	
	}

	$(function() {
		//전송 버튼을 누를 때 이벤트 처리
		$('#sendBtn').bind('click', function() {
			//nickname 과 message에 입력된 내용을 서버에 전송
			var nick = $('#nickname').val();

			var msg = $('#message').val();
			//메시지 전송
			websocket.send(nick + ":" + msg);
			alert("");
			//메시지 입력창 초기화
			$('#message').val('');
		});
	});
	
	//WebSocket이 연결된 경우 호출되는 함수
	function onOpen(evt) {

		console.log("웹 소켓에 연결 성공");
	}

	//WebSocket이 연결 해제된 경우 호출되는 함수
	function onClose(evt) {

		console.log("웹 소켓에 연결 해제");
	}

	//서버에서 메시지가 왔을 때 호출되는 함수
	function onMessage(evt) {
		//서버가 전송한 메시지 가져오기
		var data = evt.data;
		//메시지를 출력
		$('#chatMessageArea').append(data + "<br />");
	}
</script>
<%@ page import="java.util.*, java.text.*"%>
<%@ page import="icia.project.services.learningStudentMM"%>

<%


	learningStudentMM lsmm = new learningStudentMM();
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmm");
	String today = formatter.format(new java.util.Date());
	out.println(today);
	lsmm.getmethod(today);

%>
</head>
<body onLoad="test()">
<%@ page import="icia.project.services.ChatWebSocketHandler"%>

	<table>
		<tr>
			<td>
				<h1>알림</h1> <input type="hidden" name="nickname" value="${id }"
				id="nickname" />



		
				<div id="chatArea">
					<div id="chatMessageArea"></div>
				</div>


			</td>
		</tr>
	</table>


</body>
</html>
