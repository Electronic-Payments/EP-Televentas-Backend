package com.electronicpayment.televentas.features.auth.register.domain.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String document;
    private String password;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private UUID documentTypeId;
}
