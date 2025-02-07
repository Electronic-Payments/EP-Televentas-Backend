package com.electronicpayment.televentas.shared.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicpayment.televentas.shared.entities.DocumentType;
import com.electronicpayment.televentas.shared.entities.User;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByDocument(String username);
    User findByDocumentAndDocumentTypeAndStatus(String document, DocumentType documentType, boolean status);
}