package rest.pas.restpas.Model.Entities.Users;

import java.util.UUID;

public class DataAdminUser extends User{


    public DataAdminUser(UUID uuid, String login, boolean isActive, String name) {
        super(uuid, login, isActive, name);
    }
}
