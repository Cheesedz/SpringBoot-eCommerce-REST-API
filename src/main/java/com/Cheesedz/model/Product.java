package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq"
    )
    private Long id;
    private Long orderID;
    private Long cartItemID;
    private Long shopID;
    private String name;
    private String category;
    private String description;
    private String shopName;
    private Long sold;
    private Double price;
    private Double rating;
    private Long available;
    private String imgURL;

    public Product(String name, Long orderID, Long shopID, String description, String shopName, Long sold, Double price,
                   Double rating, String category, Long available, String imgURL) {
        this.name = name;
        this.orderID = orderID;
        this.shopID = shopID;
        this.description = description;
        this.shopName = shopName;
        this.sold = sold;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.available = available;
        this.imgURL = imgURL;
    }

    public Long getShopID() {
        return shopID;
    }

    public void setShopID(Long shopID) {
        this.shopID = shopID;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ","
                + "name=" + name + ","
                + "orderID=" + orderID + ","
                + "shopID=" + shopID + ","
                + "category=" + category + ","
                + "description=" + description + ","
                + "shopName=" + shopName + ","
                + "sold=" + sold + ","
                + "price=" + price + ","
                + "rating=" + rating + ","
                + "available=" + available + ","
                + "imgURL=" + imgURL + "}";
    }
}
