package com.alimmit.ionic.chatclientserver.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Arrays;
import java.util.List;

public class ProtocolOneWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private static final Log LOG = LogFactory.getLog(ProtocolOneWebSocketHandler.class);

    public ProtocolOneWebSocketHandler() {
        super();
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        LOG.info("afterConnectionEstablished");
        LOG.info("afterConnectionEstablished::getAcceptedProtocol::" + session.getAcceptedProtocol());
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        LOG.info("handleTextMessage::" + message.getPayload());
        super.handleTextMessage(session, message);
    }

    @Override
    public List<String> getSubProtocols() {
        return Arrays.asList("protocolone", "protocoltwo");
    }
}
