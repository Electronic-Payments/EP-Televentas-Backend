package com.electronicpayment.televentas.features.auth.register.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.auth.register.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.features.auth.register.domain.services.IAuthRegisterService;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthRegisterController {
    
    private final IAuthRegisterService authRegisterService;

    @PostMapping("register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterUserDto request) {
        return ResponseEntity.ok().body(this.authRegisterService.register(request));
    }  
    
}
