package org.example.movieType.dto;

import org.example.movieType.entity.EnumMovieType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PUT movieType request. Contains only fields that can be set up byt the user while creating a new movieType.How
 * movieType is described is defined in {@link org.example.movieType.dto.GetMovieTypesResponse.MovieType} and
 * {@link org.example.movieType.entity.MovieType} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMovieTypeRequest {

    /**
     * Name of the movie type.
     */
    private String typeName;

    /**
     * Description of the movie type.
     */
    private String description;

    /**
     * Riding position of the movie type.
     */
    private EnumMovieType.Age age;


}
