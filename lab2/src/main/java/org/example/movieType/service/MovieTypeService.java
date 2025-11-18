package org.example.movieType.service;

import org.example.movieType.entity.MovieType;
import org.example.movieType.repository.api.MovieTypeRepository;
import org.example.user.entity.UserRoles;
import jakarta.annotation.security.PermitAll;
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
 * Service layer for all business actions regarding movieType entity.
 */
@LocalBean
@Stateless
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
    @RolesAllowed(UserRoles.USER)
    public Optional<MovieType> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available movieTypes
     */
    @RolesAllowed(UserRoles.USER)
    public List<MovieType> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new movieType in the data store.
     *
     * @param movieType new movieType to be saved
     */
    @RolesAllowed(UserRoles.ADMIN)
    public void create(MovieType movieType) {
        repository.create(movieType);
    }

    /**
     * Updates movieType in the data store.
     *
     * @param movieType movieType to be updated
     */
    @RolesAllowed(UserRoles.ADMIN)
    public void update(MovieType movieType) {
        repository.update(movieType);
    }
    /**
     * Deletes movieType from the data store.
     *
     * @param id movieType's id to be deleted
     */
    @RolesAllowed(UserRoles.ADMIN)
    public void delete(UUID id) {
        repository.delete(repository.find(id).orElseThrow());
    }


}