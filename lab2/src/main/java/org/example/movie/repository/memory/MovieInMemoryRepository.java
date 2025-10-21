package org.example.movie.repository.memory;

import org.example.datastore.component.DataStore;
import org.example.movie.entity.Movie;
import org.example.movie.repository.api.MovieRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Movie entity. Repositories should be used in business layer (e.g.: in services).
 */
@RequestScoped
public class MovieInMemoryRepository implements MovieRepository {
    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public MovieInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public List<Movie> findAll() {
        return store.findAllMovies();
    }

    @Override
    public Optional<Movie> find(UUID id) {
        return store.findAllMovies().stream()
                .filter(Movie -> Movie.getId().equals(id))
                .findFirst();
    }

    @Override
    public void create(Movie entity) {
        store.createMovie(entity);
    }

    @Override
    public void delete(Movie entity) {
        store.deleteMovie(entity.getId());
    }

    @Override
    public void update(Movie entity) {
        store.updateMovie(entity);
    }

}
