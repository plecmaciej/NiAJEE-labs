package org.example.movieType.service;

import org.example.movieType.entity.MovieType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    private final MovieTypeRepository repository;


    /**
     * @param repository repository for movieType entity
     */
    @Inject
    public MovieTypeService(MovieTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id movieType's id
     * @return container with movieType entity
     */
    public Optional<MovieType> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available movieTypes
     */
    public List<MovieType> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new movieType in the data store.
     *
     * @param movieType new movieType to be saved
     */
    public void create(MovieType movieType) {
        repository.create(movieType);
    }

    /**
     * Updates movieType in the data store.
     *
     * @param movieType movieType to be updated
     */
    @Transactional
    public void update(MovieType movieType) {
        repository.update(movieType);
    }
    /**
     * Deletes movieType from the data store.
     *
     * @param id movieType's id to be deleted
     */
    @Transactional
    public void delete(UUID id) {
        repository.delete(repository.find(id).orElseThrow());
    }

}