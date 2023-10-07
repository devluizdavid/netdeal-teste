/*
 * FieldResponse.java
 * Copyright Kingspan Isoeste.
 *
 * Este software é confidencial e propriedade da Kingspan Isoeste.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da Kingspan Isoeste.
 * Este arquivo contém informações proprietárias.
 */
package br.com.netdeal.colaborador.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Classe de representação de Atributos de Resposta utilizada nas implementações de 'ExceptionHandler'
 *
 * @author Kingspan Isoeste
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldResponse implements Serializable {

    private static final long serialVersionUID = -807504480597471148L;

    @ApiModelProperty(value = "Nome do atributo")
    private String attribute;

    @ApiModelProperty(value = "Descrição da validação")
    private String description;
}
