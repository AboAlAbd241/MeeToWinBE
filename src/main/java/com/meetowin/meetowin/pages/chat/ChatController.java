package com.meetowin.meetowin.pages.chat;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.pages.chat.DTO.ChatRequest;
import com.meetowin.meetowin.pages.chat.DTO.Messages;
import com.meetowin.meetowin.pages.chat.DTO.MessagesResponse;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {


    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/queue/public")
    public List<MessagesResponse>  sendMessage(@Payload ChatRequest chatMessage) {
        return chatService.sendMessage(chatMessage);

    }

    @MessageMapping("/chat.addUser")
    @SendTo("/queue/public")
    public ChatRequest addUser(@Payload ChatRequest chatMessage,@CurrentUser UserPrincipal userPrincipal,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("currentUser", EncryptIds.decryptId(chatMessage.getSender()));
        return chatMessage;
    }

    @GetMapping("/users-chat")
    public Map<String,Object> getUsersController(@CurrentUser UserPrincipal userPrincipal){
        Map map=new HashMap<>();
        map.put("users",chatService.getUsersService(userPrincipal.getId()));
        map.put("myId", EncryptIds.encryptId(userPrincipal.getId()));
        return map;
    }

    @GetMapping("/getMessages")
    public List<MessagesResponse> getAllMessagesController(@RequestParam("id") String userId, @CurrentUser UserPrincipal userPrincipal){
        return chatService.getAllMessages(userPrincipal.getId(),EncryptIds.decryptId(userId));
    }


}
