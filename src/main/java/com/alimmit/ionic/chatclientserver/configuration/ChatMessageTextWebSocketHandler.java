package com.alimmit.ionic.chatclientserver.configuration;

import com.alimmit.ionic.chatclientserver.repository.ChatMessageWebSocketSessionRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatMessageTextWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private static final Log LOG = LogFactory.getLog(ChatMessageTextWebSocketHandler.class);

    private final ChatMessageWebSocketSessionRepository chatMessageWebSocketSessionRepository;

    public ChatMessageTextWebSocketHandler(final ChatMessageWebSocketSessionRepository chatMessageWebSocketSessionRepository) {
        super();
        this.chatMessageWebSocketSessionRepository = chatMessageWebSocketSessionRepository;
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        LOG.info("afterConnectionEstablished");
        LOG.info("afterConnectionEstablished::getAcceptedProtocol::" + session.getAcceptedProtocol());
        chatMessageWebSocketSessionRepository.add(session);
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        chatMessageWebSocketSessionRepository.remove(session);
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        LOG.info("handleTextMessage::" + message.getPayload());
        LOG.info("handleTextMessage::sessions::" + chatMessageWebSocketSessionRepository.count());
        chatMessageWebSocketSessionRepository.all().forEach(webSocketSession -> {
            try {
                LOG.debug("check send to websocket " + webSocketSession.getId());
                if (!session.getId().equalsIgnoreCase(webSocketSession.getId())) {
                    LOG.debug("send to websocket " + webSocketSession.getId());
                    webSocketSession.sendMessage(message);
                }
            }
            catch(IOException e) {
                LOG.error(e.getMessage());
            }
        });
    }

    @Override
    public List<String> getSubProtocols() {
        return Arrays.asList("protocolone", "protocoltwo");
    }
}
