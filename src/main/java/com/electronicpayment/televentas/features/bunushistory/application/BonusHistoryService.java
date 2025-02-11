package com.electronicpayment.televentas.features.bunushistory.application;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.electronicpayment.televentas.features.bunushistory.domain.dto.BonusHistoryDto;
import com.electronicpayment.televentas.features.bunushistory.domain.services.IBonusHistoryService;
import com.electronicpayment.televentas.shared.entities.Bonus;
import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IBonusRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BonusHistoryService implements IBonusHistoryService {

    private final IBonusRepository bonusRepository;

    @Override
    public List<BonusHistoryDto> listHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());

        List<Bonus> bonus = this.bonusRepository.findAllByUser(new User(userId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        AtomicInteger counter = new AtomicInteger(1);

        return bonus.stream()
            .map(x -> new BonusHistoryDto(
                    counter.getAndIncrement(),  
                    x.getUploadDate().format(formatter),
                    x.getImportAmount(),
                    x.getDetail()))
            .collect(Collectors.toList());
    }

}
