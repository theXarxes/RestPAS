package rest.pas.restpas.service;

import rest.pas.restpas.model.DTO.resourceDTO.ResourceDTO;
import rest.pas.restpas.model.repository.ResourceRepository;
import rest.pas.restpas.model.exception.ResourceAllocationException;
import rest.pas.restpas.model.exception.ResourceException;
import rest.pas.restpas.model.mapper.ResourceMapper;
import rest.pas.restpas.model.entity.Resources.Resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class ResourceService {

    @Inject
    ResourceRepository resourceRepository;

    public void addResource(ResourceDTO resourceDTO){
        resourceRepository.addResource(new Resource(resourceDTO.getValueOfResource()));
    }

    public ResourceDTO getResource(UUID id) throws ResourceException {
            Resource r = resourceRepository.getResource(id);
            if (r == null){
                throw new ResourceException("Invalid resource id...");
            }
            return ResourceMapper.resourceMapper(r);
    }

    public ArrayList<ResourceDTO> getResources(){
        ArrayList<ResourceDTO> list = new ArrayList<>();
        for (Resource r : resourceRepository.getResources()){
            list.add(ResourceMapper.resourceMapper(r));
        }
        return list;
    }

    public void updateResource(ResourceDTO resourceDTO) throws ResourceAllocationException {
            if (!resourceRepository.updateResource(resourceDTO)){
                throw new ResourceAllocationException("Invalid resource id...");
            }
    }

    public void deleteResource(UUID id) throws ResourceException {
            if (!resourceRepository.deleteResource(id)){
                throw new ResourceException("Invalid resource id...");
            }
    }
}
