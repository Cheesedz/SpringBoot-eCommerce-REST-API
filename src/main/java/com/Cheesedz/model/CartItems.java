package com.Cheesedz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartItems",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "type")
    private String type;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "belongTo")
    private Cart cart;
    public CartItems() {

    }

    public CartItems(Product product, String type, Double totalPrice, Long quantity) {
        this.product = product;
        this.type = type;
        this.totalPrice = product.getPrice() * quantity;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setTotalPrice(Product product, Long quantity) {
        this.totalPrice = product.getPrice() * quantity;
    }

    public Double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SelectedProduct[" + product.toString() + ","
                + "type=" + type + ","
                + "totalPrice=" + quantity * product.getPrice() + ","
                + "quantity=" + quantity + "]";
    }
}