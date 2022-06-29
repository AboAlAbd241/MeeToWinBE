package com.meetowin.meetowin.pages.eventInfo;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.SubscribedUsers;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.eventInfo.DTO.SubscribedUserInfoResponse;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/EventInfoController")
public class EventInfoController {

    @Autowired
    private EventInfoService eventInfoService;

    Logger logger = LoggerFactory.getLogger(EventInfoController.class);

    @GetMapping(path = "/not-auth-subscribed-users")
    public Map<String, Object> getSubscribedUsersController(@RequestParam(name = "id") String id){
        Long eventId = EncryptIds.decryptId(id);
        logger.trace("getSubscribedUsersController called ");
        logger.trace("checking event id : "+ eventId);
        if (eventId != null) {
            return eventInfoService.getSubscribedUsers(eventId);
        }
        else{
            logger.trace("Missing Event ID");
            throw new BadRequestException("Missing Event ID");
        }
    }

    @GetMapping(path = "subscribed-users")
    public Map<String, Object> getSubscribedUsersController(@RequestParam(name = "id") String id,@CurrentUser UserPrincipal userPrincipal){
        Long eventId = EncryptIds.decryptId(id);
        logger.trace("getSubscribedUsersController called");
        logger.trace("checking Event id : "+ eventId);
        if (eventId != null) {
            return eventInfoService.getSubscribedUsersAuthService(userPrincipal.getId(),eventId);
        }
        else {
            logger.trace("Missing Event ID");
            throw new BadRequestException("Missing Event ID");
        }
    }

    @GetMapping(path = "/enroll-user")
    public Map<String,String> enrollUserController(@CurrentUser UserPrincipal userPrincipal ,@RequestParam(name = "id") String id){
        Long eventId = EncryptIds.decryptId(id);
        logger.trace("enrollUserController called ");
        logger.trace("checking event id : "+ eventId);
        if (eventId != null) {
            return eventInfoService.enrollUserService(userPrincipal.getId(), eventId);
        }
        else {
            logger.trace("Missing Event ID");
            throw new BadRequestException("Missing Event ID");
        }
    }

    @GetMapping(path = "/un-enroll-user")
    public Map<String,String> unEnrollUserController(@CurrentUser UserPrincipal userPrincipal ,@RequestParam(name = "id") String id){
        Long eventId = EncryptIds.decryptId(id);
        logger.trace("unEnrollUserController called ");
        logger.trace("checking event id : "+ eventId);
        if (eventId != null) {
            return eventInfoService.unEnrollUserService(userPrincipal.getId(), eventId);
        }
        else {
            logger.trace("Missing Event ID");
            throw new BadRequestException("Missing Event ID");
        }
    }

    @GetMapping(path = "/available-users")
    public Map<String,Integer> getAvailableUsersController(@RequestParam(name = "id") String id){
        Long eventId = EncryptIds.decryptId(id);
        logger.trace("getAvailableUsersController called ");
        logger.trace("checking event id : "+ eventId);
        if (eventId != null) {
            return eventInfoService.getAvailableUsersService(eventId);
        }
        else {
            logger.trace("Missing Event ID");
            throw new BadRequestException("Missing Event ID");
        }
    }

}
