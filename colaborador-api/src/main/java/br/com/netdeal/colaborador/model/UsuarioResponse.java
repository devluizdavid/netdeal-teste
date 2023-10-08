package br.com.netdeal.colaborador.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class UsuarioResponse {

    private Long id;

    @NotEmpty(message = "{name.notempty}")
    private String nome;

    private Long colaboradorPai;

    private BigDecimal score;
}
