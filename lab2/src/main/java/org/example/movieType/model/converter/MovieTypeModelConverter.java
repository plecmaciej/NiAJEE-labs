package org.example.movieType.model.converter;

import org.example.component.ModelFunctionFactory;
import org.example.movieType.entity.MovieType;
import org.example.movieType.model.MovieTypeModel;
import org.example.movieType.service.MovieTypeService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(forClass = MovieTypeModel.class, managed = true)
public class MovieTypeModelConverter implements Converter<MovieTypeModel> {

    private final MovieTypeService service;

    private final ModelFunctionFactory factory;

    @Inject
    public MovieTypeModelConverter(MovieTypeService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public MovieTypeModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<MovieType> movieType = service.find(UUID.fromString(value));
        return movieType.map(factory.movieTypeToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, MovieTypeModel value) {
        return value == null ? "" : value.getId().toString();
    }

}