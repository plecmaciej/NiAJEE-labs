package org.example.movie.controller.api;

import org.example.movie.dto.GetMovieResponse;
import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.dto.PatchMovieRequest;
import org.example.movie.dto.PutMovieRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;


/**
 * Controller for managing collections movies' representations.
 */
@Path("")
public interface MovieController {

    /**
     * @return all movies representation
     */
    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    GetMoviesResponse getMovies();

    /**
     * @param id movieType's id
     * @return movies representation
     */
    @GET
    @Path("/movieTypes/{id}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    GetMoviesResponse getMovieTypeMovies(@PathParam("id") UUID id);

    /**
     * @param id movie's id
     * @return movie representation
     */
    @GET
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetMovieResponse getMovie(@PathParam("id") UUID id);

    /**
     * @param typeId movieType's id
     * @param id movie's id
     * @return movies representation
     */
    @GET
    @Path("/movieTypes/{typeId}/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetMovieResponse getMovieTypeMovie(@PathParam("typeId") UUID typeId ,@PathParam("id") UUID id);

    /**
     * @param id      movie's id
     * @param request new movie representation
     */
    @PUT
    @Path("/movieTypes/{typeId}/movies/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putMovie(@PathParam("typeId") UUID typeId, @PathParam("id") UUID id, PutMovieRequest request);

    /**
     * @param id      movie's id
     * @param request movie update representation
     */
    @PATCH
    @Path("/movieTypes/{typeId}/movies/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchMovie(@PathParam("typeId") UUID typeId, @PathParam("id") UUID id, PatchMovieRequest request);

    /**
     * @param id movie's id
     */
    @DELETE
    @Path("/movies/{id}")
    void deleteMovie(@PathParam("id") UUID id);
}
