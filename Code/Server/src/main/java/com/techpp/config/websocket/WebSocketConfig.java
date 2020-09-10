package com.techpp.config.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


//Add this annotation to an @Configuration class to configure processing WebSocket requests
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	  public void configureMessageBroker(MessageBrokerRegistry config) {
	    config.enableSimpleBroker("/driver");
	    config.setApplicationDestinationPrefixes("/websocket");
	  }
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/startwebsocket").withSockJS();
//		registry.addEndpoint("/ws");/
//		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}

}