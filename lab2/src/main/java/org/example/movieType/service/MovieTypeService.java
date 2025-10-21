package org.example.movieType.service;

import org.example.movieType.entity.MovieType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.movieType.repository.api.MovieTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding MovieType entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class MovieTypeService {

    /**
     * Repository for MovieType entity.
     */
    private final MovieTypeRepository repository;

    /**
     * @param repository repository for MovieType entity
     */
    @Inject
    public MovieTypeService(MovieTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id MovieType's id
     * @return container with MovieType entity
     */
    public Optional<MovieType> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available MovieTypes
     */
    public List<MovieType> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new MovieType in the data store.
     *
     * @param MovieType new MovieType to be saved
     */
    public void create(MovieType MovieType) {
        repository.create(MovieType);
    }
}
