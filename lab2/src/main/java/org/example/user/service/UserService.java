package org.example.user.service;

import jakarta.servlet.ServletContext;
import org.example.crypto.component.Pbkdf2PasswordHash;
import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;
import org.example.controller.servlet.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import jakarta.servlet.ServletContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.io.*;

/**
 * Service layer for all business actions regarding user entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {

    /**
     * Repository for user entity.
     */
    private final UserRepository repository;

    /**
     * Hash mechanism used for storing users' passwords.
     */
    private final Pbkdf2PasswordHash passwordHash;

    private final UserAvatarService avatarService;

    private final Path avatarPath;

    /**
     * @param repository   repository for character entity
     * @param passwordHash hash mechanism used for storing users' passwords
     */
    @Inject
    public UserService(UserRepository repository, Pbkdf2PasswordHash passwordHash, UserAvatarService avatarService, ServletContext servletContext) {
        this.repository = repository;
        this.passwordHash = passwordHash;
        this.avatarService = avatarService;
        this.avatarPath = Paths.get(servletContext.getInitParameter("avatars_path"));

    }

    /**
     * @param id user's id
     * @return container (can be empty) with user
     */
    public Optional<User> find(UUID id) {
        return repository.find(id);
    }

    /**
     * Seeks for single user using login and password. Can be used in authentication module.
     *
     * @param login user's login
     * @return container (can be empty) with user
     */
    public Optional<User> find(String login) {
        return repository.findByLogin(login);
    }

    /**
     * @return all available users
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Saves new user. Password is hashed using configured hash algorithm.
     *
     * @param user new user to be saved
     */
    public void create(User user) {
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        repository.create(user);
    }

    /**
     * @param login    user's login
     * @param password user's password
     * @return true if provided login and password are correct
     */
    public boolean verify(String login, String password) {
        return find(login)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }

    public byte[] getAvatar(UUID id) {
        Optional<User> user = repository.find(id);
        if (user.isPresent()) {
            try {
                return avatarService.getAvatar(user.get());
            } catch (IOException e) {
                throw new NotFoundException(e);
            }
        } else {
            throw new NotFoundException("User not found");
        }
    }

    /**
     * Updates portrait of the user.
     *
     * @param id user's id
     * @param is input stream containing new portrait
     */
    public void updateAvatar(UUID id, InputStream is) {
        repository.find(id).ifPresent(user -> {
            try {
                Path path = avatarPath.resolve(id.toString() + ".png");
                Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
                user.setAvatarPath(path.toString());
                //userAvatarService.saveAvatar(user, path.toString());
                repository.update(user);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    /**
     * Deletes portrait of the user.
     *
     * @param id user's id
     */
    public void deleteAvatar(UUID id) {
        repository.find(id).ifPresentOrElse(user -> {
            try {
                Path path = avatarPath.resolve(id.toString() + ".png");
                if (!Files.exists(path)) {
                    throw new NotFoundException("Avatar not found");
                }
                Files.delete(path);
                user.setAvatarPath(null);
                repository.update(user);
            } catch (IOException e) {
                throw new RuntimeException("Error deleting avatar", e);
            }
        }, () -> {
            throw new NotFoundException("User not found");
        });
    }

}
