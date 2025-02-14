package com.electronicpayment.televentas.features.auth.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.auth.domain.dto.ChangePasswordDto;
import com.electronicpayment.televentas.features.auth.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.features.auth.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.features.auth.domain.dto.TokenResponseDto;
import com.electronicpayment.televentas.features.auth.domain.services.IAuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok().body(this.authService.login(request));
    }

    @GetMapping("document-types")
    public ResponseEntity<List<DocumentTypeDto>> listDocumentTypes() {
        List<DocumentTypeDto> data = this.authService.listDocumentTypes();

        if (data.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(data);
    }

    @PostMapping("register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterUserDto request) {
        return ResponseEntity.ok().body(this.authService.register(request));
    }

    @PutMapping("change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordDto request) {
        this.authService.changePassword(request.getEmail(), request.getNewPassword());

        Map<String, String> response = new HashMap<>();
        
        response.put("message", "Clave actualizada correctamente.");
        
        return ResponseEntity.ok(response);
    }
}
