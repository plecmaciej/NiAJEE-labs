package org.example.movie.repository.persistence;

import org.example.movie.entity.Movie;
import org.example.movieType.entity.MovieType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.movie.repository.api.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Movie entity. Repositories should be used in business layer (e.g.: in services). The request scope
 * is a result of the fact that {@link EntityManager} objects cannot be used in multiple threads (are not thread safe).
 * Because services are CDI application scoped beans (technically singletons) then repositories must be thread scoped in
 * order to ensure single entity manager for single thread.
 */
@RequestScoped
public class MoviePersistenceRepository implements MovieRepository {

    /**
     * Connection with the database (not thread safe).
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Movie> findAllByMovieType(MovieType movieType) {
        return em.createQuery("select m from Movie m where m.movieType = :movieType", Movie.class)
                .setParameter("movieType", movieType)
                .getResultList();
    }

    @Override
    public Optional<Movie> find(UUID id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public void create(Movie entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Movie entity) {
        em.remove(em.find(Movie.class, entity.getId()));
    }

    @Override
    public void update(Movie entity) {
        em.merge(entity);
    }
}