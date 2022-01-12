package rest.pas.restpas.Controllers;

import rest.pas.restpas.Model.DTO.UserDTO;
import rest.pas.restpas.Model.Exceptions.AccessLevelException;
import rest.pas.restpas.Model.Exceptions.UserException;
import rest.pas.restpas.Services.UserService;

import javax.inject.Inject;
import javax.validation.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/user")
public class UserController {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Inject
    UserService userService;

    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("login") String login) {
        try {
            return Response.ok().entity(userService.getUser(login)).build();
        } catch (UserException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok().entity(userService.getUsers()).build();
    }

    @GET
    @Path("/part/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPartLogin(@PathParam("login") String login) {
        return Response.ok().entity(userService.getByPartLogin(login)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO){
        Set<ConstraintViolation<UserDTO>> cos = validator.validate(userDTO);
        if(cos.isEmpty()){
            try {
                userService.addUser(userDTO);
                return Response.status(201).build();
            } catch (AccessLevelException | UserException | NullPointerException e){
                return Response.status(409).build();
            }
        }
        return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO){
        Set<ConstraintViolation<UserDTO>> cos = validator.validate(userDTO);
        if(cos.isEmpty()){
            try {
                userService.updateUser(userDTO);
                return Response.status(201).build();
            } catch (UserException e){
                return Response.status(409).build();
            }
        }
        return Response.status(400).build();
    }

    @GET
    @Path("/changeActivity/{login}")
    public Response changeUserActivity(@PathParam("login") String login){
        try {
            userService.changeUserActivity(login);
            Response.ok().build();
        } catch (UserException e){
            return Response.status(404).build();
        }

        return Response.status(400).build();
    }

}