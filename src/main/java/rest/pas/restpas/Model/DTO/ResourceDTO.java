package rest.pas.restpas.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rest.pas.restpas.Model.Entities.Resources.Resource;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Getter
public class ResourceDTO {
    UUID id;
    boolean isAllocated = false;
    @NotNull
    int valueOfResource;

    public ResourceDTO(Resource res) {
        this.id = res.getId();
        this.isAllocated = res.isAllocated();
        this.valueOfResource = res.getValueOfResource();
    }
}
