package com.Cheesedz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryName")
    private String name;
    @Column(name = "thumbnailPath")
    private String thumbnailPath;
    public Category() {

    }

    public Category(String name, String description, String thumbnailPath) {
        this.name = name;
        this.thumbnailPath = thumbnailPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @Override
    public String toString() {
        return "Category[name=" + name + ","
                + "thumbnailPath=" + thumbnailPath + "]";
    }
}
