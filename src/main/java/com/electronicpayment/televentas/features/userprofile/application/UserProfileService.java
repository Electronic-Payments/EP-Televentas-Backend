package com.electronicpayment.televentas.features.userprofile.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public UserProfileDto profile(UUID userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

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
