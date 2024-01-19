package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" })})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String thumbnailPath;

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
        return "Category{name=" + name + ","
                + "thumbnailPath=" + thumbnailPath + "}";
    }
}
