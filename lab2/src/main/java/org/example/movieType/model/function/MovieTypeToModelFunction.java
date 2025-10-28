package org.example.movieType.model.function;

import org.example.movieType.entity.MovieType;
import org.example.movieType.model.MovieTypeModel;

import java.util.function.Function;

/**
 * Function for converting {@link MovieType} to {@link MovieTypeModel}.
 */
public class MovieTypeToModelFunction implements Function<MovieType, MovieTypeModel> {
    @Override
    public MovieTypeModel apply(MovieType movieType) {
        return MovieTypeModel.builder()
                .id(movieType.getId())
                .typeName(movieType.getTypeName())
                .build();
    }
}