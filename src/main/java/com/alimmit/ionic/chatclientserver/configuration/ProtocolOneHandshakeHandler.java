package com.alimmit.ionic.chatclientserver.configuration;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.Base64;
import java.util.Map;

public class ProtocolOneHandshakeHandler implements HandshakeHandler {

    private static final Log LOG = LogFactory.getLog(ProtocolOneHandshakeHandler.class);

    @Override
    public boolean doHandshake(final ServerHttpRequest serverHttpRequest, final ServerHttpResponse serverHttpResponse, final WebSocketHandler webSocketHandler, final Map<String, Object> map) throws HandshakeFailureException {
        LOG.info("doHandshake");
        serverHttpResponse.setStatusCode(HttpStatus.SWITCHING_PROTOCOLS);
        serverHttpResponse.getHeaders().add("Upgrade", "websocket");
        serverHttpResponse.getHeaders().add("Sec-WebSocket-Protocol", "protocol1, protocol2");
        serverHttpResponse.getHeaders().add("Sec-WebSocket-Accept", hashSecKey(serverHttpRequest.getHeaders().getFirst("Sec-WebSocket-Key")));
        return true;
    }

    private String hashSecKey(final String key) {

        final String c = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        final String concat = key + c;
        final String sha1 = DigestUtils.sha1Hex(concat);
        return Base64.getEncoder().encodeToString(sha1.getBytes());
    }
}
