package br.com.netdeal.colaborador.model;

import lombok.Data;

@Data
public class UsuarioRequest {

    private Long id;
 
    private String nome; 
    
    private String login;

    private String senha;

    private Integer score;

    private Long colaboradorPai;
}
