package com.meetowin.meetowin.pages.events;

import com.meetowin.meetowin.common.EncryptIds;
import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.events.DTO.CreateEventReq;
import com.meetowin.meetowin.pages.events.DTO.EventResponse;
import com.meetowin.meetowin.pages.events.DTO.EventsResClass;
import com.meetowin.meetowin.repository.EventsRepository;
import com.meetowin.meetowin.repository.UserRepository;
import com.meetowin.meetowin.security.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private UserRepository userRepository;

    private EventsResClass eventsResponse;

    Logger logger = LoggerFactory.getLogger(EventsService.class);

    public List<EventsResClass> getAllService() {
        logger.trace("getAllService called ");
        logger.trace("fetching all events ...");
        List<EventsResClass> listEvent = new ArrayList<>();
        try {
            for(Events event : eventsRepository.findAll()){
                eventsResponse = new EventsResClass();
                eventsResponse.setTitle(event.getTitle());
                eventsResponse.setDate(event.getDate());
                eventsResponse.setDescription(event.getDescription());
                eventsResponse.setDuration(event.getDuration());
                eventsResponse.setLocation(event.getLocation());
                eventsResponse.setImagePath(event.getImagePath());
                eventsResponse.setPrice(event.getPrice());
                eventsResponse.setNumOfPlayers(event.getNumOfPlayers());
                eventsResponse.setId(EncryptIds.encryptId(event.getId()));
                listEvent.add(eventsResponse);
            }
            return listEvent;
        }catch (Exception ex){
            logger.trace("ops this process failed");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }

    }

    public List<EventResponse> getEventService(Long id){
        logger.trace("getEventService called ");
        logger.trace("trying get info ...");
        try {
            return eventsRepository.findEventsById(id);
        }catch (Exception ex){
            logger.trace("ops this process failed");
            logger.warn(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
    }

    public String createEventService(CreateEventReq createEventReq, Long id){
        logger.trace("createEventService called ");
        logger.trace("trying create Event ... ");
        try {
            Users users=new Users(id);
            Events events = new Events(createEventReq.getId(),createEventReq.getTitle(),
                    createEventReq.getDescription(), createEventReq.getDate(),createEventReq.getPrice(),
                    users,createEventReq.getLocation(),createEventReq.getNumOfPlayers(),
                    createEventReq.getImagePath(),createEventReq.getDuration());
            eventsRepository.save(events);
            logger.trace("Event has been created ");
            return "success";
        }catch (Exception x){
            logger.warn("ops something went wrong ");
            logger.warn(x.getMessage());
            return "fail";
        }
    }
}
