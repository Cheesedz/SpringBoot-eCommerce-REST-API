package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cartItems",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class CartItems {
    @Id
    @SequenceGenerator(
            name = "cartItems_seq",
            sequenceName = "cartItems_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartItems_seq"
    )
    private Long id;
    private Long cartID;
    private Long productID;
    private Long quantity;

    public CartItems(Long cartID, Long productID, Long quantity) {
        this.cartID = cartID;
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{cartID=" + cartID + ","
                + "productID=" + productID + ","
                + "quantity=" + quantity + "}";
    }
}