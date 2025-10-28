package org.example.datastore.component;

import lombok.extern.java.Log;
import org.example.serialization.component.CloningUtility;
import org.example.user.entity.User;
import org.example.movie.entity.Movie;
import org.example.movieType.entity.MovieType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * For the sake of simplification instead of using real database this example is using a data source object which should
 * be put in servlet context in a single instance. In order to avoid {@link java.util.ConcurrentModificationException}
 * all methods are synchronized. Normally synchronization would be carried on by the database server. Caution, this is
 * very inefficient implementation but can be used to present other mechanisms without obscuration example with ORM
 * usage.
 */
@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {

    /**
     * Set of all available Movie types.
     */
    private final Set<MovieType> MovieTypes = new HashSet<>();

    /**
     * Set of all Movies.
     */
    private final Set<Movie> Movies = new HashSet<>();

    /**
     * Set of all users.
     */
    private final Set<User> users = new HashSet<>();

    /**
     * Component used for creating deep copies.
     */
    private final CloningUtility cloningUtility;

    /**
     * @param cloningUtility component used for creating deep copies
     */
    @Inject
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    /**
     * Seeks for all Movie types.
     *
     * @return list (can be empty) of all Movie types
     */
    public synchronized List<MovieType> findAllMovieTypes() {
        return MovieTypes.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new Movie type.
     *
     * @param value new Movie type to be stored
     * @throws IllegalArgumentException if Movie type with provided id already exists
     */
    public synchronized void createMovieType(MovieType value) throws IllegalArgumentException {
        if (MovieTypes.stream().anyMatch(MovieType -> MovieType.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The Movie type id \"%s\" is not unique".formatted(value.getId()));
        }
        MovieTypes.add(cloningUtility.clone(value));
    }

    public synchronized void deleteMovieType(UUID id) throws IllegalArgumentException {
        if (!MovieTypes.removeIf(motorcycleType -> motorcycleType.getId().equals(id))) {
            throw new IllegalArgumentException("The motorcycle type with id \"%s\" does not exist".formatted(id));
        }
    }

    /**
     * Seeks for all Movies.
     *
     * @return list (can be empty) of all Movies
     */
    public synchronized List<Movie> findAllMovies() {
        return Movies.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new Movie.
     *
     * @param value new Movie to be stored
     * @throws IllegalArgumentException if Movie with provided id already exists or when {@link Movie} or
     *                                  {@link MovieType} with provided uuid does not exist
     */
    public synchronized void createMovie(Movie value) throws IllegalArgumentException {
        if (Movies.stream().anyMatch(Movie -> Movie.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The Movie id \"%s\" is not unique".formatted(value.getId()));
        }
        Movie entity = cloneWithRelationships(value);
        Movies.add(entity);
    }

    /**
     * Updates existing Movie.
     *
     * @param value Movie to be updated
     * @throws IllegalArgumentException if Movie with the same id does not exist or when {@link Movie} or
     *                                  {@link MovieType} with provided uuid does not exist
     */
    public synchronized void updateMovie(Movie value) throws IllegalArgumentException {
        Movie entity = cloneWithRelationships(value);
        if (Movies.removeIf(Movie -> Movie.getId().equals(value.getId()))) {
            Movies.add(entity);
        } else {
            throw new IllegalArgumentException("The Movie with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Deletes existing Movie.
     *
     * @param id id of Movie to be deleted
     * @throws IllegalArgumentException if Movie with provided id does not exist
     */
    public synchronized void deleteMovie(UUID id) throws IllegalArgumentException {
        if (!Movies.removeIf(Movie -> Movie.getId().equals(id))) {
            throw new IllegalArgumentException("The Movie with id \"%s\" does not exist".formatted(id));
        }
    }
    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(character -> character.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(character -> character.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    private Movie cloneWithRelationships(Movie value) {
        Movie entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getMovieType() != null) {
            entity.setMovieType(MovieTypes.stream()
                    .filter(MovieType -> MovieType.getId().equals(value.getMovieType().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No Movie type with id \"%s\".".formatted(value.getMovieType().getId()))));
        }

        return entity;
    }



}
