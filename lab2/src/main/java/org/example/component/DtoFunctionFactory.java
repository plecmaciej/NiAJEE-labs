package org.example.component;

import org.example.movie.dto.function.MovieToResponseFunction;
import org.example.movie.dto.function.MoviesToResponseFunction;
import org.example.movie.dto.GetMoviesResponse;
import org.example.movie.dto.GetMovieResponse;
import org.example.movieType.dto.function.MovieTypeToResponseFunction;
import org.example.movieType.dto.GetMovieTypeResponse;
import org.example.movieType.dto.GetMovieTypesResponse;
import org.example.movieType.dto.function.MovieTypesToResponseFunction;
import org.example.movieType.entity.MovieType;
import org.example.user.dto.function.UserToResponseFunction;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.function.UsersToResponseFunction;
import org.example.movie.dto.GetMovieResponse;
import org.example.movie.dto.GetMoviesResponse;
import org.example.user.entity.User;
import org.example.movie.entity.Movie;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class DtoFunctionFactory {

    /**
     * Returns a function to convert a single {@link User} to {@link GetUserResponse}.
     *
     * @return UserToResponseFunction instance
     */
    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link User} to {@link GetUsersResponse}.
     *
     * @return UsersToResponseFunction instance
     */
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }
    
    

    public MovieTypeToResponseFunction MovieTypeToResponse() {
        return new MovieTypeToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link MovieType} to {@link GetMovieTypesResponse}.
     */
    public MovieTypesToResponseFunction MovieTypesToResponse() {
        return new MovieTypesToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link Movie} to {@link GetMovieResponse}.
     *
     * @return MovieToResponseFunction instance
     */
    public MovieToResponseFunction MovieToResponse() {
        return new MovieToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Movie} to {@link GetMoviesResponse}.
     * @return MoviesToResponseFunction instance
     */
    public MoviesToResponseFunction MoviesToResponse() {
        return new MoviesToResponseFunction();
    }

}
