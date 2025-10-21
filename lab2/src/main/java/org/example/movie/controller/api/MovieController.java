package org.example.movie.controller.api;

import org.example.movie.dto.GetMovieResponse;
import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.dto.function.PatchMovieRequest;
import org.example.movie.dto.function.PutMovieRequest;

import java.util.UUID;


/**
 * Controller for managing collections Movies' representations.
 */
public interface MovieController { //TODO: Implement patch, put and portrait methods

    /**
     * @return all Movies representation
     */
    GetMoviesResponse getMovies();

    /**
     * @param id Movie's id
     * @return Movie representation
     */
    GetMovieResponse getMovie(UUID id);

//    /**
//     * @param request new Movie representation
//     */
//    void putMovie(UUID id, PutMovieRequest request);
//
//    /**
//     * @param id      Movie's id
//     * @param request Movie update representation
//     */
//    void patchMovie(UUID id, PatchMovieRequest request);

    /**
     * @param id Movie's id
     */
    void deleteMovie(UUID id);

//    /**
//     * @param id Movie's id
//     * @return Movie's photo
//     */
//    byte[] getMoviePhoto(UUID id);

}
