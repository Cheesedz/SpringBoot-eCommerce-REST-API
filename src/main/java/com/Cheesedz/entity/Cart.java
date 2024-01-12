package com.Cheesedz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberOfProducts;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedProduct> productList;
    public Cart() {

    }

    public Cart(Long numberOfProducts, List<SelectedProduct> productList) {
        this.numberOfProducts = numberOfProducts;
        this.productList = productList;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Cart[numberOfProducts=" + numberOfProducts + ","
                + "productList=" + productList + "\n");
        productList.forEach(x -> {
            res.append(x.toString() + "\n");
        });
        return res.toString();
    }
}
