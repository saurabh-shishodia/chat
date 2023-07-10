package com.shishodia.controller;

import com.shishodia.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage/{groupName}")
    @SendTo("/topic/public/{groupName}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage
            , @DestinationVariable String groupName) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{groupName}")
    @SendTo("/topic/public/{groupName}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor
                                ,@DestinationVariable String groupName) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
