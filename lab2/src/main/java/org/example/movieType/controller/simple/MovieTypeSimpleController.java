package org.example.movieType.controller.simple;

import org.example.component.DtoFunctionFactory;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.movieType.controller.api.MovieTypeController;
import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.dto.GetMovieTypesResponse;
import org.example.movieType.service.MovieTypeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@RequestScoped
public class MovieTypeSimpleController implements MovieTypeController {

    /**
     * Service for MovieType entity.
     */
    private final MovieTypeService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service service for MovieType entity
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public MovieTypeSimpleController(MovieTypeService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
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

}
