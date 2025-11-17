package org.example.movie.view;

import org.example.component.ModelFunctionFactory;
import org.example.movie.entity.EnumMovie;
import org.example.movie.model.MoviesModel;
import org.example.movie.service.MovieService;
import org.example.movieType.entity.MovieType;
import org.example.movieType.entity.EnumMovieType;
import org.example.movieType.service.MovieTypeService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering list of movies.
 */
@RequestScoped
@Named
public class MovieList {

    /**
     * Service for managing movies.
     */
    private MovieService movieService;

    /**
     * Service for managing movie types.
     */
    private MovieTypeService movieTypeService;

    /**
     * Movies list exposed to the view.
     */
    private MoviesModel movies;

    /**
     * Id of the selected movie type.
     */
    @Getter
    @Setter
    private UUID movieTypeId;

    /**
     * Description of the selected movie type.
     */
    @Getter
    @Setter
    private String movieTypeDescription;

    @Getter
    @Setter
    private EnumMovieType.Age[] movieTypeAges;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;


    /**
     * @param factory factory producing functions for conversion between models and entities
     * */
    @Inject
    public MovieList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @EJB
    public void setMovieTypeService(MovieTypeService movieTypeService) {
        this.movieTypeService = movieTypeService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all movies
     */
    public MoviesModel getMovies() {
        if (movies == null) {
            if (movieTypeId != null) {
                Optional<MovieType> movieType = movieTypeService.find(movieTypeId);
                movies = factory.moviesToModel().apply(movieService.findAll(movieType.get()));
            } else {
                movies = factory.moviesToModel().apply(movieService.findAll());
            }
        }
        return movies;
    }

    public void init() {
        if (movieTypeId == null) {
            movieTypeDescription = "All movies";
            movieTypeAges = EnumMovieType.Age.values();

        } else {
            Optional<MovieType> movieType = movieTypeService.find(movieTypeId);
            if (movieType.isPresent()) {
                movieTypeDescription = movieType.get().getDescription();
                movieTypeAges = EnumMovieType.Age.values();
            } else {
                movieTypeDescription = "All movies";
            }
        }
    }

    /**
     * Action for clicking delete action.
     *
     * @param movie movie to be removed
     * @return navigation case to list_movies
     */
    public String deleteAction(MoviesModel.Movie movie) {
        System.out.println(movieTypeId);
        movieService.delete(movie.getId());
        if (movieTypeId == null) {
            return "movie_list?faces-redirect=true";
        } else {
            return "movie_list?faces-redirect=true&movie-type-id=" + movieTypeId;
        }
    }
}