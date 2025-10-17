package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.component.DtoFunctionFactory;
import org.example.user.controller.simple.UserSimpleController;
import org.example.user.service.UserService;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of controllers and puts them in
 * the application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        event.getServletContext().setAttribute("userController", new UserSimpleController(
                userService,
                new DtoFunctionFactory()
        ));
    }

}
