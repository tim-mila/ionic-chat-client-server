package com.alimmit.ionic.chatclientserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker
public class WebSocketSecurityConfiguration /*extends AbstractSecurityWebSocketMessageBrokerConfigurer*/ implements WebSocketConfigurer {

    public static final String PATH = "/chat";

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(protocolOneWebSocketHandler(), PATH)
                .setAllowedOrigins("*")
                .setHandshakeHandler(protocolOneHandshakeHandler());
    }

    @Bean
    public ProtocolOneWebSocketHandler protocolOneWebSocketHandler() {
        return new ProtocolOneWebSocketHandler();
    }

    @Bean
    public HandshakeHandler protocolOneHandshakeHandler() {
        final DefaultHandshakeHandler handler = new DefaultHandshakeHandler();
        handler.setSupportedProtocols("protocolone", "protocoltwo");
        return handler;
    }


//    @Override
//    public void configureMessageBroker(final MessageBrokerRegistry registry) {
//        super.configureMessageBroker(registry);
//        registry.enableSimpleBroker(PATH);
//    }

//    @Override
//    public void registerStompEndpoints(final StompEndpointRegistry registry) {
//        registry.addEndpoint(PATH).setAllowedOrigins("*");
//    }
//
//    @Override
//    protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
//        messages
//                .simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.UNSUBSCRIBE, SimpMessageType.DISCONNECT).permitAll()
//                .simpDestMatchers("/chat/**").permitAll()
//                .anyMessage().permitAll();
//    }
}
