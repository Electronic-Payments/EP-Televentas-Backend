package com.electronicpayment.televentas.features.auth.domain.services;

import java.util.List;

import com.electronicpayment.televentas.features.auth.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.features.auth.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.features.auth.domain.dto.TokenResponseDto;

public interface IAuthService {
    TokenResponseDto login(LoginDto request);
    List<DocumentTypeDto> listDocumentTypes();
    TokenResponseDto register(RegisterUserDto request);
    void changePassword(String email, String newPassword);
}
