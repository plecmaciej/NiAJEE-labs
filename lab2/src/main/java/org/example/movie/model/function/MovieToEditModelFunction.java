package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MovieEditModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Function for converting {@link Movie} to {@link MovieEditModel}.
 */
public class MovieToEditModelFunction implements Function<Movie, MovieEditModel>, Serializable {

    @Override
    public MovieEditModel apply(Movie movie) {
        return MovieEditModel.builder()
                .title(movie.getTitle())
                .price(movie.getPrice())
                .status(movie.getStatus())
                .build();
    }

}