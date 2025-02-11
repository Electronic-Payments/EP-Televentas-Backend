package com.electronicpayment.televentas.features.saleprogress.application;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.electronicpayment.televentas.features.saleprogress.domain.dto.SaleProgressDto;
import com.electronicpayment.televentas.features.saleprogress.domain.services.ISaleProgressService;
import com.electronicpayment.televentas.shared.entities.SaleProgress;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.ISaleProgressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleProgressService implements ISaleProgressService {

    private final ISaleProgressRepository saleProgressRepository;

    @Override
    public SaleProgressDto getSalesProgress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());

        SaleProgress saleProgress = this.saleProgressRepository.findTopByUserOrderByUploadDateDesc(new User(userId));

        if (saleProgress == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return new SaleProgressDto(
                saleProgress.getValidQuantity(),
                saleProgress.getActiveQuantity(),
                saleProgress.getActivePercentage(),
                saleProgress.getDeliveryQuantity(),
                saleProgress.getDeliveryPercentage(),
                saleProgress.getPortabilityQuantity(),
                saleProgress.getMultiQuantity(),
                saleProgress.getUploadDate().format(formatter));
    }

}
