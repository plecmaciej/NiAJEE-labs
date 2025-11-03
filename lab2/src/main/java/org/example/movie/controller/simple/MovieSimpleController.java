package org.example.movie.controller.simple;

import org.example.component.DtoFunctionFactory;
import org.example.movie.controller.api.MovieController;
import org.example.movie.dto.GetMovieResponse;
import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.dto.function.PutMovieRequest;
import org.example.movie.service.MovieService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

/**
 * Simple implementation of {@link MovieController}.
 */
@RequestScoped
public class MovieSimpleController implements MovieController {  //TODO: Implement patch and put methods

    /**
     * Service layer for all business actions regarding Movie entity.
     */
    private final MovieService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service character service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public MovieSimpleController(MovieService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return factory.MoviesToResponse().apply(service.findAll());
    }

    @Override
    public GetMovieResponse getMovie(UUID id) {
        return service.find(id)
                .map(factory.MovieToResponse())
                .orElseThrow(NotFoundException::new);
    }

//    @Override
//    public void PutMovie(UUID id, PutMovieRequest request) {
//        try {
//            service.create(factory.requestToMovie().apply(id, request));
//        } catch (IllegalArgumentException e) {
//            throw new BadRequestException(e);
//        }
//    }

//    @Override
//    public void patchMovie(UUID id, PutMovieRequest request) {
//        service.find(id).ifPresentOrElse(
//                Movie -> service.update(factory.updateMovie().apply(Movie, request)),
//                () -> {
//                    throw new NotFoundException();
//                }
//        );
//    }

    @Override
    public void deleteMovie(UUID id) {
        service.find(id).ifPresentOrElse(
                Movie -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
