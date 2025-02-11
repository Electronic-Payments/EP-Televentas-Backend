package com.electronicpayment.televentas.shared.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.electronicpayment.televentas.config.audit.Auditable;
import com.electronicpayment.televentas.config.uuid.UUIDConverter;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "sales_progress")
public class SaleProgress extends Auditable {
    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "valid_quantity")
    private int validQuantity;

    @Column(name = "active_quantity")
    private int activeQuantity;

    @Column(name = "active_percentage")
    private String activePercentage;

    @Column(name = "delivery_quantity")
    private int deliveryQuantity;

    @Column(name = "delivery_percentage")
    private String deliveryPercentage;

    @Column(name = "portability_quantity")
    private int portabilityQuantity;

    @Column(name = "multi_quantity")
    private int multiQuantity;

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

    public int getValidQuantity() {
        return validQuantity;
    }

    public void setValidQuantity(int validQuantity) {
        this.validQuantity = validQuantity;
    }

    public int getActiveQuantity() {
        return activeQuantity;
    }

    public void setActiveQuantity(int activeQuantity) {
        this.activeQuantity = activeQuantity;
    }

    public String getActivePercentage() {
        return activePercentage;
    }

    public void setActivePercentage(String activePercentage) {
        this.activePercentage = activePercentage;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public String getDeliveryPercentage() {
        return deliveryPercentage;
    }

    public void setDeliveryPercentage(String deliveryPercentage) {
        this.deliveryPercentage = deliveryPercentage;
    }

    public int getPortabilityQuantity() {
        return portabilityQuantity;
    }

    public void setPortabilityQuantity(int portabilityQuantity) {
        this.portabilityQuantity = portabilityQuantity;
    }

    public int getMultiQuantity() {
        return multiQuantity;
    }

    public void setMultiQuantity(int multiQuantity) {
        this.multiQuantity = multiQuantity;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
