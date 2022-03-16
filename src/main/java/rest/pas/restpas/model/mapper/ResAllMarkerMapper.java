package rest.pas.restpas.model.mapper;

import rest.pas.restpas.model.DTO.markerDTO.ResAllMarkerDTO;
import rest.pas.restpas.model.entity.Resources.ResourceAllocationMarker;

public class ResAllMarkerMapper {
    public static ResAllMarkerDTO resAllMarkerMapper(ResourceAllocationMarker res){
        return new ResAllMarkerDTO(res);
    }
}
