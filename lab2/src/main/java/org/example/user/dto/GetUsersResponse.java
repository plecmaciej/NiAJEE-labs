package org.example.user.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * GET characters response. Contains list of available characters. Can be used to list particular user's characters as
 * well as all characters in the game.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUsersResponse {

    /**
     * Represents single character in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User {

        /**
         * Unique id identifying character.
         */
        //private UUID id;

        private  String name;

        private String surname;
        /**
         * Name of the character.
         */
        private String login;

    }

    /**
     * Name of the selected characters.
     */
    @Singular
    private List<User> users;

}
