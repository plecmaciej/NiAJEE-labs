package org.example.movieType.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * PATCH movieType request. Contains all fields that can be updated by the user. How movieType is described is defined
 * in {@link org.example.movieType.dto.GetMovieTypesResponse.MovieType} and
 * {@link org.example.movieType.entity.MovieType} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchMovieTypeRequest {

    /**
     * Name of the movie type.
     */
    private String typeName;

    /**
     * Description of the movie type.
     */
    private String description;

}
