package com.meetowin.meetowin.pages.events.DTO;

import com.meetowin.meetowin.model.Users;

import java.util.Date;

public interface EventRes {
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
