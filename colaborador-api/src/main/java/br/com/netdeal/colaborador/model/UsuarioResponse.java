package br.com.netdeal.colaborador.model;

import lombok.Data;
 
import java.math.BigDecimal;

@Data
public class UsuarioResponse {

    private Long id;
 
    private String nome;

    private Long colaboradorPai;

    private Integer score;

    private String cor;

    private String complexidade;

    private String hierarquia;
}
