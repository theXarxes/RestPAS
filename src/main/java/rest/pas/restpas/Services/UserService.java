package rest.pas.restpas.Services;

import rest.pas.restpas.Model.DTO.UserDTO;
import rest.pas.restpas.Model.Repositories.UserRepository;
import rest.pas.restpas.Model.Exceptions.AccessLevelException;
import rest.pas.restpas.Model.Exceptions.UserException;
import rest.pas.restpas.Model.Mappers.UserMapper;
import rest.pas.restpas.Model.Entities.Users.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public void addUser(UserDTO userDTO) throws AccessLevelException, UserException {
            String login = userDTO.getLogin();
            int accessLevel = userDTO.getAccessLevel();
            String name = userDTO.getName();
            if(login == null || name == null || accessLevel == 0){
                throw new UserException("Incomplite user data...");
            }
            if (userRepository.isNotLoginUnique(login)){
                throw new UserException("This login is occupied...");
            }
            userRepository.addUser(userDTO);
    }

    public UserDTO getUser(String login) throws UserException {
            User u = userRepository.getUser(login);
            if (u == null){
                throw new UserException("Invalid user login...");
            }
            return UserMapper.userMapper(u);
    }

    public ArrayList<UserDTO> getUsers(){
        ArrayList<UserDTO> list = new ArrayList<>();
        for(User x: userRepository.getUsers()){
            list.add(UserMapper.userMapper(x));
        }
        return list;
    }

    public void updateUser(UserDTO userDTO) throws UserException {
        if(userDTO.getName() == null) throw new UserException("Incomplite user data...");
        if (!userRepository.updateUser(userDTO)){
            throw new UserException("Invalid user id...");
        }
    }

    public void changeUserActivity(String login) throws UserException {
        if(!userRepository.changeUserActivity(login)){
            throw new UserException("Invalid user login");
        }

    }

    public ArrayList<UserDTO> getByPartLogin(String login){
        ArrayList<UserDTO> u = new ArrayList<>();
        for (User x: userRepository.getByPartLogin(login)){
            u.add(UserMapper.userMapper(x));
        }
        return u;
    }

}
