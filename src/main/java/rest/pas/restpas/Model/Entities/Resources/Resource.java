package rest.pas.restpas.Model.Entities.Resources;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Resource {

    final UUID id;
    boolean isAllocated = false;
    int valueOfResource;

    public Resource(int valueOfResource) {
        this.id = UUID.randomUUID();
        this.valueOfResource = valueOfResource;
    }

    public Resource(UUID id, int valueOfResource) {
        this.id = id;
        this.valueOfResource = valueOfResource;
    }
}
