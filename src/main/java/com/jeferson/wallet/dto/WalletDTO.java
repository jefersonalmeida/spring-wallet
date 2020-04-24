package com.jeferson.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDTO {

    private Long id;

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 100)
    private String name;

    @NotNull(message = "Insira um valor para sua carteira")
    private BigDecimal value;
}
