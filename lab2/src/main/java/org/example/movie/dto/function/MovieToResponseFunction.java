package org.example.movie.dto.function;

import org.example.movie.dto.GetMovieResponse;
import org.example.movie.entity.Movie;

import java.util.function.Function;

/**
 * Function for converting {@link Movie} to {@link GetMovieResponse}.
 */
public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {

    @Override
    public GetMovieResponse apply(Movie entity) {
        return GetMovieResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .status(entity.getStatus())
                .releaseDate(entity.getReleaseDate())
                .price(entity.getPrice())
                .MovieType(GetMovieResponse.MovieType.builder()
                        .id(entity.getMovieType().getId())
                        .typeName(entity.getMovieType().getTypeName())
                        .build())
                .build();
    }

}
