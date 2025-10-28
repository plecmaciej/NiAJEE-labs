package org.example.movieType.model.function;

import org.example.movieType.entity.MovieType;
import org.example.movieType.model.MovieTypesModel;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List<MovieType>} to {@link MovieTypesModel}.
 */
public class MovieTypesToModelFunction implements Function<List<MovieType>, MovieTypesModel> {
    @Override
    public MovieTypesModel apply(List<MovieType> movieTypes) {
        return MovieTypesModel.builder()
                .movieTypes(movieTypes.stream()
                        .map(movieType -> MovieTypesModel.MovieType.builder()
                                .id(movieType.getId())
                                .typeName(movieType.getTypeName())
                                .build())
                        .toList())
                .build();
    }
}