package com.Cheesedz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voucher", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Voucher(String name, String description, Double minimumOrderValue, Double discountAmount, String expirationDate) {
        this.name = name;
        this.description = description;
        this.minimumOrderValue = minimumOrderValue;
        this.discountAmount = discountAmount;
        this.expirationDate = expirationDate;
    }

    @JsonIgnore
    public User getUser() {
        return user;
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
