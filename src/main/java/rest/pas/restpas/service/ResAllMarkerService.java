package rest.pas.restpas.service;

import rest.pas.restpas.model.DTO.markerDTO.ResAllMarkerDTO;
import rest.pas.restpas.model.entity.Users.User;
import rest.pas.restpas.model.exception.UserException;
import rest.pas.restpas.model.repository.ResAllMarkerRepository;
import rest.pas.restpas.model.exception.ResourceAllocationException;
import rest.pas.restpas.model.exception.ResourceException;
import rest.pas.restpas.model.mapper.ResAllMarkerMapper;
import rest.pas.restpas.model.entity.Resources.Resource;
import rest.pas.restpas.model.entity.Resources.ResourceAllocationMarker;
import rest.pas.restpas.model.repository.ResourceRepository;
import rest.pas.restpas.model.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class ResAllMarkerService {

    @Inject
    ResAllMarkerRepository allMarkerRepository;

    @Inject
    ResourceRepository resourceRepository;

    @Inject
    UserRepository userRepository;

    public ResAllMarkerDTO getMarker(UUID id) throws ResourceException {
            ResourceAllocationMarker r = allMarkerRepository.getMarker(id);
            if (r == null){
                throw new ResourceException("Invalid marker id...");
            }
            return ResAllMarkerMapper.resAllMarkerMapper(r);
    }

    public ArrayList<ResAllMarkerDTO> getUserMarkers(String login) throws ResourceException {
            ArrayList<ResAllMarkerDTO> list = new ArrayList<>();
            for (ResourceAllocationMarker r : allMarkerRepository.getUserMarkers(login)){
                list.add(ResAllMarkerMapper.resAllMarkerMapper(r));
            }
            if (list.isEmpty()){
                throw new ResourceException("Invalid marker id...");
            }
            return list;
    }

    public ArrayList<ResAllMarkerDTO> getCurrentUserMarkers(String login) throws ResourceException {
            ArrayList<ResAllMarkerDTO> list = new ArrayList<>();
            for (ResourceAllocationMarker r : allMarkerRepository.getUserMarkers(login)){
                if(!r.isEnded()) list.add(ResAllMarkerMapper.resAllMarkerMapper(r));
            }
            if (list.isEmpty()){
                throw new ResourceException("Invalid marker id...");
            }
            return list;
    }

    public ArrayList<ResAllMarkerDTO> getEndedUserMarkers(String login) throws ResourceException {
            ArrayList<ResAllMarkerDTO> list = new ArrayList<>();
            for (ResourceAllocationMarker r : allMarkerRepository.getUserMarkers(login)){
                if(r.isEnded()) list.add(ResAllMarkerMapper.resAllMarkerMapper(r));
            }
            if (list.isEmpty()){
                throw new ResourceException("Invalid marker id...");
            }
            return list;
    }



    public ArrayList<ResAllMarkerDTO> getResourceMarkers(UUID id) throws ResourceException {
            ArrayList<ResAllMarkerDTO> list = new ArrayList<>();
            for (ResourceAllocationMarker r : allMarkerRepository.getResourceMarkers(id)){
                list.add(ResAllMarkerMapper.resAllMarkerMapper(r));
            }
            if (list.isEmpty()){
                throw new ResourceException("Invalid marker id...");
            }
            return list;
    }

    public ArrayList<ResAllMarkerDTO> getMarkers(){
        ArrayList<ResAllMarkerDTO> list = new ArrayList<>();
        for (ResourceAllocationMarker r : allMarkerRepository.getMarkers()){
            list.add(ResAllMarkerMapper.resAllMarkerMapper(r));
        }
        return list;
    }

    public void deleteMarker(UUID id) throws ResourceException {
            if(!allMarkerRepository.deleteAllocation(id)){
                throw new ResourceException("Invalid marker id...");
            }
    }

    public void allocateResource(UUID resourceId, int allocationDelay, String user) throws ResourceException, UserException {
        User u = userRepository.getUser(user);
        if(u != null){
            if(u.isActive()){
                Resource r = resourceRepository.getResource(resourceId);
                if(r != null){
                    if(!r.isAllocated()){
                        Resource res = resourceRepository.getResource(resourceId);
                        ResourceAllocationMarker marker = new ResourceAllocationMarker(res, allocationDelay, u);
                        r.setAllocated(true);
                        allMarkerRepository.getMarkers().add(marker);
                        return;
                    }
                    throw new ResourceAllocationException("Resource already allocated...");
                }
                throw new ResourceAllocationException("Invalid resource id...");
            }
            throw new UserException("User is inactive...");
        }
        throw new UserException("Invalid user login...");
    }

    public void endAllocation(UUID id) throws ResourceException {
            ResourceAllocationMarker r = allMarkerRepository.getMarker(id);
            if (r == null) throw new ResourceException("Invalid marker id...");
            resourceRepository.changeAllocation(r.getResource().getId());
            r.setEnded(true);
    }



}
