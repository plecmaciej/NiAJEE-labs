package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MovieEditModel;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.BiFunction;

/**
 * Function for converting {@link Movie} to {@link MovieEditModel}.
 */
public class UpdateMovieWithModelFunction implements BiFunction<Movie, MovieEditModel, Movie>, Serializable {

    @Override
    public Movie apply(Movie entity, MovieEditModel request) {
        return Movie.builder()
                .id(entity.getId())
                .title(request.getTitle())
                .price(request.getPrice())
                .director(entity.getDirector())
                .status(request.getStatus())
                .releaseDate(entity.getReleaseDate())
                .MovieType(entity.getMovieType())
                .build();
    }

}