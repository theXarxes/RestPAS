package rest.pas.restpas.model.entity.Users;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class User {
    final UUID id;
    String login;
    boolean isActive;
    String name;

    public User(UUID uuid, String login, boolean isActive, String name) {
        this.id = uuid;
        this.login = login;
        this.isActive = isActive;
        this.name = name;
    }

    public abstract String getAccessLevel();
}
