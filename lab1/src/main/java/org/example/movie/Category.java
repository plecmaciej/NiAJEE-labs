package org.example.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.example.movie.Movie;

import java.util.List;

@Entity
public class Category {
    private Long id;
    private String name;
    private Integer minAge;
    @OneToMany(mappedBy = "category")
    private List<Movie> movies;
    public Category() {}
    public Category( String name, Integer minAge) {
        this.name = name;
        this.minAge = minAge;
    }
    public String getCategoryName() {
        return name;
    }
    public void setCategoryName(String categoryName) {
        this.name = categoryName;
    }
    public Integer getMinAge() {
        return minAge;
    }
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

}
