package com.electronicpayment.televentas.features.user.profile.domain.services;

import java.util.UUID;

import com.electronicpayment.televentas.features.user.profile.domain.dto.UserProfileDto;

public interface IUserProfileService {
    UserProfileDto profile(UUID userId);
}
