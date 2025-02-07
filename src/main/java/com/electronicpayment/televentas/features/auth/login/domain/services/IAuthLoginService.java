package com.electronicpayment.televentas.features.auth.login.domain.services;

import java.util.List;

import com.electronicpayment.televentas.features.auth.login.domain.dto.LoginDto;
import com.electronicpayment.televentas.features.auth.register.domain.dto.DocumentTypeDto;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;

public interface IAuthLoginService {
    TokenResponseDto login(LoginDto request);
    List<DocumentTypeDto> listDocumentTypes();
}
