package br.com.netdeal.colaborador.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponse implements Serializable {

    private static final long serialVersionUID = 4878825827657916191L;

    @ApiModelProperty(value = "Código da Mensagem")
    private String code;

    @ApiModelProperty(value = "Status HTTP")
    private Integer status;

    @ApiModelProperty(value = "Descrição erro HTTP")
    private String error;

    @ApiModelProperty(value = "Mensagem de negócio")
    private String message;

    @ApiModelProperty(value = "Parâmetros da mensagem")
    private Object[] parameters;

    @ApiModelProperty(value = "Atributos de validação")
    private List<FieldResponse> attributes;

    /**
     * Adiciona a instância de {@link FieldResponse}.
     *
     * @param field
     */
    public void addAttribute(final FieldResponse field) {
        if (attributes.isEmpty()) {
            attributes = new ArrayList<>();
        }
        attributes.add(field);
    }
}
