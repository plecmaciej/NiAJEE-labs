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

import java.util.UUID;


/**
 * GET Movie type response. Described details about selected Movie type. Can be used to present description while
 * character creation or on character's stat page. How Movie type is described is defined in
 * {@link com.jelinski.niajee.MovieType.entity.MovieType}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMovieTypeResponse {

    /**
     * Unique id identifying Movie type.
     */
    private UUID id;

    /**
     * Name of the Movie type.
     */
    private String name;

    /**
     * Description of the Movie type.
     */
    private String description;

    /**
     * Riding position of the Movie type.
     */
    private EnumMovieType.Age age;
}
