package com.meetowin.meetowin.pages.profile;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.security.exception.BadRequestException;
import com.meetowin.meetowin.security.security.CurrentUser;
import com.meetowin.meetowin.security.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ProfileController")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping(path = "profile")
    public Map<String,Object> getUserController(@RequestParam(name = "id") String id,@CurrentUser UserPrincipal userPrincipal) {
        Long userId = EncryptIds.decryptId(id);
        logger.trace(String.format("getUserController requested, passed User id is : %s", userId));
        if (id != null){
            if (isLoggedIn()){
                logger.trace("This User is Logged In ");
                return profileService.getUserService(userId,userPrincipal.getId());
            }
            else{
                logger.trace("This User not Logged In ");
                return profileService.getUserService(userId,null);
            }
        } else{
            logger.trace("Ops Missing the ID");
            throw new BadRequestException("Missing the ID");
        }
    }

    @GetMapping(path = "my-profile")
    public Map<String,Object> getMyProfileController(@CurrentUser UserPrincipal userPrincipal){
        logger.trace(String.format("getMyProfileController Called , this user with id : %s trying to open it's profile ",userPrincipal.getId()));
        return profileService.getMyProfileService(userPrincipal.getId());
    }

    @GetMapping("event-created")
    public Map<String,Long> getEventCreatedController(@CurrentUser UserPrincipal userPrincipal,@RequestParam(name = "id") String userId){
        logger.trace("getEventCreatedController called ");
        Long eventCreated = 0L;
        if (userId != null && !userId.isEmpty() && !userId.equals("undefined")){
            logger.trace("the user id is : "+EncryptIds.decryptId(userId));
            eventCreated = profileService.getEventCreatedService(EncryptIds.decryptId(userId));
        }else {
            logger.trace("the user id is : "+userPrincipal.getId());
            eventCreated = profileService.getEventCreatedService(userPrincipal.getId());
        }
        Map<String,Long> map = new HashMap<>();
        map.put("numberOfEvents",eventCreated);
        logger.trace("the response is  : "+ map);
        return map;
    }

//    @GetMapping("/ProfileController/chat")
//    public void openChatController(@RequestParam("id") Long id ,@CurrentUser UserPrincipal userPrincipal){
//
//
//    }

    public boolean isLoggedIn(){
        logger.trace("isLoggedIn function called");
        return ! SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser");
    }

}
