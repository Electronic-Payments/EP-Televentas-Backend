package com.electronicpayment.televentas.features.bunushistory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BonusHistoryDto {
    private int number;
    private String date;
    private double amount;
    private String detail;
}
