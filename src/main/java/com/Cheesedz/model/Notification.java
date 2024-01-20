package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "notification", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_seq",
            sequenceName = "notification_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_seq"
    )
    private Long id;
//    @Column(name = "title")
    private String title;
//    @Column(name = "detailContent")
    private String detailContent;
//    @Column(name = "timestamp")

    private Long userID;
    public Notification(String title, String detailContent, Long userID) {
        this.title = title;
        this.detailContent = detailContent;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Notification{id=" + id + ","
                + "userID=" + userID + ","
                +"title=" + title + ","
                + "detailContent=" + detailContent + "}";
    }
}
