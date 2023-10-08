package br.com.netdeal.colaborador.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScoreService {

    public int calcularScoreSenhaTotal(String senhaInformada) {
        System.out.println("senhaInformada = "+senhaInformada);
        int scoreFinal = 0;

        Integer itensRequeridos = calcularScoreSenhaItensRequeridos(senhaInformada);
        int numeroCaracteres = senhaInformada.length() * 4;
        int quantidadeDeNumeros = contarTiposDeCaracteres("\\d", senhaInformada);
        if (!soPossuiNumero(senhaInformada)) quantidadeDeNumeros = quantidadeDeNumeros * 4;
        else quantidadeDeNumeros = 0;

        int quantidadeDeSimbolos = contarCaracteresEspeciais(senhaInformada);
        int quantidadeDeLetrasMaisculas = contarTiposDeCaracteres("[A-Z]", senhaInformada);
        if (quantidadeDeLetrasMaisculas > 0) {
            quantidadeDeLetrasMaisculas = (senhaInformada.length() - quantidadeDeLetrasMaisculas) * 2;
        }

        int quantidadeDeLetrasMinusculas = contarTiposDeCaracteres("[a-z]", senhaInformada);
        if (quantidadeDeLetrasMinusculas > 0) {
            quantidadeDeLetrasMinusculas = (senhaInformada.length() - quantidadeDeLetrasMinusculas) * 2;
        }
        int quantidadeNumeroSimbolosNoMeio = calcularQuantidadeNumeroSimbolosNoMeio(senhaInformada);



        System.out.println("numeroCaracteres = "+numeroCaracteres);
        System.out.println("quantidadeDeNumeros = "+quantidadeDeNumeros);
        System.out.println("quantidadeDeLetrasMaisculas = "+quantidadeDeLetrasMaisculas);
        System.out.println("quantidadeDeLetrasMinusculas = "+quantidadeDeLetrasMinusculas);
        System.out.println("quantidadeNumeroSimbolosNoMeio = "+quantidadeNumeroSimbolosNoMeio);
        System.out.println("itensRequeridos = "+itensRequeridos);

        scoreFinal += itensRequeridos;
        scoreFinal += numeroCaracteres;
        scoreFinal += quantidadeDeNumeros;
        scoreFinal += quantidadeDeSimbolos;
        scoreFinal += quantidadeDeLetrasMaisculas;
        scoreFinal += quantidadeDeLetrasMinusculas;
        scoreFinal += quantidadeNumeroSimbolosNoMeio;

        System.out.println("scoreFinal antes da dedução - "+scoreFinal);
        //Deduções
        if (soPossuiLetra(senhaInformada)) {
            scoreFinal -= senhaInformada.length();
        }
        if (soPossuiNumero(senhaInformada)) {
            scoreFinal -= senhaInformada.length();
        }

        int quantidadeSequencialDeTresLetras = quantidadeSequencialDeTresLetras(senhaInformada);
        int quantidadeSequencialDeTresNumeros = quantidadeSequencialDeTresNumeros(senhaInformada);
        int quantidadeSequencialDeTresSimbolos = quantidadeSequencialDeTresSimbolos(senhaInformada);
        int quantidadeSequencialDeDoisNumeros = quantidadeSequencialDeDoisNumeros(senhaInformada);
        int quantidadeSequencialDeDuasMaisculas = quantidadeSequencialDeDuasMaisculas(senhaInformada);
        int quantidadeSequencialDeDuasMinusculas = quantidadeSequencialDeDuasMinusculas(senhaInformada);
        int quantidadeCaracteresRepetidos = contadorDeCaracteres(senhaInformada);

        System.out.println("quantidadeSequencialDeTresLetras = "+quantidadeSequencialDeTresLetras);
        System.out.println("quantidadeSequencialDeTresNumeros = "+quantidadeSequencialDeTresNumeros);
        System.out.println("quantidadeSequencialDeTresSimbolos = "+quantidadeSequencialDeTresSimbolos);
        System.out.println("quantidadeSequencialDeDoisNumeros = "+quantidadeSequencialDeDoisNumeros);
        System.out.println("quantidadeSequencialDeDuasMaisculas = "+quantidadeSequencialDeDuasMaisculas);
        System.out.println("quantidadeSequencialDeDuasMinusculas = "+quantidadeSequencialDeDuasMinusculas);
        System.out.println("quantidadeSequencialDeDuasMinusculas = "+quantidadeCaracteresRepetidos);



        if (quantidadeSequencialDeTresLetras > 0) scoreFinal -= quantidadeSequencialDeTresLetras;
        if (quantidadeSequencialDeTresNumeros > 0) scoreFinal -= quantidadeSequencialDeTresNumeros;
        if (quantidadeSequencialDeDoisNumeros > 0) scoreFinal -= quantidadeSequencialDeDoisNumeros;
        if (quantidadeSequencialDeDuasMaisculas > 0) scoreFinal -= quantidadeSequencialDeDuasMaisculas;
        if (quantidadeSequencialDeDuasMinusculas > 0) scoreFinal -= quantidadeSequencialDeDuasMinusculas;
        if (quantidadeCaracteresRepetidos > 0) scoreFinal -= quantidadeCaracteresRepetidos;
        if (quantidadeSequencialDeTresSimbolos > 0) scoreFinal -= (quantidadeSequencialDeTresSimbolos * 3);



        if (scoreFinal > 100) scoreFinal = 100;
        return scoreFinal;

    }


    private Integer quantidadeSequencialDeTresLetras(String senhaInformada) {
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZABC";
        if (senhaInformada.length() < 3) return 0;
        int contador = 0;
        for (int posicao = 0; posicao < senhaInformada.length(); posicao++) {
            if (senhaInformada.length() - posicao < 3) continue;
            String sequencia = senhaInformada.substring(posicao, posicao + 3);
            //if (posicao -1 == senhaInformada.length()) continue;
            boolean sequenciaDeLetras = alfabeto.contains(sequencia);
            if (sequenciaDeLetras) contador++;
        }
        return (contador * 3);
    }

    private Integer quantidadeSequencialDeTresNumeros(String senhaInformada) {
        if (senhaInformada.length() < 3) return 0;
        int contador = 0;
        for (int posicao = 0; posicao < senhaInformada.length(); posicao++) {
            if (senhaInformada.length() - posicao < 3) continue;
            String sequencia = senhaInformada.substring(posicao, posicao + 3);
            //if (posicao -1 == senhaInformada.length()) continue;
            boolean sequenciaDeNumeros = true;
            int atual = -1;
            for (char character : sequencia.toCharArray()) {
                if (!Character.isDigit(character)) {
                    sequenciaDeNumeros = false;
                    break;
                }
                if (atual == -1) {
                    atual = Integer.parseInt("" + character);
                    continue;
                }
                int numero = Integer.parseInt("" + character);
                if (atual == 9 && numero == 0 || numero - atual == 1) atual = numero;
                else sequenciaDeNumeros = false;
            }
            if (sequenciaDeNumeros) contador++;
        }
        return (contador * 3);
    }

    private Integer quantidadeSequencialDeDuasMinusculas(String senhaInformada) {
        int quantidadeSequencias = 0;

        String stringSemEspacos = senhaInformada.replaceAll("\\s", "");

        for (int i = 0; i < stringSemEspacos.length() - 1; i++) {
            char caractereAtual = stringSemEspacos.charAt(i);
            char proximoCaractere = stringSemEspacos.charAt(i + 1);
            if (Character.isLowerCase(caractereAtual) && Character.isLowerCase(proximoCaractere)) {
                quantidadeSequencias++;
            }
        }

        return quantidadeSequencias * 2;
    }

    private Integer quantidadeSequencialDeDuasMaisculas(String senhaInformada) {
        int quantidadeSequencias = 0;
        String stringSemEspacos = senhaInformada.replaceAll("\\s", "");
        for (int i = 0; i < stringSemEspacos.length() - 1; i++) {
            char caractereAtual = stringSemEspacos.charAt(i);
            char proximoCaractere = stringSemEspacos.charAt(i + 1);

            if (Character.isUpperCase(caractereAtual) && Character.isUpperCase(proximoCaractere)) {
                quantidadeSequencias++;
            }
        }

        return quantidadeSequencias * 2;
    }

    private Integer quantidadeSequencialDeDoisNumeros(String senhaInformada) {
        if (senhaInformada.length() < 2) return 0;
        int contador = 0;
        char caracterAnterior = 'A';
        for (char character : senhaInformada.toCharArray()) {
            if (Character.isDigit(character) && Character.isDigit(caracterAnterior)) {
                contador++;
                caracterAnterior=character;
            }
            caracterAnterior=character;
        }

        return (contador * 2);
    }

    private Integer quantidadeSequencialDeTresSimbolos(String senhaInformada) {
        if (senhaInformada.length() < 3) return 0;
        String sequenciaDeSimbolosOficial = "!@#$%&*()-_=+[]{};:'\",.<>?/|\\";
        int contador = 0;
        for (int posicao = 0; posicao < senhaInformada.length(); posicao++) {
            if (senhaInformada.length() - posicao < 3) continue;
            String sequencia = senhaInformada.substring(posicao, posicao + 3);
            //if (posicao -1 == senhaInformada.length()) continue;
            boolean sequenciaDeSimbolos = sequenciaDeSimbolosOficial.contains(sequencia);
            if (sequenciaDeSimbolos) contador++;
        }
        return (contador * 2);
    }


    private Integer calcularQuantidadeNumeroSimbolosNoMeio(String senhaInformada) {
        if (senhaInformada.length() < 3) return 0;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]+");

        int contador = 0;
        for (int posicao = 0; posicao < senhaInformada.length(); posicao++) {
            if (senhaInformada.length() - posicao < 3) continue;
            String sequencia = senhaInformada.substring(posicao, posicao + 3);
            //if (posicao -1 == senhaInformada.length()) continue;
            char caracter = sequencia.charAt(1);
            boolean isSimbolo = pattern.matcher("" + caracter).find();
            if (Character.isDigit(caracter) || isSimbolo) {
                contador++;
            }
        }
        return (contador * 2);
    }

    private int contarTiposDeCaracteres(String regex, String senhaInformada) {
        Pattern pattern = Pattern.compile(regex); // Expressão regular para um único dígito
        Matcher matcher = pattern.matcher(senhaInformada);

        int contador = 0;

        while (matcher.find()) {
            contador++; // Incrementa o contador para cada dígito encontrado
        }

        return contador;
    }

    private int contarCaracteresEspeciais(String senhaInformada) {
        int contador = 0;

        for (char caractere : senhaInformada.toCharArray()) {
            if (!Character.isLetter(caractere) && !Character.isDigit(caractere)
                    && !Character.isWhitespace(caractere)) {
                contador++; // Incrementa o contador se o caractere for uma letra maiúscula
            }
        }

        return (contador * 6);
    }

    private boolean soPossuiNumero(String senhaInformada) {
        boolean isSoNumero = true;
        for (char caractere : senhaInformada.toCharArray()) {
            if (!Character.isDigit(caractere) && !Character.isWhitespace(caractere)) {
                isSoNumero = false;
                break;
            }
        }

        return isSoNumero;
    }

    private boolean soPossuiLetra(String senhaInformada) {
        boolean isSoLetra = true;
        for (char caractere : senhaInformada.toCharArray()) {
            if (!Character.isLetter(caractere) && !Character.isWhitespace(caractere)) {
                isSoLetra = false;
                break;
            }
        }
        return isSoLetra;
    }

    private Integer calcularScoreSenhaItensRequeridos(String senhaInformada) {
        Pattern patternLetrasMinusculas = Pattern.compile("[a-z]");
        Pattern patternLetrasMaisculas = Pattern.compile("[A-Z]");
        Pattern patternLetrasNumeros = Pattern.compile("[0-9]");
        Pattern patternCaracteresEspeciais = Pattern.compile("[^a-zA-Z0-9\\s]+");

        int itensRequeridos = 0;

        if (patternLetrasMinusculas.matcher(senhaInformada).find()) itensRequeridos++;
        if (patternLetrasMaisculas.matcher(senhaInformada).find()) itensRequeridos++;
        if (patternLetrasNumeros.matcher(senhaInformada).find()) itensRequeridos++;
        if (patternCaracteresEspeciais.matcher(senhaInformada).find()) itensRequeridos++;
        if (itensRequeridos > 2 && senhaInformada.length() > 7) {
            itensRequeridos++;
            itensRequeridos *= 2;
            return itensRequeridos;
        }
        return 0;
    }

    private List<String> converteArrayPassword(String pwd) {
        List<String> arrPwdArray = new ArrayList<>();

        // Remover espaços em branco e dividir a string em um array de caracteres
        String cleanPwd = pwd.replaceAll("\\s+", "");
        for (int i = 0; i < cleanPwd.length(); i++) {
            arrPwdArray.add(Character.toString(cleanPwd.charAt(i)));
        }
        return arrPwdArray;
    }

    private int contadorDeCaracteres(String pwd) {
        double nRepChar = 0, nRepInc = 0, nUnqChar = 0;
        List<String> arrPwd = converteArrayPassword(pwd);

        int arrPwdLen = arrPwd.size();

        for (int a = 0; a < arrPwdLen; a++) {
            boolean bCharExists = false;

            for (int b = 0; b < arrPwdLen; b++) {
                if (arrPwd.get(a).equals(arrPwd.get(b)) && a != b) { /* repeat character exists */
                    bCharExists = true;
                    double arrPwdLenDdouble = (double) arrPwdLen;
                    double valor = arrPwdLenDdouble / (b - a);
                    if (valor < 0) valor = valor * -1;
                    nRepInc += valor;
                }
            }

            if (bCharExists) {
                nRepChar++;
                nUnqChar = arrPwdLen - nRepChar;
                nRepInc = (nUnqChar != 0) ? (int) Math.ceil(nRepInc / nUnqChar) : (int) Math.ceil(nRepInc);
            }
        }
        return (int) Math.ceil(nRepInc);
    }
}

