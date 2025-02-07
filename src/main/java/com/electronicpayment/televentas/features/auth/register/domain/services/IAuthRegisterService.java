package com.electronicpayment.televentas.features.auth.register.domain.services;

import com.electronicpayment.televentas.features.auth.register.domain.dto.RegisterUserDto;
import com.electronicpayment.televentas.shared.dto.TokenResponseDto;

public interface IAuthRegisterService {
    TokenResponseDto register(RegisterUserDto request);
}
