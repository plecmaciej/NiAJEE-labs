package org.example.movie.model;

import org.example.movie.entity.EnumMovie;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * JSF view model class in order to not use entity classes. Represents single Movie to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieModel {

    /**
     * Movie name.
     */
    private String title;

    /**
     * Movie price.
     */
    private double price;

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
     * Movie type.
     */
    private String type;

}