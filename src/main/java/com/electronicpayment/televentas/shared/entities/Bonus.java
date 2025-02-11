package com.electronicpayment.televentas.shared.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.electronicpayment.televentas.config.UUIDConverter;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "bonus")
public class Bonus extends Auditable {
    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "detail")
    private String detail;

    @Column(name = "import")
    private double importAmount;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getImportAmount() {
        return importAmount;
    }

    public void setImportAmount(double importAmount) {
        this.importAmount = importAmount;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
