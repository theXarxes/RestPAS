package rest.pas.restpas.model.DTO.userDTO;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PUserDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String name;
}
