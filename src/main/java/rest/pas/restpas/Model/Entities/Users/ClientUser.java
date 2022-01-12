package rest.pas.restpas.Model.Entities.Users;

import java.util.UUID;

public class ClientUser extends User{

    public ClientUser(UUID uuid, String login, boolean isActive, String name) {
        super(uuid, login, isActive, name);
    }

}
