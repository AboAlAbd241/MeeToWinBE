package com.meetowin.meetowin.pages.chat.DTO;

import java.util.Date;

public interface Messages {
    Long getSender_id();
    Long getReceiver_id();
    String getMessage();
    Date getSent_date();

}
