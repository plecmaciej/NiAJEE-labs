package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MovieModel;

import java.util.function.Function;

/**
 * Function for converting {@link Movie} to {@link MovieModel}.
 */
public class MovieToModelFunction implements Function<Movie, MovieModel> {

    @Override
    public MovieModel apply(Movie movie) {
        return MovieModel.builder()
                .title(movie.getTitle())
                .price(movie.getPrice())
                .director(movie.getDirector())
                .status(movie.getStatus())
                .releaseDate(movie.getReleaseDate())
                .type(movie.getMovieType().getTypeName())
                .build();
    }

}