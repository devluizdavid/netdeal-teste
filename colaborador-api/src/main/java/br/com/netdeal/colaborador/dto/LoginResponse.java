package br.com.netdeal.colaborador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String token;
    private String nome;

}
