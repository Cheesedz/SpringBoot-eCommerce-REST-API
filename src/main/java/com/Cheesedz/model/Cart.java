package com.Cheesedz.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "cart")
    private User owner;
    @Column(name = "numberOfProducts")
    private Long numberOfProducts;
    @OneToMany
    @JoinColumn(name = "belongTo")
    private List<CartItems> items;
    public Cart() {

    }

    public Cart(Long numberOfProducts, List<CartItems> items) {
        this.numberOfProducts = numberOfProducts;
        this.items = items;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Long numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public List<CartItems> getProductList() {
        return items;
    }

    public void setProductList(List<CartItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Cart[numberOfProducts=").append(numberOfProducts).append(",").append("productList=[").append("\n");
        for (CartItems x : items) {
            res.append(x.getProduct().toString()).append("quantity: ").append(x.getQuantity()).append("]\n");
        }
        return res.toString();
    }
}
