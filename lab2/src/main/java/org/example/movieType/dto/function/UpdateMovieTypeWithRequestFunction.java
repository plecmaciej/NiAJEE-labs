package org.example.movieType.dto.function;

import org.example.movieType.dto.PatchMovieTypeRequest;
import org.example.movieType.entity.MovieType;

import java.util.function.BiFunction;

/**
 * Returns new instance of {@link MovieType} based on provided value and updated with values from
 * {@link PatchMovieTypeRequest}.
 */
public class UpdateMovieTypeWithRequestFunction implements BiFunction<MovieType, PatchMovieTypeRequest, MovieType> {
    @Override
    public MovieType apply(MovieType entity, PatchMovieTypeRequest request) {
        return MovieType.builder()
                .id(entity.getId())
                .typeName(request.getTypeName())
                .description(request.getDescription())
                .age(entity.getAge())
                .movies(entity.getMovies())
                .build();
    }
}