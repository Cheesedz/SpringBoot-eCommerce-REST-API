package com.Cheesedz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String description;
    private Long followers;
    private Long following;
    private String joiningDate;
    private Double chatPerformance;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;
    public Shop() {

    }

    public Shop(String name, String description, Long followers, Long following, String joiningDate, Double chatPerformance, List<Product> productList) {
        this.name = name;
        this.description = description;
        this.followers = followers;
        this.following = following;
        this.joiningDate = joiningDate;
        this.chatPerformance = chatPerformance;
        this.productList = productList;
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
        res.append("Shop[name=" + name + ","
                + "description=" + description + ","
                + "followers=" + followers + ","
                + "following=" + following + ","
                + "joiningDate=" + joiningDate + ","
                + "chatPerformance=" + chatPerformance + "\n");
        productList.forEach(x -> {
            res.append(x.toString() + "\n");
        });
        return res.toString();
    }
}
