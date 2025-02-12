package com.electronicpayment.televentas.features.bunushistory.domain.services;

import java.util.List;

import com.electronicpayment.televentas.features.bunushistory.domain.dto.BonusHistoryDto;

public interface IBonusHistoryService {
    List<BonusHistoryDto> listHistory(int month);
}
