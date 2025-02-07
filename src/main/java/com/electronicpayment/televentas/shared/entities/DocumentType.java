package com.electronicpayment.televentas.shared.entities;

import java.util.UUID;

import com.electronicpayment.televentas.config.UUIDConverter;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "document_types")
public class DocumentType extends Auditable {
    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    public DocumentType(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
