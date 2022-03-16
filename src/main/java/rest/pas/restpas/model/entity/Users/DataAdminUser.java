package rest.pas.restpas.model.entity.Users;

import java.util.UUID;

public class DataAdminUser extends User{


    public DataAdminUser(UUID uuid, String login, boolean isActive, String name) {
        super(uuid, login, isActive, name);
    }

    @Override
    public String getAccessLevel() {
        return "CLIENT";
    }
}
