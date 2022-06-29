package com.meetowin.meetowin.pages.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetowin.meetowin.model.Chat;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.profile.DTO.ProfileResponse;
import com.meetowin.meetowin.repository.EventsRepository;
import com.meetowin.meetowin.repository.SubscribedUsersRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventsRepository eventsRepository;

    private Long id,userLoggedInId;

    Logger logger = LoggerFactory.getLogger(ProfileService.class);

    public Map<String,Object> getUserService(Long id, Long userLoggedInId){
        try{
            logger.trace("getUserService Called , trying to find the user ...");
            ObjectMapper oMapper = new ObjectMapper();
            Map<String,Object> map = oMapper.convertValue(userRepository.findUsersById(id),Map.class);
            logger.trace("user found successfully");
            this.id=id;
            this.userLoggedInId = userLoggedInId;
            if (isMyProfile()){
                logger.trace("This user opened its profile");
                map.put("isMyProfile",true);
            }
            else{
                logger.trace("This user opened user's profile");
                map.put("isMyProfile",false);
            }
            logger.trace("The response is : "+ map);
            return  map;
        }catch (Exception ex){
            logger.trace("ops this process failed");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }

    }

    public Map<String,Object> getMyProfileService(Long id){
        logger.trace("getMyProfileService called ");
        logger.trace("trying to get the data for the user ");
        try{
            ObjectMapper oMapper = new ObjectMapper();
            Map<String,Object> map = oMapper.convertValue(userRepository.findUsersById(id),Map.class);
            map.put("isMyProfile",true);
            logger.trace("the server got the date");
            logger.trace("the response is : "+map);
            return map;

        }catch (Exception ex){
            logger.trace("ops this process failed");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }

    }

    public  Long getEventCreatedService(Long userId){
        logger.trace("getEventCreatedService called");
        logger.trace("trying to get number of created Events ...");
        try {
            return eventsRepository.countByUser_Id(userId);
        }catch (Exception ex){
            logger.trace("ops this process failed");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
    }

    public boolean isMyProfile(){
        return id == userLoggedInId;
    }

//    public void openChatService(Long userId, Long currentUser){
//        Chat chat = new Chat(currentUser,userId,"");
//
//
//    }

}
