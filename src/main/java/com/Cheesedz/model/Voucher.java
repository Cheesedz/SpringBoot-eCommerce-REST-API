package com.Cheesedz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "voucher", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Voucher {
    @Id
    @SequenceGenerator(
            name = "voucher_seq",
            sequenceName = "voucher_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

//    @Column(name = "voucherName")
    private String name;

//    @Column(name = "description")
    private String description;

//    @Column(name = "minimumOrderValue")
    private Double minimumOrderValue;

//    @Column(name = "discountAmount")
    private Double discountAmount;

//    @Column(name = "expirationDate")
    private String expirationDate;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private Long userID;

    public Voucher(String name, String description, Double minimumOrderValue, Double discountAmount,
                   String expirationDate, Long userID) {
        this.name = name;
        this.description = description;
        this.minimumOrderValue = minimumOrderValue;
        this.discountAmount = discountAmount;
        this.expirationDate = expirationDate;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Voucher{name=" + name + ","
                + "userID=" + userID + ","
                + "description=" + description + ","
                + "minimumOrderValue=" + minimumOrderValue + ","
                + "discountAmount=" + discountAmount + ","
                + "expirationDate=" + expirationDate + "}";
    }
}
