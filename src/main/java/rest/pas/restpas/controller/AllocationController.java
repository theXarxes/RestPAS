package rest.pas.restpas.controller;

import rest.pas.restpas.model.DTO.markerDTO.ResAllMarkerDTO;
import rest.pas.restpas.model.exception.ResourceAllocationException;
import rest.pas.restpas.model.exception.ResourceException;
import rest.pas.restpas.model.exception.UserException;
import rest.pas.restpas.service.ResAllMarkerService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

@Path("/allocate")
public class AllocationController {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Inject
    ResAllMarkerService markerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarkers() {
        return Response.ok().entity(markerService.getMarkers()).build();
    }

    @GET
    @Path("/resource/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourceMarkers(@PathParam("id") UUID id) {
        try {
            return Response.ok().entity(markerService.getResourceMarkers(id)).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/user/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserMarkers(@PathParam("login") String login) {
        try {
            return Response.ok().entity(markerService.getUserMarkers(login)).build();
        } catch (ResourceException e){
            return Response.status(404).build();

        }
    }

    @GET
    @Path("/user/{login}/current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUserMarkers(@PathParam("login") String login) {
        try {
            return Response.ok().entity(markerService.getCurrentUserMarkers(login)).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/user/{login}/ended")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndedUserMarkers(@PathParam("login") String login) {
        try {
            return Response.ok().entity(markerService.getEndedUserMarkers(login)).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarker(@PathParam("id") UUID id) {
        try {
            return Response.ok().entity(markerService.getMarker(id)).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteMarker(@PathParam("id") UUID id) {
        try {
            markerService.deleteMarker(id);
            return Response.ok().build();
        } catch (ResourceAllocationException e){
            return Response.status(400).build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response allocateResource(@PathParam("id") UUID id, ResAllMarkerDTO dto){
        Set<ConstraintViolation<ResAllMarkerDTO>> cos = validator.validate(dto);
        if(cos.isEmpty()){
            try {
                markerService.allocateResource(id, dto.getDelay(), dto.getUser());
                return Response.ok().build();
            } catch (ResourceException | UserException e){
                return Response.status(400).build();
            }
        }
        return Response.status(401).build();

    }

    @POST
    @Path("/end/{id}")
    public Response endAllocation(@PathParam("id") UUID id){
        try {
            markerService.endAllocation(id);
            return Response.ok().build();
        } catch (ResourceException e){
            return Response.status(404).build();
        }
    }

}
