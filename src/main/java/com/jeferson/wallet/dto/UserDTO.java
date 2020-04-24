package com.jeferson.wallet.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private Long id;

    @Length(min = 3, max = 100)
    private String name;

    @Email
    private String email;

    @NotNull
    @Length(min = 6)
    private String password;
}
