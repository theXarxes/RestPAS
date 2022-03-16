package rest.pas.restpas.model.DTO.markerDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rest.pas.restpas.model.entity.Resources.ResourceAllocationMarker;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ResAllMarkerDTO {
    UUID id;
    LocalDate start;
    UUID resourceId;
    boolean isEnded;
    @NotNull
    String user;
    int delay = 0;

    public ResAllMarkerDTO(ResourceAllocationMarker res) {
        this.id = res.getId();
        this.start = res.getStart();
        this.resourceId = res.getResource().getId();
        this.isEnded = res.isEnded();
        this.user = res.getUser().getLogin();
    }
}
