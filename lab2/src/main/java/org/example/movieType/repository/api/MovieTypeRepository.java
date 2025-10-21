package org.example.movieType.repository.api;

import org.example.movieType.entity.MovieType;
import org.example.repository.api.Repository;

import java.util.UUID;

/**
 * Repository for MovieType entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface MovieTypeRepository extends Repository<MovieType, UUID> {

}
