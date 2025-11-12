package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MovieCreateModel;
import org.example.movieType.entity.MovieType;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.function.Function;

public class ModelToMovieFunction implements Function<MovieCreateModel, Movie>, Serializable {
    @Override
    @SneakyThrows
    public Movie apply(MovieCreateModel movieCreateModel) {
        return Movie.builder()
                .id(movieCreateModel.getId())
                .title(movieCreateModel.getTitle())
                .status(movieCreateModel.getStatus())
                .director(movieCreateModel.getDirector())
                .releaseDate(movieCreateModel.getReleaseDate())
                .price(movieCreateModel.getPrice())
                .movieType(MovieType.builder()
                        .id(movieCreateModel.getMovieType().getId())
                        .build())
                .build();
    }
}