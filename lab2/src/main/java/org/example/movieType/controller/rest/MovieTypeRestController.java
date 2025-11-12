package org.example.movieType.controller.rest;

import org.example.component.DtoFunctionFactory;
import org.example.movieType.controller.api.MovieTypeController;
import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.dto.GetMovieTypesResponse;
import org.example.movieType.dto.PatchMovieTypeRequest;
import org.example.movieType.dto.PutMovieTypeRequest;
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
public class MovieTypeRestController implements MovieTypeController {

    /**
     * Service for movieType entity.
     */
    private final MovieTypeService service;

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
     * @param service service for movieType entity
     * @param factory factory producing functions for conversion between DTO and entities
     * @param uriInfo allows to create {@link UriBuilder} based on current request
     */
    @Inject
    public MovieTypeRestController(
            MovieTypeService service,
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetMovieTypesResponse getMovieTypes() {
        return factory.MovieTypesToResponse().apply(service.findAll());
    }

    @Override
    public GetMovieTypeResponse getMovieType(UUID id) {
        return service.find(id)
                .map(factory.MovieTypeToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @SneakyThrows
    public void putMovieType(UUID id, PutMovieTypeRequest request) {
        try {
            service.create(factory.requestToMovieType().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(MovieTypeController.class, "getMovieType")
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
    public void patchMovieType(UUID id, PatchMovieTypeRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateMovieType().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteMovieType(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
