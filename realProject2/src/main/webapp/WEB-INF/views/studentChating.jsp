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
	
	function test(){
	websocket = new WebSocket("ws://localhost:80/web/chat");
	//웹 소켓 이벤트 처리
  	
	websocket.onopen = onOpen;
	websocket.onmessage = onMessage;
	websocket.onclose = onClose;
	
	testClick();
	
	window.Close();
	
	}
	
	function testClick(){
		
		$('#sendBtn').bind('click', function() {
			//nickname 과 message에 입력된 내용을 서버에 전송
			var nick = $('#nickname').val();	// 아이디
			
			var msg = ${message};	// 메세지
			//메시지 전송
			websocket.send(nick + ":" + msg);
			//메시지 입력창 초기화
		});
		
	}

	$(function() {

		//입장 버튼을 클릭했을 때 이벤트 처리
		

		//퇴장 버튼을 누를 때 이벤트 처리
		$('#exitBtn').bind('click', function() {
			alert("웹 소켓 해제");
			//웹 소켓 연결 해제
			websocket.close();
		});

		//전송 버튼을 누를 때 이벤트 처리
		$('#sendBtn').bind('click', function() {
			//nickname 과 message에 입력된 내용을 서버에 전송
			var nick = $('#nickname').val();
			
			var msg = $('#message').val();
			//메시지 전송
			websocket.send(nick + ":" + msg);
			//메시지 입력창 초기화
			$('#message').val('');
		});

		//message 창에서 Enter를 눌렀을 때도 메시지를 전송
		//키보드를 누를 때 이벤트 처리
		$('#message').keypress(function(event) {
			var keycode = event.keyCode ? event.keyCode : event.whice;
			if (keycode == 13) {
				//nickname 과 message에 입력된 내용을 서버에 전송
				var nick = $('#nickname').val();
				
				var msg = $('#message').val();
				//메시지 전송
				websocket.send(nick + ":" + msg);
				//메시지 입력창 초기화
				$('#message').val('');
			}
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

</head>
<body onLoad="test()">
	<table>
	<tr><td>
	<h1>알림</h1>
	<input type="hidden" name="nickname" value="${id }" id="nickname" />

	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	</td>
	</tr>
	</table>


</body>
</html>
