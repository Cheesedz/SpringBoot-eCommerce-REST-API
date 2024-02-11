package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
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

    private Long userID;

    private String typeOfPayment;

    private String status;

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
