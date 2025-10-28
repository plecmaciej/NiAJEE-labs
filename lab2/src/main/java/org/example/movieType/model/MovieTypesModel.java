package org.example.movieType.model;

import org.example.movie.model.MoviesModel;
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
 * JSF view model class in order to not use entity classes. Represents list of movie types to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieTypesModel implements Serializable {

    /**
     * Represents single movie type in list.
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
         * Unique id identifying movie type.
         */
        private UUID id;

        /**
         * Name of the movie type.
         */
        private String typeName;

    }

    /**
     * Name of the selected movie types.
     */
    @Singular
    private List<MovieType> movieTypes;

}