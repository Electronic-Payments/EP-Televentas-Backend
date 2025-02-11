package com.electronicpayment.televentas.features.bunushistory.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.bunushistory.domain.dto.BonusHistoryDto;
import com.electronicpayment.televentas.features.bunushistory.domain.services.IBonusHistoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bonus")
public class BonusHistoryController {
    
    private final IBonusHistoryService bonusHistoryService;

    @GetMapping("history")
    public ResponseEntity<List<BonusHistoryDto>> listHistory() {
        List<BonusHistoryDto> data = this.bonusHistoryService.listHistory();

        if(data.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(data);
    }
    
}
