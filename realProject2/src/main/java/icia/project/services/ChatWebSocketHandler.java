package icia.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChatWebSocketHandler implements WebSocketHandler {

	//접속한 클라이언트들의 정보를 저장할 컬렉션 객체
	 public static List<WebSocketSession> list=
			 new ArrayList<WebSocketSession>();
	 
	 //클라이언트와의 연결이 끊겼을떄 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		// 리스트에서 제거
		
        list.remove(arg0);
	}

	//클라이언트가 연결되었을때 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		//리스트에 추가
		System.out.println("연결 되었니??");
		list.add(arg0);
	}

	//클라이언트가 메시지를 보냈을때 호출되는 메소드
	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
		//전송된 메시지를 모든 클라이언트에게 전송
		 String msg=(String)arg1.getPayload();
		 for(WebSocketSession socket:list){
			 //메시지 생성
			 WebSocketMessage<String>sentMsg=
					 new TextMessage(msg);
			 //각 세션에게 메시지를 전송
			 socket.sendMessage(sentMsg);
		 }

	}

	//메시지 전송에 실패했을때 호출되는 메소드
	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		
	}

	//메시지가 긴 경우 분할해서 보낼수있는지 여부를 설정하는 메소드
	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

}
