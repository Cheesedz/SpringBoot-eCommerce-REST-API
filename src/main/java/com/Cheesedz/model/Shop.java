package com.Cheesedz.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shop", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "shopName" })})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String typeOfShop;
    private String typeOfProduct;
    private String description;
    private Long followers;
    private Long following;
    private String joiningDate;
    private Double chatPerformance;
    @OneToMany(mappedBy = "shopName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;
    public Shop() {

    }

    public Shop(String name, String typeOfShop, String typeOfProduct, String description, Long followers,
                Long following, String joiningDate, Double chatPerformance, List<Product> productList) {
        this.name = name;
        this.typeOfShop = typeOfShop;
        this.typeOfProduct = typeOfProduct;
        this.description = description;
        this.followers = followers;
        this.following = following;
        this.joiningDate = joiningDate;
        this.chatPerformance = chatPerformance;
        this.productList = productList;
    }

    public String getTypeOfShop() {
        return typeOfShop;
    }

    public void setTypeOfShop(String typeOfShop) {
        this.typeOfShop = typeOfShop;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
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

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getChatPerformance() {
        return chatPerformance;
    }

    public void setChatPerformance(Double chatPerformance) {
        this.chatPerformance = chatPerformance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Shop[name=").append(name).append(",").append("description=").append(description).append(",")
                .append("followers=").append(followers).append(",").append("following=").append(following).append(",")
                .append("typeOfShop=").append(typeOfShop).append(",").append("typeOfProduct=").append(typeOfProduct)
                .append(",").append("joiningDate=").append(joiningDate).append(",").append("chatPerformance=")
                .append(chatPerformance).append("\n");
        productList.forEach(x -> res.append(x.toString()).append("\n"));
        return res.toString();
    }
}
