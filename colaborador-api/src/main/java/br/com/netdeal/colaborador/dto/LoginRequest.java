package br.com.netdeal.colaborador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @NotEmpty(message = "{name.notempty}")
    private String login;

    @NotEmpty(message = "{name.notempty}")
    private String senha;

}
