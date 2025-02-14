package com.electronicpayment.televentas.features.auth.domain.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String email;
    private String newPassword;
}
