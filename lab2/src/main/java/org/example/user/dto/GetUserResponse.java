package org.example.user.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * GET character response. It contains all field that can be presented (but not necessarily changed) to the used. How
 * character is described is defined in {@link pl.edu.pg.eti.kask.rpg.character.dto.GetCharactersResponse.Character}
 * and {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    private UUID id;

    /**
     * User's username (login)
     */
    private String login;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's surname.
     */
    private String surname;

    /**
     * User's birthdate.
     */
    private LocalDate birthDate;

    /**
     * User's email.
     */
    private String email;


}
