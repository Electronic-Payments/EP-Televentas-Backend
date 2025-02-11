package com.electronicpayment.televentas.features.userprofile.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserProfileDto {
    private String document;
    private boolean passwordChanged;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String phone;
    private String email;
    private String department;
    private String documentType;
}
