package rest.pas.restpas.Model.Repositories;

import rest.pas.restpas.Model.DTO.UserDTO;
import rest.pas.restpas.Model.Entities.Users.AdminUser;
import rest.pas.restpas.Model.Entities.Users.ClientUser;
import rest.pas.restpas.Model.Entities.Users.DataAdminUser;
import rest.pas.restpas.Model.Entities.Users.User;
import rest.pas.restpas.Model.Exceptions.AccessLevelException;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class UserRepository {

    List<User> users = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
            new AdminUser(UUID.randomUUID(), "user1", true, "Bolek"),
            new DataAdminUser(UUID.randomUUID(), "user2", true, "Lolek"),
            new ClientUser(UUID.randomUUID(), "user3", true, "Tola")
    )));

    public void addUser(UserDTO userDTO) throws AccessLevelException {
        switch (userDTO.getAccessLevel()){
            case 1:
                users.add(new ClientUser(UUID.randomUUID(), userDTO.getLogin(),true, userDTO.getName()));
                break;
            case 2:
                users.add(new DataAdminUser(UUID.randomUUID(), userDTO.getLogin(),true, userDTO.getName()));
                break;
            case 3:
                users.add(new AdminUser(UUID.randomUUID(), userDTO.getLogin(),true, userDTO.getName()));
                break;
            default:
                throw new AccessLevelException("Invalid access level...");
        }
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(String login){
        for(User x: users){
            if(x.getLogin().equals(login)){
                return x;
            }
        }
        return null;
    }

    public ArrayList<User> getByPartLogin(String login){
        ArrayList<User> u = new ArrayList<>();
        for(User x: users){
           if (x.getLogin().contains(login)){
               u.add(x);
           }
        }
        return u;
    }

    public boolean updateUser(UserDTO u){
        User user = getUser(u.getLogin());
        if (user != null){
            user.setName(u.getName());
            return true;
        }
        return false;
    }

    public boolean changeUserActivity(String login){
        User u = getUser(login);
        if (u != null){
            u.setActive(!u.isActive());
            return true;
        }
        return false;
    }

    public boolean isNotLoginUnique(String login){
        for (User u : users){
            if (u.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public boolean isActive(String login){
        User u = getUser(login);
        return u.isActive();
    }
}
