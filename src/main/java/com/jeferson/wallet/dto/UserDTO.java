package com.jeferson.wallet.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserDTO {

    private Long id;

    @Size(min = 3, max = 100)
    private String name;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;
}
