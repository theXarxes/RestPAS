package rest.pas.restpas.model.entity.Users;

import java.util.UUID;

public class AdminUser extends User{

    public AdminUser(UUID uuid, String login, boolean isActive, String name) {
        super(uuid, login, isActive, name);
    }

    @Override
    public String getAccessLevel() {
        return "ADMIN";
    }
}
