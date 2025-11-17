package org.example.movie.view;

import org.example.component.ModelFunctionFactory;
import org.example.movie.entity.EnumMovie;
import org.example.movie.entity.Movie;
import org.example.movie.model.MovieEditModel;
import org.example.movie.service.MovieService;
import jakarta.ejb.EJB;
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
 * View bean for rendering single movie edit form.
 */
@ViewScoped
@Named
public class MovieEdit implements Serializable {

    /**
     * Service for managing movies.
     */
    private MovieService service;

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
     * Movie color.
     */
    @Setter
    @Getter
    private EnumMovie.Status[] statuses;

    /**
     * Movie exposed to the view.
     */
    @Getter
    private MovieEditModel movie;

    /**
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public MovieEdit(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(MovieService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        this.statuses = EnumMovie.Status.values();
        Optional<Movie> movie = service.find(id);
        if (movie.isPresent()) {
            this.movie = factory.movieToEditModel().apply(movie.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        service.update(factory.updateMovie().apply(service.find(id).orElseThrow(), movie));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}