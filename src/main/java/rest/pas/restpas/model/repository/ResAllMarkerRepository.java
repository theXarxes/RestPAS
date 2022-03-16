package rest.pas.restpas.model.repository;

import rest.pas.restpas.model.exception.ResourceAllocationException;
import rest.pas.restpas.model.entity.Resources.ResourceAllocationMarker;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ResAllMarkerRepository {

    @Inject
    ResourceRepository repository;

    List<ResourceAllocationMarker> markers = Collections.synchronizedList(new ArrayList<>());

    public List<ResourceAllocationMarker> getMarkers(){
        return markers;
    }

    public ResourceAllocationMarker getMarker(UUID id){
        for(ResourceAllocationMarker x: markers){
            if(x.getId().equals(id)){
                return x;
            }
        }
        return null;
    }

    public List<ResourceAllocationMarker> getUserMarkers(String login){
        List<ResourceAllocationMarker> list = new ArrayList<>();
        for(ResourceAllocationMarker x: markers){
            if(x.getUser().getLogin().equals(login)){
                list.add(x);
            }
        }
        return list;
    }

    public List<ResourceAllocationMarker> getResourceMarkers(UUID id){
        List<ResourceAllocationMarker> list = new ArrayList<>();
        for(ResourceAllocationMarker x: markers){
            if(x.getResource().getId().equals(id)){
                list.add(x);
            }
        }
        return list;
    }

    public boolean deleteAllocation(UUID id) throws ResourceAllocationException {
        for(ResourceAllocationMarker x: markers){
            if(x.getId().equals(id)){
                if(x.isEnded()){
                    repository.changeAllocation(x.getResource().getId());
                    markers.remove(x);
                    return true;
                }
                throw new ResourceAllocationException("Finished allocations cannot be deleted...");
            }
        }
        return false;
    }
}
