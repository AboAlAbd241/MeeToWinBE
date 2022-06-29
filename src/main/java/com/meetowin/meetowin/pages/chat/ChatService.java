package com.meetowin.meetowin.pages.chat;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.model.Chat;
import com.meetowin.meetowin.pages.chat.DTO.*;
import com.meetowin.meetowin.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;


    public List<MessagesResponse> sendMessage(ChatRequest chatRequest) {
        Chat chat = new Chat(EncryptIds.decryptId(chatRequest.getSender()), EncryptIds.decryptId(chatRequest.getReceiver()), chatRequest.getMessage());
        chatRepository.save(chat);
        return encryptIds(chatRepository.findAllBySender_IdAndReceiver_Id(EncryptIds.decryptId(chatRequest.getSender()), EncryptIds.decryptId(chatRequest.getReceiver())));
    }

    public List<ListUserMessagesResponse> getUsersService(Long userId) {
        return encryptIdsForGetUsersService(chatRepository.findAllBy(userId));
    }

    public List<MessagesResponse> getAllMessages(Long currentUser, Long userId) {
        return encryptIds(chatRepository.findAllBySender_IdAndReceiver_Id(currentUser, userId));
    }

    public List<MessagesResponse> encryptIds(List<Messages> list) {
        List<MessagesResponse> messagesResponseList = new ArrayList<>();
        for (Messages messages : list) {
            messagesResponseList.add(new MessagesResponse(messages));
        }
        return messagesResponseList;
    }

    public List<ListUserMessagesResponse> encryptIdsForGetUsersService(List<UsersMessages> list) {
        List<ListUserMessagesResponse> listUserMessagesResponses = new ArrayList<>();
        for (UsersMessages usersMessages : list) {
            listUserMessagesResponses.add(new ListUserMessagesResponse(usersMessages));
        }
        return listUserMessagesResponses;
    }
}
