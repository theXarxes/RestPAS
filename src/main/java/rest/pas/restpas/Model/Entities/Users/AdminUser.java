package rest.pas.restpas.Model.Entities.Users;

import java.util.UUID;

public class AdminUser extends User{

    public AdminUser(UUID uuid, String login, boolean isActive, String name) {
        super(uuid, login, isActive, name);
    }
}
