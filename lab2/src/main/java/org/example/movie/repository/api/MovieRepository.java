package org.example.movie.repository.api;

import org.example.movie.entity.Movie;
import org.example.movieType.entity.MovieType;
import org.example.repository.api.Repository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Movie entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface MovieRepository extends Repository<Movie, UUID> {

    /**
     * Seeks for single user's movie.
     *
     * @param id   character's id
     * @param user character's owner
     * @return container (can be empty) with movie
     */
    Optional<Movie> findByIdAndUser(UUID id, User user);

    /**
     * Seeks for all user's movie.
     *
     * @param user characters' owner
     * @return list (can be empty) of user's movies
     */
    List<Movie> findAllByUser(User user);


    List<Movie> findAllByMovieType(MovieType movieType);
}
