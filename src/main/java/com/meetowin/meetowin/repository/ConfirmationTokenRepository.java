package com.meetowin.meetowin.repository;

import com.meetowin.meetowin.model.VerifiedEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<VerifiedEmail, String> {
    VerifiedEmail findByConfirmationToken(String confirmationToken);
}
