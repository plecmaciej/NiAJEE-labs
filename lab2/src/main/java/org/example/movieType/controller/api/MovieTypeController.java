package org.example.movieType.controller.api;

import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.dto.GetMovieTypesResponse;

import java.util.UUID;

/**
 * Controller for managing collections MovieTypes' representations.
 */
public interface MovieTypeController {

    /**
     * @return all MovieTypes representation
     */
    GetMovieTypesResponse getMovieTypes();

    /**
     * @param id MovieType's id
     * @return MovieType representation
     */
    GetMovieTypeResponse getMovieType(UUID id);
}
