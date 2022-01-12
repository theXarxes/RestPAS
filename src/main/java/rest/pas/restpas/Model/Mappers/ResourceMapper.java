package rest.pas.restpas.Model.Mappers;

import rest.pas.restpas.Model.DTO.ResourceDTO;
import rest.pas.restpas.Model.Entities.Resources.Resource;

public class ResourceMapper {
    public static ResourceDTO resourceMapper(Resource res){
        return new ResourceDTO(res);
    }
}
