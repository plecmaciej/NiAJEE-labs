package org.example.movieType.view;

import org.example.component.ModelFunctionFactory;
import org.example.movieType.model.MovieTypesModel;
import org.example.movieType.service.MovieTypeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * View bean for rendering list of movie types.
 */
@RequestScoped
@Named
public class MovieTypeList {

    /**
     * Service for managing movie types.
     */
    private final MovieTypeService service;

    /**
     * Movies list exposed to the view.
     */
    private MovieTypesModel movieTypes;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * @param service movie type service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public MovieTypeList(MovieTypeService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all movie types
     */
    public MovieTypesModel getMovieTypes() {
        if (movieTypes == null) {
            movieTypes = factory.movieTypesToModel().apply(service.findAll());
        }
        return movieTypes;
    }

    /**
     * Action for clicking delete action.
     *
     * @param movieType movie type to be removed
     * @return navigation case to list_movie_types
     */
    public String deleteAction(MovieTypesModel.MovieType movieType) {
        service.delete(movieType.getId());
        return "movieType_list?faces-redirect=true";
    }
}