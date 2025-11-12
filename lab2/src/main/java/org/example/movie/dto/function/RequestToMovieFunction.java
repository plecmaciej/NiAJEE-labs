package org.example.movie.dto.function;

import org.example.movie.dto.PutMovieRequest;
import org.example.movie.entity.Movie;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutMovieRequest} to {@link Movie}.
 */
public class RequestToMovieFunction implements BiFunction<UUID, PutMovieRequest, Movie> {

    @Override
    public Movie apply(UUID uuid, PutMovieRequest putMovieRequest) {
        return Movie.builder()
                .id(uuid)
                .title(putMovieRequest.getTitle())
                .status(putMovieRequest.getStatus())
                .releaseDate(putMovieRequest.getReleaseDate())
                .price(putMovieRequest.getPrice())
                .build();
    }
}