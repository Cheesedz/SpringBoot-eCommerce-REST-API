package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
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

    private String name;

    private String description;

    private Double minimumOrderValue;

    private Double discountAmount;

    private String expirationDate;

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
