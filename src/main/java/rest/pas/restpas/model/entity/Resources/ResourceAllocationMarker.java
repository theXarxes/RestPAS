package rest.pas.restpas.model.entity.Resources;

import lombok.Getter;
import lombok.Setter;
import rest.pas.restpas.model.entity.Users.User;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ResourceAllocationMarker {
    final UUID id;
    final LocalDate start;
    final Resource resource;
    boolean isEnded = false;
    User user;

    public ResourceAllocationMarker(Resource resource, int delay, User user) {
        this.id = UUID.randomUUID();
        this.resource = resource;
        this.start = LocalDate.now().plusDays(delay);
        this.user = user;
    }
}
