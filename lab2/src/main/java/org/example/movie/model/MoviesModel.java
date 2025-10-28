package org.example.movie.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents list of movies to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MoviesModel implements Serializable {

    /**
     * Represents single movie in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Movie {

        /**
         * Unique id identifying movie.
         */
        private UUID id;

        /**
         * Name of the movie.
         */
        private String title;

        /**
         * Price of the movie.
         */
        private double price;

    }

    /**
     * Name of the selected movies.
     */
    @Singular
    private List<Movie> movies;
}