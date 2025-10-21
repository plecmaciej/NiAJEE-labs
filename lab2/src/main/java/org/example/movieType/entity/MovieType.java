package org.example.movieType.entity;

import org.example.movie.entity.Movie;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieType implements Serializable {

    /**
     * Unique id (primary key).
     */
    private UUID id;

    /**
     * Name of the Movie type.
     */
    private String typeName;

    /**
     * Description of the Movie type.
     */
    private String description;

    /**
     * Riding position of the Movie type.
     */
    private EnumMovieType.Age age;

    /**
     * List of Movies of this type.
     */
    List<Movie> Movies;

}
