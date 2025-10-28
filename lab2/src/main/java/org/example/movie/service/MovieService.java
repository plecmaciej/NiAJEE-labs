package org.example.movie.service;

import org.example.movie.entity.Movie;
import org.example.movie.repository.api.MovieRepository;
import org.example.movieType.entity.MovieType;
import org.example.movieType.repository.api.MovieTypeRepository;
import org.example.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding movie entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class MovieService {

    /**
     * Repository for movie entity.
     */
    private final MovieRepository movieRepository;

    /**
     * Repository for movieType entity.
     */
    private final MovieTypeRepository movieTypeRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param movieRepository     repository for movie entity
     * @param movieTypeRepository repository for movieType entity
     * @param userRepository           repository for user entity
     */
    @Inject
    public MovieService(MovieRepository movieRepository, MovieTypeRepository movieTypeRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.movieTypeRepository = movieTypeRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single movie.
     *
     * @param id movie's id
     * @return container with movie
     */
    public Optional<Movie> find(UUID id) {
        return movieRepository.find(id);
    }

    /**
     * @return all available movies
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * @return all available movies by movie type
     */
    public List<Movie> findAll(MovieType movieType) {
        return movieRepository.findAllByMovieType(movieType);
    }

    /**
     * Stores new movie in the data store.
     *
     * @param movie new movie to be saved
     */
    public void create(Movie movie) {
        movieRepository.create(movie);
    }

    /**
     * Deletes movie from the data store.
     *
     * @param id movie's id
     */
    public void delete(UUID id) {
        movieRepository.delete(movieRepository.find(id).orElseThrow());
    }

}