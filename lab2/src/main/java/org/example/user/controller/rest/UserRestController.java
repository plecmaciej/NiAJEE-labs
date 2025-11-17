package org.example.user.controller.rest;

import org.example.component.DtoFunctionFactory;
import org.example.user.controller.api.UserController;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.PutUserRequest;
import org.example.user.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class UserRestController implements UserController {

    /**
     * User service.
     */
    private UserService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * Allows to create {@link UriBuilder} based on current request.
     */
    private final UriInfo uriInfo;

    /**
     * Current HTTP Servlet response.
     */
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    /**
     * @param factory factory producing functions for conversion between DTO and entities
     * @param uriInfo allows to create {@link UriBuilder} based on current request
     */
    @Inject
    public UserRestController(
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setService(UserService service) {
        this.service = service;
    }


    @Override
    public GetUsersResponse getUsers() {
        return factory.usersToResponse().apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID id) {
        return service.find(id)
                .map(factory.userToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void registerUser(UUID id, PutUserRequest request) {
        try {
            service.create(factory.requestToUser().apply(id, request));
            response.setHeader("Location", uriInfo.getAbsolutePathBuilder()
                    .path(UserController.class, "getUser")
                    .build(id)
                    .toString());
            throw  new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }

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