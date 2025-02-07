package com.electronicpayment.televentas.features.auth.login.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.auth.login.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.login.domain.services.IAuthLoginService;
import com.electronicpayment.televentas.features.auth.register.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthLoginController {
    
    private final IAuthLoginService authLoginService;

    @PostMapping("login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok().body(this.authLoginService.login(request));
    }

    @GetMapping("document-types")
    public ResponseEntity<List<DocumentTypeDto>> listDocumentTypes() {
        return ResponseEntity.ok().body(this.authLoginService.listDocumentTypes());
    }
}
