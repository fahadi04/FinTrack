package com.project.fintrack.repository;

import com.project.fintrack.modal.ProfileModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileModal, Long> {

    // select * from tbl_profiles where email = ?
    Optional<ProfileModal> findByEmail(String email);

    // select * from tbl_profiles where activation_token = ?
    Optional<ProfileModal> findByActivationToken(String activationToken);
}
