package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users,Long> {
/*
    public  Optional<Users> findByUsername(String username);
*/
    public Optional<Users> findByEmail(String email);
//    Users findByEmailIdIgnoreCase(String emailId);
//    Users findByUsername(String username);
    Boolean existsByEmail(String email);

}
