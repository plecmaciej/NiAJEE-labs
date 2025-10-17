package org.example.configuration.listener;

import org.example.crypto.component.Pbkdf2PasswordHash;
import org.example.datastore.component.DataStore;
import org.example.user.repository.api.UserRepository;
import org.example.user.repository.memory.UserInMemoryRepository;
import org.example.user.service.UserAvatarService;
import org.example.user.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of services (business layer) and
 * puts them in the application (servlet) context.
 */
public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");
        String filePathString = event.getServletContext().getInitParameter("avatars_path");
        Path filePath = Paths.get(filePathString);

        UserRepository userRepository = new UserInMemoryRepository(dataSource);
        UserAvatarService avatarService = new UserAvatarService(filePathString);

        event.getServletContext().setAttribute("userService", new UserService(userRepository, new Pbkdf2PasswordHash(), avatarService, filePath));
    }

}