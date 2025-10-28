package org.example.movie.repository.api;

import org.example.movie.entity.Movie;
import org.example.movieType.entity.MovieType;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Movie entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface MovieRepository extends Repository<Movie, UUID> {

    List<Movie> findAllByMovieType(MovieType movieType);
}
