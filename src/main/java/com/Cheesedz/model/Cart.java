package com.Cheesedz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Cart {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "numberOfProducts")
    private Long numberOfProducts;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> items;

    @JsonIgnore
    public User getOwner() {
        return user;
    }

    public Long getNumberOfProducts() {
        return numberOfProducts;
    }

    public List<CartItems> getProductList() {
        return this.items == null ? null : new ArrayList<>(this.items);
    }

    public void setProductList(List<CartItems> items) {
        if (items == null) {
            this.items = null;
        } else {
            this.items = Collections.unmodifiableList(items);
        }
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
