package rest.pas.restpas.Model.DTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import rest.pas.restpas.Model.Entities.Users.AdminUser;
import rest.pas.restpas.Model.Entities.Users.DataAdminUser;
import rest.pas.restpas.Model.Entities.Users.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotBlank
    String login;
    boolean isActive;
    @NotNull
    int accessLevel;
    @NotBlank
    String name;

    public UserDTO(User user) {
        this.login = user.getLogin();
        this.isActive = user.isActive();
        this.name = user.getName();
        if (user.getClass() == AdminUser.class){
            this.accessLevel = 3;
        }else if (user.getClass() == DataAdminUser.class){
            this.accessLevel = 2;
        }else this.accessLevel = 1;
    }
}
