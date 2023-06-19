package com.movie.movieproject.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity()
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate premiere;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Movie(String title, LocalDate premiere, String description, Category category) {
        this.title = title;
        this.premiere = premiere;
        this.description = description;
        this.category = category;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", premiere=" + premiere +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
