package rest.pas.restpas.Controllers;

import rest.pas.restpas.Model.DTO.ResourceDTO;
import rest.pas.restpas.Model.Exceptions.ResourceException;
import rest.pas.restpas.Services.ResourceService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Path("/resource")
public class ResourceController {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Inject
    ResourceService resourceService;


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResource(@PathParam("id") String id) {
        try {
            return Response.ok().entity(resourceService.getResource(UUID.fromString(id))).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ResourceDTO> getResources() {
        return resourceService.getResources();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUResource(ResourceDTO resourceDTO){
        Set<ConstraintViolation<ResourceDTO>> cos = validator.validate(resourceDTO);
        if(cos.isEmpty()){
            resourceService.addResource(resourceDTO);
            return Response.status(201).build();
        }
        return Response.status(400).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateResource(ResourceDTO resourceDTO){
        try {
            resourceService.updateResource(resourceDTO);
            return Response.ok().build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") UUID id){
        try {
            resourceService.deleteResource(id);
            return Response.ok().build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }
}
