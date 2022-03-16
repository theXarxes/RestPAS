package rest.pas.restpas.service;

import rest.pas.restpas.model.DTO.userDTO.GUserDTO;
import rest.pas.restpas.model.DTO.userDTO.PUserDTO;
import rest.pas.restpas.model.DTO.userDTO.UserDTO;
import rest.pas.restpas.model.repository.UserRepository;
import rest.pas.restpas.model.exception.AccessLevelException;
import rest.pas.restpas.model.exception.UserException;
import rest.pas.restpas.model.mapper.UserMapper;
import rest.pas.restpas.model.entity.Users.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

//    public void addUser(UserDTO userDTO) throws AccessLevelException, UserException {
//            String login = userDTO.getLogin();
//            int accessLevel = userDTO.getAccessLevel();
//            String name = userDTO.getName();
//            if(login == null || name == null || accessLevel == 0){
//                throw new UserException("Incomplite user data...");
//            }
//            if (userRepository.isLoginNotUnique(login)){
//                throw new UserException("This login is occupied...");
//            }
//            userRepository.addUser(userDTO);
//    }

    public void addAdminUser(PUserDTO userDTO) throws AccessLevelException, UserException {
        if (userRepository.isLoginNotUnique(userDTO.getLogin())){
            throw new UserException("This login is occupied...");
        }
        userRepository.addAdminUser(userDTO);
    }

    public void addDataUser(PUserDTO userDTO) throws AccessLevelException, UserException {
        if (userRepository.isLoginNotUnique(userDTO.getLogin())){
            throw new UserException("This login is occupied...");
        }
        userRepository.addDataUser(userDTO);
    }

    public void addClientUser(PUserDTO userDTO) throws AccessLevelException, UserException {
        if (userRepository.isLoginNotUnique(userDTO.getLogin())){
            throw new UserException("This login is occupied...");
        }
        userRepository.addClientUser(userDTO);
    }


    public GUserDTO getUser(String login) throws UserException {
            User u = userRepository.getUser(login);
            if (u == null){
                throw new UserException("Invalid user login...");
            }
            return UserMapper.userMapper(u);
    }

    public ArrayList<GUserDTO> getUsers(){
        ArrayList<GUserDTO> list = new ArrayList<>();
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

    public void changeActivity(String login) throws UserException {
        if(!userRepository.changeActivity(login)){
            throw new UserException("Invalid user login");
        }

    }

    public ArrayList<GUserDTO> getByPartLogin(String login){
        ArrayList<GUserDTO> u = new ArrayList<>();
        for (User x: userRepository.getByPartLogin(login)){
            u.add(UserMapper.userMapper(x));
        }
        return u;
    }

}
