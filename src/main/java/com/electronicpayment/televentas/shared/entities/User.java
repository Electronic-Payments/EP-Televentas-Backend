package com.electronicpayment.televentas.shared.entities;

import java.util.UUID;

import com.electronicpayment.televentas.config.UUIDConverter;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Auditable {
    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "document")
    private String document;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "paternal_last_name")
    private String paternalLastName;

    @Column(name = "maternal_last_name")
    private String maternalLastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    public User(String document, String password, UUID documentTypeId) {
        this.document = document;
        this.password = password;
        this.documentType = new DocumentType(documentTypeId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }
}
