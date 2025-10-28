package org.example.component;

import org.example.movie.entity.Movie;
//import org.example.movie.model.function.MovieToEditModelFunction;
import org.example.movie.model.function.MovieToModelFunction;
//import org.example.movie.model.MotorcycleEditModel;
//import org.example.movie.model.function.UpdateMotorcycleWithModelFunction;
import org.example.movieType.entity.MovieType;
import org.example.movie.model.function.MoviesToModelFunction;
import org.example.movie.model.MoviesModel;
import org.example.movieType.model.function.MovieTypesToModelFunction;
import org.example.movieType.model.MovieTypesModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class ModelFunctionFactory {

    /**
     * Returns a function to convert a list of {@link Movie} to {@link MoviesModel}.
     *
     * @return new instance
     */
    public MoviesToModelFunction moviesToModel() {
        return new MoviesToModelFunction();
    }

    /**
     * Returns a function to convert a list of {@link MovieType} to {@link MovieTypesModel}.
     *
     * @return new instance
     */
    public MovieTypesToModelFunction movieTypesToModel() {
        return new MovieTypesToModelFunction();
    }

    /**

     *
     * @return new instance
     */
    public MovieToModelFunction movieToModel() {
        return new MovieToModelFunction();
    }

    /**
     * Returns a function to convert a single {@link Movie} to {@link MovieEditModel}.
     *
     * @return new instance
     */
    //public MovieToEditModelFunction MovieToEditModel() {
    //    return new MovieToEditModelFunction();
    //}

    /**
     * Returns a function to convert a single {@link MovieEditModel} to {@link Movie}.
     *
     * @return UpdateMovieFunction instance
     */
    //public UpdateMovieWithModelFunction updateMovie() {
    //    return new UpdateMovieWithModelFunction();
    //}

}
