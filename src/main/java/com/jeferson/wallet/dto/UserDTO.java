package com.jeferson.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @Size(min = 3, max = 100)
    private String name;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull(message = "Informe um grupo de acesso")
    @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$", message = "Para o grupo somente são aceitos os valores ROLE_ADMIN ou ROLE_USER")
    private String role;
}
