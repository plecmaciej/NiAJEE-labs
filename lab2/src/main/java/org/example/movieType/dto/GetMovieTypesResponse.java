package org.example.movieType.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

/**
 * GET Movie type response. Returns list of all available Movie type names.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMovieTypesResponse {

    /**
     * Represents single Movie type in list.
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
        private String name;

    }

    /**
     * List of all Movie types.
     */
    @Singular
    private List<MovieType> movieTypes;

}
