package org.example.movieType.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents list of movie types to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieTypeModel  {

    /**
     * Represents single movie type in list.
     */


        /**
         * Unique id identifying movie type.
         */
    private UUID id;

        /**
         * Name of the movie type.
         */
    private String typeName;





}