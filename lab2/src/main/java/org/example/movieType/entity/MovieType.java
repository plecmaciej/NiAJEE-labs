package org.example.movieType.entity;

import org.example.movie.entity.Movie;
import jakarta.persistence.*;
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
@Entity
@Table(name = "movie_types")
public class MovieType implements Serializable {

    /**
     * Unique id (primary key).
     */
    @Id
    private UUID id;

    /**
     * Name of the Movie type.
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * Description of the Movie type.
     */
    private String description;

    /**
     * Riding position of the Movie type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "age")
    private EnumMovieType.Age age;

    /**
     * List of Movies of this type.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "movieType", cascade = CascadeType.REMOVE)
    List<Movie> Movies;

}
