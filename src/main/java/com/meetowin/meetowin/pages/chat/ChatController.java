package com.example.websocketdemo.controller;

import com.example.websocketdemo.dao.Impl.HelloMessageRepositoryExtensionImpl;
import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.pojo.HelloMessage;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private HelloMessageRepositoryExtensionImpl helloMessageRepositoryExtension;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        HelloMessage helloMessage = helloMessageRepositoryExtension.getalldata();
        chatMessage.setAlertmsg("temperature crossed 100 on 11:02:00");
        chatMessage.setAlertmsg(helloMessage.getName());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
