package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Cart {
    @Id
    @SequenceGenerator(
            name = "cart_seq",
            sequenceName = "cart_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_seq"
    )
    private Long id;

    private Long userID;

    @Override
    public String toString() {
        return "Cart{id=" + id + ","
                + "userID=" + userID + "}";
    }
}
