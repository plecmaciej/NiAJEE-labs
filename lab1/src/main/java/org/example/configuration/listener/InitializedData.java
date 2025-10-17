package org.example.configuration.listener;

import org.example.user.entity.User;
import org.example.user.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Listener started automatically on servlet context initialization.
 * Initializes the application with default example data.
 */
public class InitializedData implements ServletContextListener {

    /**
     * User service.
     */
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
        init();
    }

    /**
     * Initializes example users in the application.
     */
    @SneakyThrows
    private void init() {
        // Default admin user
        User admin = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("admin")
                .name("System")
                .surname("Admin")
                .birthDate(LocalDate.of(1990, 10, 21))
                .email("admin@example.com")
                .password("adminadmin")
                .build();

        // Example users
        User emily = User.builder()
                .id(UUID.fromString("7f2a9f17-4a9c-4b4c-8b1b-6d0e3b8f9c42"))
                .login("emily")
                .name("Emily")
                .surname("Stone")
                .birthDate(LocalDate.of(1998, 5, 14))
                .email("emily.stone@example.com")
                .password("useruser")
                .build();

        User daniel = User.builder()
                .id(UUID.fromString("2c3b6c62-8a34-44b5-b69a-fcb5b1a47d9a"))
                .login("daniel")
                .name("Daniel")
                .surname("Frost")
                .birthDate(LocalDate.of(1995, 9, 23))
                .email("daniel.frost@example.com")
                .password("useruser")
                .build();

        User sophia = User.builder()
                .id(UUID.fromString("e6a8740e-4a39-4f63-b63e-96a7cfe0b981"))
                .login("sophia")
                .name("Sophia")
                .surname("Miller")
                .birthDate(LocalDate.of(2000, 2, 11))
                .email("sophia.miller@example.com")
                .password("useruser")
                .build();

        User michael = User.builder()
                .id(UUID.fromString("b5e92a39-5578-4e67-9d46-2bbac8f8a78a"))
                .login("michael")
                .name("Michael")
                .surname("Reed")
                .birthDate(LocalDate.of(1997, 12, 3))
                .email("michael.reed@example.com")
                .password("useruser")
                .build();

        // Save users
        userService.create(admin);
        userService.updateAvatar(admin.getId(), this.getClass().getResourceAsStream("/admin.png"));
        userService.create(emily);
        userService.create(daniel);
        userService.create(sophia);
        userService.create(michael);
    }

    /**
     * Reads a resource file as a byte array.
     *
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }

}
