package org.example.user.controller.api;

import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;


import java.io.InputStream;
import java.util.UUID;


public interface UserController {


    GetUsersResponse getUsers();
    GetUserResponse getUser(UUID uuid);

    byte[] getUserAvatar(UUID id);

    /**
     * @param id user's id
     * @param avatar user's new avatar
     */
    void putUserAvatar(UUID id, InputStream avatar);

    /**
     * @param id user's id
     */
    void deleteUserAvatar(UUID id);

}
