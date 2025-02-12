package com.electronicpayment.televentas.shared.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.electronicpayment.televentas.shared.entities.Bonus;
import com.electronicpayment.televentas.shared.entities.User;

public interface IBonusRepository extends JpaRepository<Bonus, UUID> {
    @Query("SELECT b FROM Bonus b WHERE b.user = :user AND MONTH(b.uploadDate) = :month")
    List<Bonus> findAllByUserAndUploadDateMonth(User user, int month);
}
