package com.electronicpayment.televentas.features.saleprogress.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicpayment.televentas.features.saleprogress.domain.dto.SaleProgressDto;
import com.electronicpayment.televentas.features.saleprogress.domain.services.ISaleProgressService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sales-progress")
public class SaleProgressController {
    
    private final ISaleProgressService saleProgressService;

    @GetMapping()
    public ResponseEntity<SaleProgressDto> getSalesProgress() {
        SaleProgressDto result = this.saleProgressService.getSalesProgress();

        if(result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(result);
    }
    
}
