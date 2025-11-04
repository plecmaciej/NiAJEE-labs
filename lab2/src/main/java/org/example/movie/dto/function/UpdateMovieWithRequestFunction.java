package org.example.movie.dto.function;

import org.example.movie.dto.PatchMovieRequest;
import org.example.movie.entity.Movie;

import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Movie} based on provided value and updated with values from
 * {@link PatchMovieRequest}.
 */
public class UpdateMovieWithRequestFunction implements BiFunction<Movie, PatchMovieRequest, Movie> {

    @Override
    public Movie apply(Movie movie, PatchMovieRequest patchMovieRequest) {
        return Movie.builder()
                .id(movie.getId())
                .title(patchMovieRequest.getTitle())
                .price(patchMovieRequest.getPrice())
                .status(patchMovieRequest.getStatus())
                .releaseDate(movie.getReleaseDate())
                .MovieType(movie.getMovieType())
                .user(movie.getUser())
                .build();
    }
}