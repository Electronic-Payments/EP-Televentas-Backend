package com.electronicpayment.televentas.features.userprofile.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.userprofile.domain.dto.UserProfileDto;
import com.electronicpayment.televentas.features.userprofile.domain.services.IUserProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserProfileController {

    private final IUserProfileService userProfileService;

    @GetMapping("profile")
    public ResponseEntity<UserProfileDto> getProfile() {       
        return ResponseEntity.ok().body(this.userProfileService.getProfile());
    }

}
