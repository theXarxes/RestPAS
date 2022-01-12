package rest.pas.restpas.Model.Mappers;

import rest.pas.restpas.Model.DTO.ResAllMarkerDTO;
import rest.pas.restpas.Model.Entities.Resources.ResourceAllocationMarker;

public class ResAllMarkerMapper {
    public static ResAllMarkerDTO resAllMarkerMapper(ResourceAllocationMarker res){
        return new ResAllMarkerDTO(res);
    }
}
