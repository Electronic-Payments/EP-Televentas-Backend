package com.electronicpayment.televentas.features.auth.domain.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class LoginDto {
    private String document;
    private String password;
    private UUID documentTypeId;
}
