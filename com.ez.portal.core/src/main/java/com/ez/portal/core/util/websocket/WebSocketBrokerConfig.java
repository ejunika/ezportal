//package com.ez.portal.core.util.websocket;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/questions").withSockJS();
//	}
//	
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.setApplicationDestinationPrefixes("/app")
//				.enableSimpleBroker("/topic", "/queue");
//	}
//
//}
