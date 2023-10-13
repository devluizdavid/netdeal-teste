package br.com.netdeal.colaborador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String token;
    private String nome;
    private String email;
    private String picture;

}
