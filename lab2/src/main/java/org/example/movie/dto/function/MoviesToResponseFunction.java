package org.example.movie.dto.function;

import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.entity.Movie;

import java.util.List;
import java.util.function.Function;

public class MoviesToResponseFunction implements Function<List<Movie>, GetMoviesResponse> {

    @Override
    public GetMoviesResponse apply(List<Movie> entities) {
        return GetMoviesResponse.builder()
                .movies(entities.stream()
                        .map(Movie -> GetMoviesResponse.Movie.builder()
                                .id(Movie.getId())
                                .title(Movie.getTitle())
                                .price(Movie.getPrice())
                                .build())
                        .toList())
                .build();
    }
}
