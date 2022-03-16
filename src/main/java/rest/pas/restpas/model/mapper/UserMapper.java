package rest.pas.restpas.model.mapper;

import rest.pas.restpas.model.DTO.userDTO.GUserDTO;
import rest.pas.restpas.model.DTO.userDTO.UserDTO;
import rest.pas.restpas.model.entity.Users.User;

public class UserMapper {
    public static GUserDTO userMapper(User user){
        return new GUserDTO(user);
    }
}