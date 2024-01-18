package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = { "orderId" })})
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_seq",
            sequenceName = "order_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_seq"
    )
    private Long orderID;
//    @Column(name = "typeOfPayment")
    private Long userID;
    private String typeOfPayment;
//    @Column(name = "totalPrice")
//    @Column(name = "status")
    private String status;
    //@OneToMany(mappedBy = "productID")
    private String orderDate;
    private String expectedShippedDate;
    private Long voucherID;

    public Order(Long userID, String typeOfPayment, String status, String orderDate, String expectedShippedDate,
                 Long voucherID) {
        this.userID = userID;
        this.typeOfPayment = typeOfPayment;
        this.status = status;
        this.orderDate = orderDate;
        this.expectedShippedDate = expectedShippedDate;
        this.voucherID = voucherID;
    }

    public Long getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Long voucherID) {
        this.voucherID = voucherID;
    }

    public Long getOrderNumber() {
        return orderID;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderID = orderNumber;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getExpectedShippedDate() {
        return expectedShippedDate;
    }

    public void setExpectedShippedDate(String expectedShippedDate) {
        this.expectedShippedDate = expectedShippedDate;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{userID=" + userID + ","
                + "typeOfPayment=" + typeOfPayment + ","
                + "orderDate=" + orderDate + ","
                + "expectedShipDate=" + expectedShippedDate + ","
                + "status=" + status + ","
                + "voucherID=" + voucherID + "}";
    }
}
