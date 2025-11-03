package org.example.movieType.controller.api;

import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.dto.GetMovieTypesResponse;
import org.example.movieType.dto.PatchMovieTypeRequest;
import org.example.movieType.dto.PutMovieTypeRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

/**
 * Controller for managing collections movieTypes' representations.
 */
@Path("")
public interface MovieTypeController {

    /**
     * @return all movieTypes representation
     */
    @GET
    @Path("/movieTypes")
    @Produces(MediaType.APPLICATION_JSON)
    GetMovieTypesResponse getMovieTypes();

    /**
     * @param id movieType's id
     * @return movieType representation
     */
    @GET
    @Path("/movieTypes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetMovieTypeResponse getMovieType(@PathParam("id") UUID id);

    /**
     * @param id movieType's id
     */
    @DELETE
    @Path("/movieTypes/{id}")
    void deleteMovieType(@PathParam("id") UUID id);

    /**
     * @param id movieType's id
     * @param request new movie type representation
     */
    @PUT
    @Path("/movieTypes/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putMovieType(@PathParam("id") UUID id, PutMovieTypeRequest request);

    /**
     * @param id movieType's id
     * @param request movie type update representation
     */
    @PATCH
    @Path("/movieTypes/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchMovieType(@PathParam("id") UUID id, PatchMovieTypeRequest request);

}
