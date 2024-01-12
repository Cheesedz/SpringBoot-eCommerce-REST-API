package com.Cheesedz.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;
    private String detailContent;
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
        return "Notification[tile=" + title + ","
                + "detailContent=" + detailContent + ","
                + "timestamp=" + timestamp + "]";
    }
}
