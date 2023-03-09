package com.waad.rest.controller;

import com.waad.rest.db.Database;
import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.DELETE;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.event.Event;

import com.waad.rest.model.User;
import com.waad.rest.db.Selected;

@Stateless
@Path("/users")
public class UserController {

    @Inject
    private Event<User> userEvent;

    @Inject
    private Event<User> userAsyncEvent;

    @Inject
    @Selected(Selected.Type.DEV) 
    private Database prodDatabase;

    @Inject
    @Selected(Selected.Type.TEST)
    private Database testDatabase; // Instance from producer method

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsers() {

        return Response.ok(testDatabase.getUsers()).build();
    }

    @GET
    @Path("/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("username") String username) {
        Optional<User> optUser = prodDatabase.getUser(username);
        if (optUser.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(optUser.get()).build();
    }

    @POST
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveUser(User user) {
        prodDatabase.saveUser(user);

        userEvent.fire(user); // Evenement propagé d'une manière synchrone

        return Response.ok().build();
    }

    @DELETE
    @Path("/{username}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteUser(User user) {
        prodDatabase.deleteUser(user);

        userAsyncEvent.fireAsync(user); // Evenement propagé d'une manière asynchrone

        return Response.ok().build();
    }
}
