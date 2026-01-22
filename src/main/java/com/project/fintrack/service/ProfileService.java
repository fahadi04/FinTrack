package com.project.fintrack.service;

import com.project.fintrack.dto.ProfileDTO;
import com.project.fintrack.modal.ProfileModal;
import com.project.fintrack.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final EmailService emailService;


    public ProfileDTO registerProfile(ProfileDTO profileDTO) {
        ProfileModal newProfile = toModal(profileDTO);
        newProfile.setActivationToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);

        //send activation mail
        String activationLink = "http://localhost:8080/api/v1.0/activate?token=" + newProfile.getActivationToken();
        String subject = "Activate your FinTrack account";
        String body = "Click on the following link to activate your account:" + activationLink;
        emailService.sendEmail(newProfile.getEmail(), subject, body);

        return toDTO(newProfile);
    }


    //Convert DTO to modal
    public ProfileModal toModal(ProfileDTO profileDTO) {
        return ProfileModal.builder().id(profileDTO.getId()).email(profileDTO.getEmail()).fullName(profileDTO.getFullName()).password(profileDTO.getPassword()).profileImageUrl(profileDTO.getProfileImageUrl()).createdAt(profileDTO.getCreatedAt()).updatedAt(profileDTO.getUpdatedAt()).build();
    }


    //    Convert modal to DTO
    public ProfileDTO toDTO(ProfileModal profileModal) {
        return ProfileDTO.builder().id(profileModal.getId()).email(profileModal.getEmail()).fullName(profileModal.getFullName()).profileImageUrl(profileModal.getProfileImageUrl()).createdAt(profileModal.getCreatedAt()).updatedAt(profileModal.getUpdatedAt()).build();
    }

    public boolean activateProfile(String activationToken) {
        return profileRepository.findByActivationToken(activationToken).map(profile -> {
            profile.setIsActive(true);
            profileRepository.save(profile);
            return true;
        }).orElse(false);
    }

}
