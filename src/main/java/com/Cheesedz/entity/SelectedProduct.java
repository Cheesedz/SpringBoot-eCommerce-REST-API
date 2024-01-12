package com.Cheesedz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "selectedProduct")
public class SelectedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Product product;
    private String type;
    private Double totalPrice;
    private Long quantity;
    private Voucher appliedVoucher;
    public SelectedProduct() {

    }

    public SelectedProduct(Product product, String type, Double totalPrice, Long quantity, Voucher appliedVoucher) {
        this.product = product;
        this.type = type;
        this.totalPrice = product.getPrice() * quantity;
        this.quantity = quantity;
        this.appliedVoucher = appliedVoucher;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Voucher getAppliedVoucher() {
        return appliedVoucher;
    }

    public void setAppliedVoucher(Voucher appliedVoucher) {
        this.appliedVoucher = appliedVoucher;
    }

    @Override
    public String toString() {
        return "SelectedProduct[" + product.toString() + ","
                + "type=" + type + ","
                + "totalPrice=" + quantity * product.getPrice() + ","
                + "quantity=" + quantity + ","
                + "appliedVoucher=" + appliedVoucher + "]";
    }
}
