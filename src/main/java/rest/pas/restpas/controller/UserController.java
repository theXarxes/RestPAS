package rest.pas.restpas.controller;

import rest.pas.restpas.model.DTO.userDTO.PUserDTO;
import rest.pas.restpas.model.DTO.userDTO.UserDTO;
import rest.pas.restpas.model.exception.AccessLevelException;
import rest.pas.restpas.model.exception.UserException;
import rest.pas.restpas.service.UserService;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok().entity(userService.getUsers()).build();
    }

    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByLogin(@PathParam("login") String login) {
        try {
            return Response.ok().entity(userService.getUser(login)).build();
        } catch (UserException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/part/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPartialLogin(@PathParam("login") String login) {
        return Response.ok().entity(userService.getByPartLogin(login)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAdminUser(PUserDTO userDTO){
        Set<ConstraintViolation<PUserDTO>> cos = validator.validate(userDTO);
        if(cos.isEmpty()){
            try {
                userService.addAdminUser(userDTO);
                return Response.status(201).build();
            } catch (AccessLevelException | UserException | NullPointerException e){
                return Response.status(409).build();
            }
        }
        return Response.status(400).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDataUser(PUserDTO userDTO){
        Set<ConstraintViolation<PUserDTO>> cos = validator.validate(userDTO);
        if(cos.isEmpty()){
            try {
                userService.addDataUser(userDTO);
                return Response.status(201).build();
            } catch (AccessLevelException | UserException | NullPointerException e){
                return Response.status(409).build();
            }
        }
        return Response.status(400).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClientUser(PUserDTO userDTO){
        Set<ConstraintViolation<PUserDTO>> cos = validator.validate(userDTO);
        if(cos.isEmpty()){
            try {
                userService.addClientUser(userDTO);
                return Response.status(201).build();
            } catch (AccessLevelException | UserException | NullPointerException e){
                return Response.status(409).build();
            }
        }
        return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(UserDTO userDTO){
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
    public Response changeActivity(@PathParam("login") String login){
        try {
            userService.changeActivity(login);
            Response.ok().build();
        } catch (UserException e){
            return Response.status(404).build();
        }

        return Response.status(400).build();
    }

}