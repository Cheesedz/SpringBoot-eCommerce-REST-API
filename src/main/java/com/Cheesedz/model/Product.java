package com.Cheesedz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "productName" })})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productName")
    private Long id;
    private String name;
//    @OneToOne
//    @JoinColumn(name = "categoryName")
    private String category;
    //@Column(name = "description")
    private String description;
    //@Column(name = "shopName")
    private String shopName;
    //@Column(name = "sold")
    private Long sold;
    //@Column(name = "price")
    private Double price;
    //@Column(name = "rating")
    private Double rating;
    //@Column(name = "available")
    private Long available;
    public Product() {

    }

    public Product(String name, String description, String shopName, Long sold, Double price, Double rating,
                   String category, Long available) {
        this.name = name;
        this.description = description;
        this.shopName = shopName;
        this.sold = sold;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" + "id" + id + ","
                + "name=" + name + ","
                + "category=" + category + ","
                + "description=" + description + ","
                + "shopName=" + shopName + ","
                + "sold=" + sold + ","
                + "price=" + price + ","
                + "rating=" + rating + ","
                + "available=" + available + "}";
    }
}
