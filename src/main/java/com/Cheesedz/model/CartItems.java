package com.Cheesedz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cartItems",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "type")
    private String type;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItems(Product product, String type, Double totalPrice, Long quantity) {
        this.product = product;
        this.type = type;
        this.totalPrice = product.getPrice() * quantity;
        this.quantity = quantity;
    }

    @JsonIgnore
    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "SelectedProduct[" + product.toString() + ","
                + "type=" + type + ","
                + "totalPrice=" + quantity * product.getPrice() + ","
                + "quantity=" + quantity + "]";
    }
}