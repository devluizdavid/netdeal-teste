package br.com.netdeal.colaborador;

import br.com.netdeal.colaborador.service.ColaboradorService;
import br.com.netdeal.colaborador.service.ScoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScoreServiceTest {

    @Autowired
    private ScoreService scoreService;

    /*
    @Test
    public void showSuccess_ScoreFinalSoComLetrasMinusculas() {
        Integer scoreFinalSenhaSoComLetrasMinusculas =
                scoreService.calcularScoreSenhaTotal("12345");
        Assertions.assertEquals(4, scoreFinalSenhaSoComLetrasMinusculas);
    }

    @Test
    public void showSuccess_ScoreFinalSoComLetrasMaiusculas() {
        Integer scoreFinalSenhaSoComLetrasMaiusculas = scoreService.calcularScoreSenhaTotal("teste");
        Assertions.assertEquals(3, scoreFinalSenhaSoComLetrasMaiusculas);
    }

    @Test
    public void showSuccess_ScoreFinalSoComNumeros() {
        Integer scoreFinalSenhaSoComNumeros = scoreService.calcularScoreSenhaTotal("123456");
        Assertions.assertEquals(4, scoreFinalSenhaSoComNumeros);
    }

    @Test
    public void showSuccess_ScoreFinalSoComSimbolos() {
        Integer scoreFinalSenhaSoComSimbolos = scoreService.calcularScoreSenhaTotal("@#-");
        Assertions.assertEquals(32, scoreFinalSenhaSoComSimbolos);
    }

    @Test
    public void showSuccess_ScoreFinalComTodosRequisitos() {

        Integer scoreFinalSenhaComTodosRequisitos = scoreService.calcularScoreSenhaTotal("@Aa123456");
        Assertions.assertEquals(96, scoreFinalSenhaComTodosRequisitos);
    }

    @Test
    public void showSuccess_ScoreFinalComLetrasMaiusculas() {
        //@AbbbACCCx1
        Integer scoreFinalSenhaComTodosRequisitos = scoreService.calcularScoreSenhaTotal("@AbbbACCCx1");
        Assertions.assertEquals(72, scoreFinalSenhaComTodosRequisitos);
    }

    */
    @Test
    public void showSuccess_ScoreFinalComEspacos() {
        Integer scoreFinalSenhaComTodosRequisitos = scoreService.calcularScoreSenhaTotal("Nova senha e tal");
        Assertions.assertEquals(63, scoreFinalSenhaComTodosRequisitos);
    }

    @Test
    public void showSuccess_ScoreFinalSemSimbolos() {
        Integer scoreFinalSenhaComTodosRequisitos = scoreService.calcularScoreSenhaTotal("5otFwe9q2WgNrfO");
        Assertions.assertEquals(100, scoreFinalSenhaComTodosRequisitos);
    }



}