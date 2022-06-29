package com.meetowin.meetowin.pages.profile.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ProfileResponse {

    Long getId();
    String getName();
    String getImageUrl();
    String getEventCreated();
    String getAbout();
    Long getRating();

}
