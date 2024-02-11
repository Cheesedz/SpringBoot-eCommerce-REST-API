package com.Cheesedz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @Override
    public String toString() {
        return "Category{name=" + name + ","
                + "thumbnailPath=" + thumbnailPath + "}";
    }
}
