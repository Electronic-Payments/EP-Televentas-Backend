package com.electronicpayment.televentas.shared.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicpayment.televentas.shared.entities.Bonus;
import com.electronicpayment.televentas.shared.entities.User;

public interface IBonusRepository extends JpaRepository<Bonus, UUID>  {
    List<Bonus> findAllByUser(User user);
}
