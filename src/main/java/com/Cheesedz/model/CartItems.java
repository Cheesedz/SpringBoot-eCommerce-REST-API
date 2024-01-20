package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{cartID=" + cartID + ","
                + "productID=" + productID + ","
                + "quantity=" + quantity + "}";
    }
}