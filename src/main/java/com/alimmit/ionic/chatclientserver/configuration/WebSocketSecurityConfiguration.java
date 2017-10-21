package com.alimmit.ionic.chatclientserver.configuration;

import com.alimmit.ionic.chatclientserver.repository.ChatMessageWebSocketSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocket
public class WebSocketSecurityConfiguration implements WebSocketConfigurer {

    public static final String PATH = "/chat";
    public static final String[] SUPPORTED_PROTOCOLS = {"protocolone", "protocoltwo"};

    @Autowired
    private ChatMessageWebSocketSessionRepository chatMessageWebSocketSessionRepository;

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(chatMessageTextWebSocketHandler(), PATH)
                .setAllowedOrigins("*")
                .setHandshakeHandler(protocolOneHandshakeHandler());
    }

    @Bean
    public ChatMessageTextWebSocketHandler chatMessageTextWebSocketHandler() {
        return new ChatMessageTextWebSocketHandler(chatMessageWebSocketSessionRepository);
    }

    @Bean
    public HandshakeHandler protocolOneHandshakeHandler() {
        final DefaultHandshakeHandler handler = new DefaultHandshakeHandler();
        handler.setSupportedProtocols(SUPPORTED_PROTOCOLS);
        return handler;
    }
}
