package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.Events;
import com.meetowin.meetowin.model.SubscribedUsers;
import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.eventInfo.DTO.SubscribedUserInfoResponse;
import com.meetowin.meetowin.pages.eventInfo.DTO.UserInfo;
import com.meetowin.meetowin.security.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribedUsersRepository extends JpaRepository<SubscribedUsers,Long> {
        List<SubscribedUserInfoResponse> findDistinctByEvent_Id(Long id);
        boolean existsByUser_IdAndEvent_Id(Long userId, Long eventId);
        SubscribedUsers findByUser_IdAndEvent_Id(Long userId, Long eventId);
        int countByEvent_Id(Long eventId);

}
