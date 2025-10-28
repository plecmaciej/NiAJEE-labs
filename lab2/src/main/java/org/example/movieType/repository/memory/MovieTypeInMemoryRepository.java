package org.example.movieType.repository.memory;


import org.example.datastore.component.DataStore;
import org.example.movieType.entity.MovieType;
import org.example.movieType.repository.api.MovieTypeRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for MovieType entity. Repositories should be used in business layer (e.g.: in services).
 */
@RequestScoped
public class MovieTypeInMemoryRepository implements MovieTypeRepository {
    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public MovieTypeInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<MovieType> find(UUID id) {
        return store.findAllMovieTypes().stream()
                .filter(MovieType -> MovieType.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<MovieType> findAll() {
        return store.findAllMovieTypes();
    }

    @Override
    public void create(MovieType entity) {
        store.createMovieType(entity);
    }

    @Override
    public void delete(MovieType entity) {
        store.deleteMovieType(entity.getId());
    }

    @Override
    public void update(MovieType entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }
}
