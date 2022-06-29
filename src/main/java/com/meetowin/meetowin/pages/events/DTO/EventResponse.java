package com.meetowin.meetowin.pages.events.DTO;

import java.util.Date;

public interface EventResponse {
     Long getId();
     String getTitle();
     String getDescription();
     Date getDate();
     String getPrice();
     String getLocation();
     int getNumOfPlayers();
     String getImagePath();
     String getDuration();
}
