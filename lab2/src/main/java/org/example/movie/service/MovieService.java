package org.example.movie.service;

import org.example.movie.entity.Movie;
import org.example.movie.repository.api.MovieRepository;
import org.example.movieType.repository.api.MovieTypeRepository;
import org.example.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding Movie entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class MovieService {

    /**
     * Repository for Movie entity.
     */
    private final MovieRepository MovieRepository;

    /**
     * Repository for MovieType entity.
     */
    private final MovieTypeRepository MovieTypeRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param MovieRepository     repository for Movie entity
     * @param MovieTypeRepository repository for MovieType entity
     * @param userRepository           repository for user entity
     */
    @Inject
    public MovieService(MovieRepository MovieRepository, MovieTypeRepository MovieTypeRepository, UserRepository userRepository) {
        this.MovieRepository = MovieRepository;
        this.MovieTypeRepository = MovieTypeRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single Movie.
     *
     * @param id Movie's id
     * @return container with Movie
     */
    public Optional<Movie> find(UUID id) {
        return MovieRepository.find(id);
    }

    /**
     * @return all available Movies
     */
    public List<Movie> findAll() {
        return MovieRepository.findAll();
    }

    /**
     * Stores new Movie in the data store.
     *
     * @param Movie new Movie to be saved
     */
    public void create(Movie Movie) {
        MovieRepository.create(Movie);
    }

    /**
     * Deletes Movie from the data store.
     *
     * @param id Movie's id
     */
    public void delete(UUID id) {
        MovieRepository.delete(MovieRepository.find(id).orElseThrow());
    }

}
