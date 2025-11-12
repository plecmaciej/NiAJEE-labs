package org.example.movie.controller.rest;

import org.example.component.DtoFunctionFactory;
import org.example.movie.controller.api.MovieController;
import org.example.movie.dto.GetMovieResponse;
import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.dto.PatchMovieRequest;
import org.example.movie.dto.PutMovieRequest;
import org.example.movie.entity.Movie;
import org.example.movie.service.MovieService;
import org.example.movieType.entity.MovieType;
import org.example.movieType.service.MovieTypeService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.util.UUID;
import java.util.logging.Level;

/**
 * Simple framework agnostic implementation of controller.
 */
@Path("")
@Log
public class MovieRestController implements MovieController {

    /**
     * Service layer for all business actions regarding movie entity.
     */
    private final MovieService movieService;

    /**
     * Service layer for all business actions regarding movieType entity.
     */
    private final MovieTypeService movieTypeService;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * Allows to create {@link UriBuilder} based on current request.
     */
    private final UriInfo uriInfo;

    /**
     * Current HTTP Servlet response.
     */
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    /**
     * @param movieService character service
     * @param factory factory producing functions for conversion between DTO and entities
     * @param uriInfo allows to create {@link UriBuilder} based on current request
     */
    @Inject
    public MovieRestController(
            MovieService movieService,
            MovieTypeService movieTypeService,
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.movieService = movieService;
        this.movieTypeService = movieTypeService;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return factory.MoviesToResponse().apply(movieService.findAll());
    }

    @Override
    public GetMoviesResponse getMovieTypeMovies(UUID id) {
        return movieService.findAllByMovieType(id)
                .map(factory.MoviesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetMovieResponse getMovie(UUID id) {
        return movieService.find(id)
                .map(factory.MovieToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetMovieResponse getMovieTypeMovie(UUID typeId, UUID id) {
        try {
            movieTypeService.find(typeId).orElseThrow(NotFoundException::new);
            return movieService.find(id)
                    .map(factory.MovieToResponse())
                    .orElseThrow(NotFoundException::new);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    @SneakyThrows
    public void putMovie(UUID typeId, UUID id, PutMovieRequest request) {
        try {
            Movie movie = factory.requestToMovie().apply(id, request);

            MovieType movieType = movieTypeService.find(typeId).orElseThrow(NotFoundException::new);
            movie.setMovieType(movieType);
            movieType.getMovies().add(movie);
            movieTypeService.update(movieType);
//            movieService.create(movie);
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(MovieController.class, "getMovie")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchMovie(UUID typeId, UUID id, PatchMovieRequest request) {
        movieTypeService.find(typeId).orElseThrow(NotFoundException::new);
        movieService.find(id).ifPresentOrElse(
                movie -> movieService.update(factory.updateMovie().apply(movie, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteMovie(UUID id) {
        movieService.find(id).ifPresentOrElse(
                movie -> movieService.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}