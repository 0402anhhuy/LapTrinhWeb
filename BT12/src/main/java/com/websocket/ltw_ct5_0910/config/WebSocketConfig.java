package com.websocket.ltw_ct5_0910.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker// Kích hoạt tính năng message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // "/topic" là tiền tố cho các điểm đến mà server sẽ gửi tin nhắn tới client.
        // Ví dụ: server sẽ gửi tin nhắn tới "/topic/public".
        config.enableSimpleBroker("/topic");

        // "/app" là tiền tố cho các điểm đến mà client sẽ gửi tin nhắn tới server.
        // Ví dụ: client gửi tin nhắn tới "/app/chat.sendMessage".
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/ws" là endpoint mà client sẽ kết nối tới WebSocket server.
        // withSockJS() cung cấp phương án dự phòng nếu trình duyệt không hỗ trợ WebSocket.
        registry.addEndpoint("/ws").withSockJS();
    }
}
