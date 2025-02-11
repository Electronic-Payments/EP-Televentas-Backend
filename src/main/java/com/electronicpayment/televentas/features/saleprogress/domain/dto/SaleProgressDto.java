package com.electronicpayment.televentas.features.saleprogress.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SaleProgressDto {
    private int validQuantity;
    private int activeQuantity;
    private String activePercentage;
    private int deliveryQuantity;
    private String deliveryPercentage;
    private int portabilityQuantity;
    private int multiQuantity;
    private String uploadDate; 
}
