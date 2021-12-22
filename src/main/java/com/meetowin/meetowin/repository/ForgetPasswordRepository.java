package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,Long>
{
    public List<ForgetPassword> findByEmail(String email);

}
