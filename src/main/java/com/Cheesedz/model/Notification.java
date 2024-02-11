package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
    private String title;
    private String detailContent;

    private Long userID;
    public Notification(String title, String detailContent, Long userID) {
        this.title = title;
        this.detailContent = detailContent;
        this.userID = userID;
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
