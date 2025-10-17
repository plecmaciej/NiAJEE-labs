package org.example.user.controller.simple;

import org.example.user.controller.api.UserController;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.service.UserService;
import org.example.component.DtoFunctionFactory;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.user.entity.User;

import java.io.InputStream;
import java.util.UUID;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple controller implementation for User entity.
 * Based on CharacterSimpleController.
 */
public class UserSimpleController implements UserController {

    /**
     * User service (business logic layer).
     */
    private final UserService service;

    /**
     * Factory producing conversion functions between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service user service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    public UserSimpleController(UserService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetUsersResponse getUsers() {
        return factory.usersToResponse().apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        // maps single User → GetUserResponse using DTO factory
        return service.find(uuid)
                .map(factory.userToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public byte[] getUserAvatar(UUID id) {
        return service.getAvatar(id);
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
                user -> service.updateAvatar(id, avatar),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteUserAvatar(UUID id) {
        service.find(id).ifPresentOrElse(
                user -> service.deleteAvatar(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
