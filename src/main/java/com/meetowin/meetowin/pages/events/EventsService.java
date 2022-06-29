package com.meetowin.meetowin.pages.cards;

import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.cards.DTO.EventsResponse;
import com.meetowin.meetowin.repository.EventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    private EventsResponse eventsResponse;

    Logger logger = LoggerFactory.getLogger(EventsService.class);

    public List<EventsResponse> getAll(){
        logger.trace("fetching all events");
        List<EventsResponse> listEvent = new ArrayList<>();
        for(Events event : eventsRepository.findAll()){
            eventsResponse = new EventsResponse();
            eventsResponse.setTitle(event.getTitle());
            eventsResponse.setDate(event.getDate());
            eventsResponse.setDescription(event.getDescription());
            eventsResponse.setDuration(event.getDuration());
            eventsResponse.setLocation(event.getLocation());
            eventsResponse.setImagePath(event.getImagePath());
            eventsResponse.setPrice(event.getPrice());
            eventsResponse.setNumOfPlayers(event.getNumOfPlayers());
            listEvent.add(eventsResponse);
        }
        return listEvent;
    }

    public List<Users> getUsers(Long id) {
        return eventsRepository.findById(id).get().getRolledUsers();
    }
}
