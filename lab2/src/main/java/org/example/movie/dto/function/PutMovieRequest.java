package org.example.movie.dto.function;

import org.example.movie.entity.EnumMovie;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMovieRequest {
    /**
     * Movie's name.
     */
    private String title;


    /**
     * Movie's color.
     */
    private EnumMovie.Status status;


    /**
     * Movie's production date.
     */
    private LocalDate releaseDate;

    /**
     * Movie's price.
     */
    private double price;

    /**
     * Identifier of the Movie's type
     */
    private UUID MovieType;
}
