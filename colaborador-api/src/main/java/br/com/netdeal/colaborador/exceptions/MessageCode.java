/*
 * MessageCode.java
 * Copyright Kingspan Isoeste.
 *
 * Este software é confidencial e propriedade da Kingspan Isoeste.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da Kingspan Isoeste.
 * Este arquivo contém informações proprietárias.
 */
package br.com.netdeal.colaborador.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum com os código de exceções/mensagens de negócio.
 *
 * @author Kingspan Isoeste
 */
@Getter
@AllArgsConstructor
public enum MessageCode {

    ERRO_NENHUM_REGISTRO_ENCONTRADO("MSG001", 404);

    /**
     * Código da mensagem disponível no *.properties de mensagem.
     */
    private final String code;

    /**
     * Status HTTP referente a mensagem.
     */
    private final Integer status;

    @Override
    public String toString() {
        return code;
    }
}
