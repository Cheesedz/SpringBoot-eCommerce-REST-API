package com.Cheesedz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voucher", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "voucherName" })})
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucherName")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "minimumOrderValue")
    private Double minimumOrderValue;
    @Column(name = "discountAmount")
    private Double discountAmount;
    @Column(name = "expirationDate")
    private String expirationDate;
    public Voucher() {

    }

    public Voucher(String name, String description, Double minimumOrderValue, Double discountAmount, String expirationDate) {
        this.name = name;
        this.description = description;
        this.minimumOrderValue = minimumOrderValue;
        this.discountAmount = discountAmount;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(Double minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Voucher[name=" + name + ","
                + "description=" + description + ","
                + "minimumOrderValue=" + minimumOrderValue + ","
                + "discountAmount=" + discountAmount + ","
                + "expirationDate=" + expirationDate + "]";
    }
}
