package org.example.movieType.repository.persistence;

import org.example.movieType.entity.MovieType;
import org.example.movieType.repository.api.MovieTypeRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Movie Type entity. Repositories should be used in business layer (e.g.: in services). The request
 * scope is a result of the fact that {@link EntityManager} objects cannot be used in multiple threads (are not thread
 * safe). Because services are CDI application scoped beans (technically singletons) then repositories must be thread
 * scoped in order to ensure single entity manager for single thread.
 */
@Dependent
public class MovieTypePersistenceRepository implements MovieTypeRepository {

    /**
     * Connection with the database (not thread safe).
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<MovieType> find(UUID id) {
        return Optional.ofNullable(em.find(MovieType.class, id));
    }

    @Override
    public List<MovieType> findAll() {
        return em.createQuery("select mt from MovieType mt", MovieType.class).getResultList();
    }

    @Override
    public void create(MovieType entity) {
        em.persist(entity);
    }

    @Override
    public void delete(MovieType entity) {
        em.remove(em.find(MovieType.class, entity.getId()));
    }

    @Override
    public void update(MovieType entity) {
        em.merge(entity);
    }
}