package com.meetowin.meetowin.pages.chat.DTO;

import com.meetowin.meetowin.common.EncryptIds;

public class ListUserMessagesResponse {
    private String id;
    private String imageUrl;
    private String name;

    public ListUserMessagesResponse() {
    }

    public ListUserMessagesResponse(UsersMessages message) {
        this.id = EncryptIds.encryptId(message.getId());
        this.imageUrl = message.getImageUrl();
        this.name = message.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
