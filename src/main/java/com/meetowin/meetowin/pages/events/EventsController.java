package com.meetowin.meetowin.pages.events;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.events.DTO.CreateEventReq;
import com.meetowin.meetowin.pages.events.DTO.EventResponse;
import com.meetowin.meetowin.pages.events.DTO.EventsResClass;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/EventsController")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    Logger logger = LoggerFactory.getLogger(EventsController.class);

    @GetMapping(path = "/events")
    public List<EventsResClass> getAllEventsController(){
        logger.trace("getAllEventsController called ");
        return  eventsService.getAllService();
    }

    @GetMapping(path = "/events-details")
    public List<EventResponse> getEventByIdController(@RequestParam(name = "id") String id){
        Long EventId = EncryptIds.decryptId(id);
        logger.trace("getEventByIdController called ");
        logger.trace("check the sent Event Id : "+EventId);
        if (id != null)
            return eventsService.getEventService(EventId);
        else {
            logger.trace("Missing the ID ");
            throw new BadRequestException("Missing the ID");
        }
    }

    @PostMapping(path = "create-event")
    public Map createEventController(@RequestBody CreateEventReq createEventReq, @CurrentUser UserPrincipal userPrincipal){
       logger.trace("createEventController called ");
       Map<String,String> map=new HashMap<>();
       String status = eventsService.createEventService(createEventReq, userPrincipal.getId());
       map.put("status",status);
       logger.trace("response is :  "+ map);
        return map;
    }

}
