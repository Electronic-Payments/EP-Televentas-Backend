package com.electronicpayment.televentas.features.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenResponseDto {
    public String accessToken;
}
