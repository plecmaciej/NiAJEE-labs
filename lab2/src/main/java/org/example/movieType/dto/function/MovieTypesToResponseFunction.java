package org.example.movieType.dto.function;

import org.example.movieType.dto.GetMovieTypesResponse;
import org.example.movieType.entity.MovieType;

import java.util.List;
import java.util.function.Function;

/**
 * Function for converting {@link MovieType} to {@link GetMovieTypesResponse}.
 */
public class MovieTypesToResponseFunction implements Function<List<MovieType>, GetMovieTypesResponse> {

    @Override
    public GetMovieTypesResponse apply(List<MovieType> MovieTypes) {
        return GetMovieTypesResponse.builder()
                .movieTypes(MovieTypes.stream()
                        .map(movieType -> GetMovieTypesResponse.MovieType.builder()
                                .id(movieType.getId())
                                .name(movieType.getTypeName())
                                .build())
                        .toList())
                .build();
    }

}
