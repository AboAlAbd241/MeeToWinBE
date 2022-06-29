package com.meetowin.meetowin.pages.eventInfo.DTO;

import com.meetowin.meetowin.common.EncryptIds;

public class SubscribedUserResponse {
    private String id;
    private String imageUrl;
    private String name;

    public SubscribedUserResponse() {
    }

    public SubscribedUserResponse(Long id, String imageUrl, String name) {
        this.id = EncryptIds.encryptId(id);
        this.imageUrl = imageUrl;
        this.name = name;
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
