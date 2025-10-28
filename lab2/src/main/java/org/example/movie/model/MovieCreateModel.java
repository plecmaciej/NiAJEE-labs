package org.example.movie.model;

import org.example.movie.entity.EnumMovie;
import org.example.movieType.entity.MovieType;
import org.example.movieType.model.MovieTypeModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents new movie to be created. Includes oll
 * fields which can be used in movie creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieCreateModel {

    /**
     * Unique id (primary key).
     */
    private UUID id;

    /**
     * Movie's name.
     */
    private String title;

    /**
     * Movie's horsepower.
     */
    private String director;

    /**
     * Movie's status.
     */
    private EnumMovie.Status status;


    /**
     * Movie's release date.
     */
    private LocalDate releaseDate;

    /**
     * Movie's price.
     */
    private double price;


    /**
     * Movie's type.
     */
    private MovieTypeModel movieType;

}