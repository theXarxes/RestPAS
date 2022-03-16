package rest.pas.restpas.model.repository;

import rest.pas.restpas.model.DTO.resourceDTO.ResourceDTO;
import rest.pas.restpas.model.exception.ResourceAllocationException;
import rest.pas.restpas.model.entity.Resources.Resource;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ResourceRepository {

    List<Resource> resources = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
            new Resource(UUID.fromString("68a577ae-6863-4510-82ce-aadecadb736c"),1),
            new Resource(UUID.fromString("71900926-0ab8-454f-abba-cf49a73ef503"),2),
            new Resource(UUID.fromString("185fa974-fb7c-4d57-bf53-51ef5fd9563e"),3),
            new Resource(UUID.fromString("b40fe46b-ba62-498a-a1d3-5a7ca4feae1a"), 4),
            new Resource(UUID.fromString("f01221d3-c14b-4c40-adfb-509238ed26a4"), 5)
    )));

    public void addResource(Resource r){
            resources.add(r);
    }

    public Resource getResource(UUID id){
        for(Resource x: resources){
            if(x.getId().equals(id)){
                return x;
            }
        }
        return null;
    }

    public List<Resource> getResources(){
        return resources;
    }

    public boolean updateResource(ResourceDTO resourceDTO) throws ResourceAllocationException {
        Resource x = getResource(resourceDTO.getId());
        if(!x.isAllocated()){
            x.setValueOfResource(resourceDTO.getValueOfResource());
            return true;
        }
        throw new ResourceAllocationException("Allocated resource cannot be changed...");
    }

    public boolean deleteResource(UUID id) throws ResourceAllocationException{
        Resource x = getResource(id);
        if(!x.isAllocated()){
            resources.remove(x);
            return true;
        }
        throw new ResourceAllocationException("Allocated resource cannot be deleted...");
    }

    public void changeAllocation(UUID id){
        Resource x = getResource(id);
        x.setAllocated(!x.isAllocated());
    }

}
