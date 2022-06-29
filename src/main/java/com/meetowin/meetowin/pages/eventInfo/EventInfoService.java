package com.meetowin.meetowin.pages.eventInfo;

import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.SubscribedUsers;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.eventInfo.DTO.SubscribedUserInfoResponse;
import com.meetowin.meetowin.pages.eventInfo.DTO.SubscribedUserResponse;
import com.meetowin.meetowin.pages.eventInfo.repository.EnrollUserInsertRepository;
import com.meetowin.meetowin.repository.EventsRepository;
import com.meetowin.meetowin.repository.SubscribedUsersRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventInfoService {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private SubscribedUsersRepository subscribedUsersRepository;
    @Autowired
    private EnrollUserInsertRepository enrollUserInsertRepository;

    Logger logger = LoggerFactory.getLogger(EventInfoService.class);

    public Map<String, Object> getSubscribedUsers(Long id) {
        logger.trace("getSubscribedUsers called ");
        logger.trace("trying get the users ... ");
        try {
            List<SubscribedUserInfoResponse> subscribedUsers = subscribedUsersRepository.findDistinctByEvent_Id(id);
            Map<String,Object> map = new HashMap<>();
            List<SubscribedUserResponse> subscribedUserResponses = new ArrayList<>();
            for (SubscribedUserInfoResponse user : subscribedUsers){
                subscribedUserResponses.add(new SubscribedUserResponse(user.getUser().getId(),user.getUser().getImageUrl(),user.getUser().getName()));
            }
            map.put("users",subscribedUserResponses);
            map.put("isSubscribed",false);
            logger.trace("the data got successfully");
            return map ;
        }catch (Exception ex){
            logger.warn("ops this process failed ");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }

    }

    public Map<String, Object> getSubscribedUsersAuthService(Long userId, Long eventId) {
        logger.trace("getSubscribedUsersAuth called ");
        logger.trace("trying get the users ... ");
        try {
            List<SubscribedUserInfoResponse> subscribedUsers = subscribedUsersRepository.findDistinctByEvent_Id(eventId);
            boolean isSubscribed = subscribedUsersRepository.existsByUser_IdAndEvent_Id(userId,eventId);
            Map<String,Object> map = new HashMap<>();
            List<SubscribedUserResponse> subscribedUserResponses = new ArrayList<>();
            for (SubscribedUserInfoResponse user : subscribedUsers){
                subscribedUserResponses.add(new SubscribedUserResponse(user.getUser().getId(),user.getUser().getImageUrl(),user.getUser().getName()));
            }
            map.put("users",subscribedUserResponses);
            map.put("isSubscribed",isSubscribed);
            logger.trace("the data got successfully");
            return map ;
        }catch (Exception ex){
            logger.warn("ops this process failed ");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
    }

    public Map<String,String> enrollUserService(Long userId, Long eventId){
        logger.trace("getSubscribedUsersAuth called ");
        Map<String,String> map = new HashMap<>();
        try {
            logger.trace(String.format("check if  user id = %s is subscribed or not with  event id = %s",userId,eventId));
            boolean isSubscribed = subscribedUsersRepository.existsByUser_IdAndEvent_Id(userId,eventId);
            if (!isSubscribed){
                logger.trace(String.format("this user id = %s not subscribed with event id = %s",userId,eventId));
                logger.trace(String.format("trying to save this user id = %s to this event id = %s",userId,eventId));
                SubscribedUsers subscribedUsers = new SubscribedUsers(new Users(userId), new Events(eventId));
                subscribedUsersRepository.save(subscribedUsers);
                logger.trace(String.format("this user id = %s enrolled with this event id = %s",userId,eventId));
                map.put("status","success");
                logger.trace("response is for user id = "+userId +" : "+map);
                return map ;
            }else {
                map.put("status","already subscribed");
                logger.trace(String.format("this user id=%s already subscribed to event id = %s "),userId,eventId);
                logger.trace("response is for user id = "+userId +" : "+map);
                return map ;
            }
        }catch (Exception ex){
            logger.warn("ops this process failed ");
            logger.warn(ex.getMessage());
            map.put("status","abnormal error");
            return map ;
//            throw new BadRequestException(ex.getMessage());
        }


    }

    public Map<String,String> unEnrollUserService(Long userId, Long eventId){
        logger.trace("unEnrollUserService called ");
        Map<String,String> map = new HashMap<>();
        try {
            logger.trace(String.format("check if the user id = %s is subscribed or not with  event id = %s",userId,eventId));
            boolean isSubscribed = subscribedUsersRepository.existsByUser_IdAndEvent_Id(userId,eventId);
            if (isSubscribed){
                logger.trace(String.format("this user id = %s subscribed with event id = %s",userId,eventId));
                logger.trace(String.format("trying to remove this user id = %s from this event id = %s",userId,eventId));
                SubscribedUsers subscribedUsers = subscribedUsersRepository.findByUser_IdAndEvent_Id(userId,eventId);
                subscribedUsersRepository.delete(subscribedUsers);
                logger.trace(String.format("this user id = %s UnEnrolled with this event id = %s",userId,eventId));
                map.put("status","success");
                return map ;
            }else {
                map.put("status","not subscribed");
                logger.trace(String.format("this user id=%s not subscribed to event id = %s "),userId,eventId);
                logger.trace("response is for user id = "+userId +" : "+map);
                return map ;
            }
        }catch (Exception ex){
            logger.warn("ops this process failed ");
            logger.warn(ex.getMessage());
            map.put("status","abnormal error");
            return map ;
        }


    }

    public Map<String,Integer> getAvailableUsersService(Long eventId){
        logger.trace("getAvailableUsersService called ");
        Map<String,Integer> map = new HashMap<>();
        try {
            logger.trace(String.format("trying to get subscribed users in event id = %s",eventId));
            int subscribedUsers = subscribedUsersRepository.countByEvent_Id(eventId);
            logger.trace(String.format("trying to get Max users in event id = %s",eventId));
            int allowedUsers = eventsRepository.findById(eventId).get().getNumOfPlayers();
            int available = allowedUsers - subscribedUsers;
            logger.trace(String.format("available users in  event id = %s is available User = %s",eventId,available));
            map.put("available",available);
            return map;
        }catch (Exception ex){
            logger.warn("ops this process failed ");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }

    }

}
