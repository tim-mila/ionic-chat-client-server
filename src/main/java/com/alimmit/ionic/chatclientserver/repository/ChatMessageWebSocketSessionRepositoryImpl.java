package com.alimmit.ionic.chatclientserver.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatMessageWebSocketSessionRepositoryImpl implements ChatMessageWebSocketSessionRepository {

    private static final Log LOG = LogFactory.getLog(ChatMessageWebSocketSessionRepositoryImpl.class);

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void add(final WebSocketSession webSocketSession) {
        LOG.info("addWebSocketSession::" + webSocketSession.getId());
        sessions.put(webSocketSession.getId(), webSocketSession);
    }

    @Override
    public void remove(final WebSocketSession webSocketSession) {
        LOG.info("removeWebSocketSession::" + webSocketSession.getId());
        sessions.remove(webSocketSession.getId());
    }

    @Override
    public int count() {
        return sessions.size();
    }

    @Override
    public Collection<WebSocketSession> all() {
        return sessions.values();
    }
}
