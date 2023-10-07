package br.com.netdeal.colaborador.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum FaixaScoreEnum {
    RUIM("green"),

    MEDIANA("orange"),

    BOM("green-light"),

    FORTE("green");

    private String cor;

}
