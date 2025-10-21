package org.example.movie;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movie {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String director;
    private double price;
    private Status status;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Movie user;

    public Movie() {}
    public Movie(Long id, String title, String description, LocalDate releaseDate, String author, double price) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public String getDirector() {
        return director;
    }
    public double getPrice() {
        return price;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}

