package br.com.netdeal.colaborador.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class UsuarioRequest {

    private Long id;

    @NotEmpty(message = "{name.notempty}")
    private String nome;

    @NotEmpty(message = "{name.notempty}")
    private String login;

    @NotEmpty(message = "{name.notempty}")
    private String senha;

    private Integer score;

    private Long colaboradorPai;
}
