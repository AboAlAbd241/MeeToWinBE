package com.meetowin.meetowin.pages.eventInfo.repository;

import com.meetowin.meetowin.model.SubscribedUsers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EnrollUserInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Long eventId , Long userId,SubscribedUsers subscribedUsers) {
        int query = entityManager.createNativeQuery("select next_val from hubernate_squence")
                .executeUpdate();

        entityManager.createNativeQuery("INSERT INTO subscribed_users (event_id, user_id , id) VALUES (?,?,?)")
                .setParameter(1,eventId)
                .setParameter(2, userId)
                .setParameter(3,query)
                .executeUpdate();
    }
}
