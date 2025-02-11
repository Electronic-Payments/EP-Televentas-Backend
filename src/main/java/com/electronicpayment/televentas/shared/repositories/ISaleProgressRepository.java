package com.electronicpayment.televentas.shared.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicpayment.televentas.shared.entities.SaleProgress;
import com.electronicpayment.televentas.shared.entities.User;

public interface ISaleProgressRepository extends JpaRepository<SaleProgress, UUID> {
    SaleProgress findTopByUserOrderByUploadDateDesc(User user);
}
