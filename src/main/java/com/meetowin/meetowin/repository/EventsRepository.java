package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.pages.eventInfo.DTO.SubscribedUserInfoResponse;
import com.meetowin.meetowin.pages.events.DTO.CreateEventReq;
import com.meetowin.meetowin.pages.events.DTO.EventResponse;
import com.meetowin.meetowin.security.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Events,Long> {

    List<SubscribedUserInfoResponse> findAllById(Long id) throws ResourceNotFoundException ;

    List<EventResponse> findEventsById(Long id) throws ResourceNotFoundException;

    Long countByUser_Id(Long id);




}
