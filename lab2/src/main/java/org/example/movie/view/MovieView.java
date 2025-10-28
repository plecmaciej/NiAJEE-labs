package org.example.movie.view;

import org.example.component.ModelFunctionFactory;
import org.example.movie.entity.Movie;
import org.example.movie.model.MovieModel;
import org.example.movie.service.MovieService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single movie information.
 */
@ViewScoped
@Named
public class MovieView implements Serializable {

    /**
     * Service for managing movies.
     */
    private final MovieService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Movie id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Movie exposed to the view.
     */
    @Getter
    private MovieModel movie;

    /**
     * @param service service for managing movies
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public MovieView(MovieService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Movie> movie = service.find(id);
        if (movie.isPresent()) {
            this.movie = factory.movieToModel().apply(movie.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
        }
    }

}