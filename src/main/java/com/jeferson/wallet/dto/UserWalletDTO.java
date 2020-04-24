package com.jeferson.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWalletDTO {

    private Long id;

    @NotNull(message = "Informe o id do usuário")
    private Long users;

    @NotNull(message = "Informe o id da carteira")
    private Long wallets;
}
