package org.example.movie.entity;

import org.example.movieType.entity.MovieType;
import org.example.user.entity.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    private UUID id;

    private String title;

    private String description;

    private String director;

    @Enumerated(EnumType.STRING)
    private EnumMovie.Status status;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double price;

    @ManyToOne
    @JoinColumn(name = "movie_type")
    private MovieType movieType;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

}
