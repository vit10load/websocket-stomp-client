package br.com.tech.websocket.app.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry config) {
    
        config.enableSimpleBroker("/topic");

        config.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {

        registry.addEndpoint("/wb-guide-websocket")
        .setAllowedOrigins("*");
        
    }

    @Bean
    public ApplicationListener<BrokerAvailabilityEvent> brokerAvailabilityEventListener() {
        return event -> System.out.println("📡 Message Broker disponível: " + event.isBrokerAvailable());
    }


}
