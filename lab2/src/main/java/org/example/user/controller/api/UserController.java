package org.example.user.controller.api;

import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.PutUserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.InputStream;
import java.util.UUID;

@Path("")
public interface UserController {

    /**
     * @return all users representation
     */
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    GetUsersResponse getUsers();

    /**
     * @param id user's id
     * @return user representation
     */
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUserResponse getUser(@PathParam("id") UUID id);

    /**
     * @param id user's id
     */
    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void registerUser(@PathParam("id") UUID id, PutUserRequest request);

    /**
     * @param id user's id
     * @return user's avatar
     */
    @GET
    @Path("/users/{id}/avatar")
    @Produces("image/png")
    byte[] getUserAvatar(@PathParam("id") UUID id);

    /**
     * @param id user's id
     * @param avatar user's new avatar
     */
    @PUT
    @Path("/users/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    void putUserAvatar(
            @PathParam("id") UUID id,
            @SuppressWarnings("RestParamTypeInspection")@FormParam("avatar") InputStream avatar
    );

    /**
     * @param id user's id
     */
    @DELETE
    @Path("/users/{id}/avatar")
    void deleteUserAvatar(@PathParam("id") UUID id);

}