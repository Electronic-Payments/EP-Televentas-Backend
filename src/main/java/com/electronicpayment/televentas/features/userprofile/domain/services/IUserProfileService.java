package com.electronicpayment.televentas.features.userprofile.domain.services;

import java.util.UUID;

import com.electronicpayment.televentas.features.userprofile.domain.dto.UserProfileDto;

public interface IUserProfileService {
    UserProfileDto profile(UUID userId);
}
