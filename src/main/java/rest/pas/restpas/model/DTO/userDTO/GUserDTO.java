package rest.pas.restpas.model.DTO.userDTO;

import lombok.Data;
import rest.pas.restpas.model.entity.Users.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GUserDTO {

    @NotBlank
    String login;
    @NotBlank
    boolean isActive;
    @NotBlank
    String accessLevel;
    @NotBlank
    String name;

    public GUserDTO(User user) {
        this.login = user.getLogin();
        this.isActive = user.isActive();
        this.accessLevel = user.getAccessLevel();
        this.name = user.getName();
    }
}
