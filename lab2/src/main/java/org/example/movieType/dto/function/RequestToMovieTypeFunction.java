package org.example.movieType.dto.function;

import org.example.movieType.dto.PutMovieTypeRequest;
import org.example.movieType.entity.MovieType;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutMovieTypeRequest} to {@link MovieType}. Caution, some fields are not set as they should be updated
 * by business logic.
 */
public class RequestToMovieTypeFunction implements BiFunction<UUID, PutMovieTypeRequest, MovieType> {
    @Override
    public MovieType apply(UUID uuid, PutMovieTypeRequest putMovieTypeRequest) {
        return MovieType.builder()
                .id(uuid)
                .typeName(putMovieTypeRequest.getTypeName())
                .description(putMovieTypeRequest.getDescription())
                .age(putMovieTypeRequest.getAge())
                .build();
    }
}