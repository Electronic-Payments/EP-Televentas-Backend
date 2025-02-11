package com.electronicpayment.televentas.features.user.profile.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.user.profile.domain.dto.UserProfileDto;
import com.electronicpayment.televentas.features.user.profile.domain.services.IUserProfileService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserProfileController {
    
    private final IUserProfileService userProfileService;

    @GetMapping("profile/{userId}")
    public ResponseEntity<UserProfileDto> profile(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(this.userProfileService.profile(userId));
    }
    
}
