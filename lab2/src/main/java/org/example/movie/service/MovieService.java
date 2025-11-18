package org.example.movie.service;

import org.example.movie.entity.Movie;
import org.example.movie.repository.api.MovieRepository;
import org.example.movieType.entity.MovieType;
import org.example.movieType.repository.api.MovieTypeRepository;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.repository.api.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding movie entity.
 */
@LocalBean
@Stateless
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
     * Security context
     */
    private final SecurityContext securityContext;

    /**
     * @param movieRepository     repository for movie entity
     * @param movieTypeRepository repository for movieType entity
     * @param userRepository           repository for user entity
     * @param securityContext          security context
     */
    @Inject
    public MovieService(
            MovieRepository movieRepository,
            MovieTypeRepository movieTypeRepository,
            UserRepository userRepository,
            @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext
    ) {
        this.movieRepository = movieRepository;
        this.movieTypeRepository = movieTypeRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    /**
     * Finds single movie.
     *
     * @param id movie's id
     * @return container with movie
     */
    @RolesAllowed(UserRoles.USER)
    public Optional<Movie> find(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return movieRepository.find(id);
        }
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return movieRepository.findByIdAndUser(id, user);
    }

    /**
     * @param id   character's id
     * @param user existing user
     * @return selected character for user
     */
    @RolesAllowed(UserRoles.USER)
    public Optional<Movie> find(User user, UUID id) {
        return movieRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available movies
     */
    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAll() {

        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return movieRepository.findAll();
        }
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return movieRepository.findAllByUser(user);
    }

    /**
     * @return all available movies by movie type
     */
    @RolesAllowed(UserRoles.ADMIN)
    public List<Movie> findAll(MovieType movieType) {
        return movieRepository.findAllByMovieType(movieType);
    }


    @RolesAllowed(UserRoles.ADMIN)
    public Optional<List<Movie>> findAllByMovieType(UUID id) {
        return movieTypeRepository.find(id)
                .map(movieRepository::findAllByMovieType);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Optional<List<Movie>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(movieRepository::findAllByUser);
    }

    /**
     * Stores new movie in the data store.
     *
     * @param movie new movie to be saved
     */
    @RolesAllowed(UserRoles.ADMIN)
    public void create(Movie movie) {
        if (movieRepository.find(movie.getId()).isPresent()) {
            throw new IllegalArgumentException("Movie already exists");
        }
        if (movieTypeRepository.find(movie.getMovieType().getId()).isEmpty()) {
            throw new IllegalArgumentException("Movie type does not exist");
        }

        movieRepository.create(movie);
        movieTypeRepository.find(movie.getMovieType().getId())
                .ifPresent(movieType -> movieType.getMovies().add(movie));
        userRepository.find(movie.getUser().getId())
                .ifPresent(user -> user.getMovies().add(movie));
    }

    /**
     * Creates new movie for current caller principal.
     *
     * @param movie new movie
     */
    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Movie movie) {
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        movie.setUser(user);
        create(movie);
    }

    /**
     * Deletes movie from the data store.
     *
     * @param id movie's id
     */

    /**
     * Updates existing movie in the data store.
     *
     * @param movie movie to be updated
     */

    @RolesAllowed(UserRoles.USER)
    public void update(Movie movie) {
        checkAdminRoleOrOwner(movieRepository.find(movie.getId()));
        movieRepository.update(movie);
    }

    @RolesAllowed(UserRoles.USER)
    public void delete(UUID id) {
        checkAdminRoleOrOwner(movieRepository.find(id));
        movieRepository.delete(movieRepository.find(id).orElseThrow());
    }

    /**
     * @param movie character to be checked
     * @throws EJBAccessException when caller principal has no admin role and is not character's owner
     */
    private void checkAdminRoleOrOwner(Optional<Movie> movie) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && movie.isPresent()
                && movie.get().getUser().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }

}