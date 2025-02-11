package com.electronicpayment.televentas.features.auth.domain.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DocumentTypeDto {
    private UUID id;
    private String name;
}
