package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
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
