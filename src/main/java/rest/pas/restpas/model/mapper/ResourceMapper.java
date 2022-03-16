package rest.pas.restpas.model.mapper;

import rest.pas.restpas.model.DTO.resourceDTO.ResourceDTO;
import rest.pas.restpas.model.entity.Resources.Resource;

public class ResourceMapper {
    public static ResourceDTO resourceMapper(Resource res){
        return new ResourceDTO(res);
    }
}
