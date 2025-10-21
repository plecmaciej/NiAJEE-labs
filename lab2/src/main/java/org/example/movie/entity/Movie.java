package org.example.movie.entity;

import org.example.movieType.entity.MovieType;
import org.example.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
public class Movie implements Serializable {

    private UUID id;

    private String title;

    private String description;

    private String director;

    private EnumMovie.Status status;

    private LocalDate releaseDate;

    private double price;

    private MovieType MovieType;

    private User user;

}
