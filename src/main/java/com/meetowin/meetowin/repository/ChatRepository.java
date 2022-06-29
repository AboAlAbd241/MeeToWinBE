package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.Chat;
import com.meetowin.meetowin.pages.chat.DTO.UsersMessages;
import com.meetowin.meetowin.pages.chat.DTO.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,String> {

    @Query(value = "SELECT distinct users.id,users.name,users.image_url FROM users where\n" +
            " users.id != ?1 and ( users.id in (select receiver_id from chat where sender_id = ?1) or users.id in (select sender_id from chat where receiver_id = ?1) ); ",
            nativeQuery = true)
    List<UsersMessages> findAllBy(Long id);

    @Query(value = "select sender_id,receiver_id,message ,sent_date from chat where ( receiver_id = ?1 or sender_id = ?1) and ( receiver_id = ?2 or sender_id = ?2)  order by sent_date;",
            nativeQuery = true)
    List<Messages> findAllBySender_IdAndReceiver_Id(Long currentUser, Long userId);

//    @Query(value = "select sender_id,receiver_id,message from chat where ( receiver_id =?1 or sender_id = ?1) and ( receiver_id = ?2 or sender_id = ?2) ;",
//            nativeQuery = true)
//    List<testRes> findAllBySender_Id(Long currentUser, Long userId);


}
