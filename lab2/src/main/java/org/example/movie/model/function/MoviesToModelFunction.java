package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MoviesModel;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List <Movie>} to {@link MoviesModel}.
 */
public class MoviesToModelFunction implements Function<List<Movie>, MoviesModel> {

    @Override
    public MoviesModel apply(List<Movie> movies) {
        return MoviesModel.builder()
                .movies(movies.stream()
                        .map(movie -> MoviesModel.Movie.builder()
                                .id(movie.getId())
                                .title(movie.getTitle())
                                .price(movie.getPrice())
                                .build())
                        .toList())
                .build();
    }

}