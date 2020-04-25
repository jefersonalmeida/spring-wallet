package com.jeferson.wallet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletItemDTO {

    private Long id;

    @NotNull(message = "Insira o id da carteira")
    private Long wallet;

    @NotNull(message = "Informe uma data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date date;

    @NotNull(message = "Informe um tipo")
    @Pattern(regexp = "^(ENTRADA|SAÍDA)$", message = "Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
    private String type;

    @NotNull(message = "Informe uma descrição")
    @Size(min = 3, max = 500)
    private String description;

    @NotNull(message = "Insira um valor para o lançamento")
    private BigDecimal value;
}
