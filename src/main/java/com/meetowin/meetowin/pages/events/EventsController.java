package com.meetowin.meetowin.pages.cards;

import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.forgetPassword.DTO.ForgetReq;
import com.meetowin.meetowin.pages.cards.DTO.EventsResponse;
import com.meetowin.meetowin.security.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("home")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    Logger logger = LoggerFactory.getLogger(EventsController.class);

    @GetMapping
    public List<EventsResponse> getAllEvents(){
        logger.trace("Event controller requested getAllEvents()");
        return  eventsService.getAll();
    }

    @GetMapping(path = "/test")
    public List<Users> getRolledUsers(@RequestBody ForgetReq forgetReq) throws BadRequestException {
        logger.trace("this is the id =  "+ forgetReq.getId());
        return  eventsService.getUsers(forgetReq.getId());
    }
}
