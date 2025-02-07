package com.electronicpayment.televentas.shared.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicpayment.televentas.shared.entities.DocumentType;

public interface IDocumentTypeRepository extends JpaRepository<DocumentType, UUID> {
    List<DocumentType> findAllByStatus(boolean status);
}
