package com.alimmit.ionic.chatclientserver.repository;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;

public interface ChatMessageWebSocketSessionRepository {

    void add(WebSocketSession webSocketSession);

    void remove(WebSocketSession webSocketSession);

    int count();

    Collection<WebSocketSession> all();
}
