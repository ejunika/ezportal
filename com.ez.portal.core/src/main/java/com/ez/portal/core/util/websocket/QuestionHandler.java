package com.ez.portal.core.util.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class QuestionHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		for (WebSocketSession ssn : sessions) {
			try {
				ssn.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
