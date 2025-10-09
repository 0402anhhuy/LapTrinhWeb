package com.websocket.ltw_ct5_0910.controller;

import com.websocket.ltw_ct5_0910.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    /**
     * Xử lý tin nhắn gửi từ client.
     * Khi client gửi tin nhắn đến đích "/app/chat.sendMessage", phương thức này sẽ được gọi.
     * @MessageMapping("/chat.sendMessage")
     *
     * Gửi tin nhắn trả về cho tất cả các client đang lắng nghe trên đích "/topic/public".
     @SendTo("/topic/public")
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage; // Tin nhắn nhận được sẽ được gửi lại cho tất cả mọi người
    }
}
