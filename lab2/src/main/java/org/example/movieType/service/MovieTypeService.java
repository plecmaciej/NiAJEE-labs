package org.example.movieType.service;

import org.example.movie.entity.Movie;
import org.example.movie.repository.api.MovieRepository;
import org.example.movieType.entity.MovieType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.movieType.repository.api.MovieTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding movieType entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class MovieTypeService {

    /**
     * Repository for movieType entity.
     */
    private final MovieTypeRepository movieTypeRepository;

    /**
     * Repository for movie entity.
     */
    private final MovieRepository movieRepository;

    /**
     * @param movieTypeRepository repository for movieType entity
     * @param movieRepository     repository for movie entity
     */
    @Inject
    public MovieTypeService(MovieTypeRepository movieTypeRepository, MovieRepository movieRepository) {
        this.movieTypeRepository = movieTypeRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * @param id movieType's id
     * @return container with movieType entity
     */
    public Optional<MovieType> find(UUID id) {
        return movieTypeRepository.find(id);
    }

    /**
     * @return all available movieTypes
     */
    public List<MovieType> findAll() {
        return movieTypeRepository.findAll();
    }

    /**
     * Stores new movieType in the data store.
     *
     * @param movieType new movieType to be saved
     */
    public void create(MovieType movieType) {
        movieTypeRepository.create(movieType);
    }

    /**
     * Updates movieType in the data store.
     *
     * @param movieType movieType to be updated
     */
    public void update(MovieType movieType) {
        movieTypeRepository.update(movieType);
    }
    /**
     * Deletes movieType from the data store.
     *
     * @param id movieType's id to be deleted
     */
    public void delete(UUID id) {
        MovieType movieType = movieTypeRepository.find(id).orElseThrow();
        List<Movie> movies = movieRepository.findAllByMovieType(movieType);
        if (!movies.isEmpty()) {
            for (Movie movie : movies) {
                movieRepository.delete(movie);
            }
        }
        movieTypeRepository.delete(movieType);
    }
}