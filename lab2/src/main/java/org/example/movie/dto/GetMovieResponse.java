package org.example.movie.dto;

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
import java.util.UUID;

/**
 * Represents single Movie.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMovieResponse {

    /**
     * Represents single Movie type.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class MovieType {
        /**
         * Unique id identifying Movie type.
         */
        private UUID id;

        /**
         * Name of the Movie type.
         */
        private String typeName;
    }

    /**
     * Unique id identifying Movie.
     */
    private UUID id;

    /**
     * Name of the Movie.
     */
    private String title;


    /**
     * Movie's color.
     */
    private EnumMovie.Status status;
    /**
     * Movie's production date.
     */
    private LocalDate releaseDate;

    /**
     * Movie's price.
     */
    private double price;

    /**
     * Movie's type.
     */
    private MovieType movieType;
}
