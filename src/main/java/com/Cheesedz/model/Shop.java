package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "shop", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Shop {
    @Id
    @SequenceGenerator(
            name = "shop_seq",
            sequenceName = "shop_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shop_seq"
    )
    private Long shopID;
    private String name;
    private String typeOfShop;
    private String typeOfProduct;
    private String description;
    private Long followers;
    private Long following;
    private String joiningDate;
    private Double chatPerformance;

    public Shop(String name, String typeOfShop, String typeOfProduct, String description, Long followers,
                Long following, String joiningDate, Double chatPerformance) {
        this.name = name;
        this.typeOfShop = typeOfShop;
        this.typeOfProduct = typeOfProduct;
        this.description = description;
        this.followers = followers;
        this.following = following;
        this.joiningDate = joiningDate;
        this.chatPerformance = chatPerformance;
    }

    public Long getShopID() {
        return shopID;
    }

    public void setShopID(Long shopID) {
        this.shopID = shopID;
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Shop{name=").append(name).append(",").append("description=").append(description).append(",")
                .append("followers=").append(followers).append(",").append("following=").append(following).append(",")
                .append("typeOfShop=").append(typeOfShop).append(",").append("typeOfProduct=").append(typeOfProduct)
                .append(",").append("joiningDate=").append(joiningDate).append(",").append("chatPerformance=")
                .append(chatPerformance).append("}\n");
        return res.toString();
    }
}
