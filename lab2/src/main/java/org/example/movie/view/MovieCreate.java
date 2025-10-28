package org.example.movie.view;

import org.example.component.ModelFunctionFactory;
import org.example.movie.entity.EnumMovie;
import org.example.movie.model.MovieCreateModel;
import org.example.movieType.model.MovieTypeModel;
import org.example.movie.service.MovieService;
import org.example.movieType.service.MovieTypeService;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single movie create form. In order to use single bean, conversation scope is used.
 */
@ViewScoped
@Named
@NoArgsConstructor(force = true)
public class MovieCreate implements Serializable {

    /**
     * Service for managing movies.
     */
    private final MovieService movieService;

    /**
     * Service for managing movieTypes.
     */
    private final MovieTypeService movieTypeService;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Movie exposed to the view.
     */
    @Getter
    private MovieCreateModel movie;

    /**
     * Available movieTypes.
     */
    @Getter
    private List<MovieTypeModel> movieTypes;

    /**
     * Movie color.
     */
    @Setter
    @Getter
    private EnumMovie.Status[] statuses;


    /**
     * @param movieService     service for managing movies
     * @param movieTypeService service for managing movieTypes
     * @param factory               factory producing functions for conversion between models and entities
     */
    @Inject
    public MovieCreate(
            MovieService movieService,
            MovieTypeService movieTypeService,
            ModelFunctionFactory factory) {
        this.movieService = movieService;
        this.movieTypeService = movieTypeService;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered.
     */
    public void init() {
        this.statuses = EnumMovie.Status.values();
        movieTypes = movieTypeService.findAll().stream()
                .map(factory.movieTypeToModel())
                .collect(Collectors.toList());
        movie = MovieCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }

    /**
     * Stores new movie.
     *
     * @return movies list navigation case
     */
    public String saveAction() {
        movieService.create(factory.modelToMovie().apply(movie));
        return "/movie/movie_list.xhtml?faces-redirect=true";
    }

    /**
     * Cancels movie creation process.
     *
     * @return characters list navigation case
     */
    public String cancelAction() {
        return "/movie/movie_list.xhtml?faces-redirect=true";
    }

}