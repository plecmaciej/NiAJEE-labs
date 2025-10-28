package org.example.movie.model;

import lombok.*;
import org.example.movie.entity.EnumMovie;

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
public class MovieEditModel {

    /**
     * Movie name.
     */
    private String title;

    /**
     * Movie price.
     */
    private double price;


    /**
     * Movie's status.
     */
    private EnumMovie.Status status;


    /**
     * Movie's release date.
     */
    //private LocalDate releaseDate;

    /**
     * Movie type.
     */
    //private String type;

}