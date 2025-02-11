package com.electronicpayment.televentas.features.userprofile.application;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.electronicpayment.televentas.features.userprofile.domain.dto.UserProfileDto;
import com.electronicpayment.televentas.features.userprofile.domain.services.IUserProfileService;
import com.electronicpayment.televentas.shared.entities.DocumentType;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserProfileService implements IUserProfileService {

    private final IUserRepository userRepository;

    @Override
    public UserProfileDto getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());
        
        User user = this.userRepository.findById(userId).orElse(null);

        DocumentType documentType = user.getDocumentType();

        return new UserProfileDto(
                user.getDocument(),
                user.isPasswordChanged(),
                user.getName(),
                user.getPaternalLastName(),
                user.getMaternalLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getDepartment(),
                documentType.getName());
    }

}
