package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.Users;
import com.meetowin.meetowin.pages.eventInfo.DTO.UserInfo;
import com.meetowin.meetowin.pages.profile.DTO.ProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    ProfileResponse findUsersById(Long id);

}
