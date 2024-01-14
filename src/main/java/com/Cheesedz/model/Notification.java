package com.Cheesedz.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

//@Entity
//@Table(name = "notification", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Notification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "title")
    private String title;
//    @Column(name = "detailContent")
    private String detailContent;
//    @Column(name = "timestamp")
    private Timestamp timestamp;
    public Notification() {

    }

    public Notification(String title, String detailContent, Timestamp timestamp) {
        this.title = title;
        this.detailContent = detailContent;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Notification{tile=" + title + ","
                + "detailContent=" + detailContent + ","
                + "timestamp=" + timestamp + "}";
    }
}
