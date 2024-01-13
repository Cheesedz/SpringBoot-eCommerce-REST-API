package com.Cheesedz.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "order", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "typeOfPayment")
    private String typeOfPayment;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "status")
    private String status;
    //@OneToMany(mappedBy = "productID")
    private HashMap<Product, Long> items;
    private List<Voucher> appliedVouchers;

    public Order() {

    }

    public Order(Timestamp timestamp, String typeOfPayment, Double totalPrice, HashMap<Product, Long> items,
                 String status, List<Voucher> appliedVouchers) {
        this.timestamp = timestamp;
        this.typeOfPayment = typeOfPayment;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
        this.appliedVouchers = appliedVouchers;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Double getTotalPrice() {
        double sum = 0;
        for (Map.Entry<Product, Long> entry : items.entrySet()) {
            sum = sum + entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<Product, Long> getItems() {
        return items;
    }

    public void setItems(HashMap<Product, Long> items) {
        this.items = items;
    }

    public List<Voucher> getAppliedVouchers() {
        return appliedVouchers;
    }

    public void setAppliedVouchers(List<Voucher> appliedVouchers) {
        this.appliedVouchers = appliedVouchers;
    }

    @Override
    public String toString() {
        return "Order[" + items.toString() + ","
                + "appliedVouchers=" + appliedVouchers.toString() + ","
                + "totalPrice=" + totalPrice + ","
                + "typeOfPayment=" + typeOfPayment + ","
                + "status=" + status + ","
                + "timestamp=" + timestamp + "]";
    }
}
