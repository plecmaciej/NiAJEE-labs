package org.example.movieType.dto.function;

import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.entity.MovieType;

import java.util.function.Function;

/**
 * Function for converting {@link MovieType} to {@link GetMovieTypeResponse}.
 */
public class MovieTypeToResponseFunction implements Function<MovieType, GetMovieTypeResponse> {

    @Override
    public GetMovieTypeResponse apply(MovieType MovieType) {
        return GetMovieTypeResponse.builder()
                .id(MovieType.getId())
                .name(MovieType.getTypeName())
                .description(MovieType.getDescription())
                .age(MovieType.getAge())
                .build();
    }

}
