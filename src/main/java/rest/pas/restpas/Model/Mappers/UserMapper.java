package rest.pas.restpas.Model.Mappers;

import rest.pas.restpas.Model.DTO.UserDTO;
import rest.pas.restpas.Model.Entities.Users.User;

public class UserMapper {
    public static UserDTO userMapper(User user){
        return new UserDTO(user);
    }
}