package br.com.netdeal.colaborador.adapter;

import br.com.netdeal.colaborador.dto.ColaboradorDto;
import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ColaboradorAdapter {


    public ColaboradorModel getColaboradorModel(ColaboradorDto colaboradorDto) {
        ColaboradorModel colaboradorModel = new ColaboradorModel();

        colaboradorModel.setId(colaboradorDto.getId());
        colaboradorModel.setNome(colaboradorDto.getNome());
        colaboradorModel.setSenha(colaboradorDto.getSenha());
        colaboradorModel.setScore(colaboradorDto.getScore());

        return colaboradorModel;
    }

    public ColaboradorDto getColaboradorDto(ColaboradorModel colaboradorModel) {
        ColaboradorDto colaboradorDto = new ColaboradorDto();

        colaboradorDto.setId(colaboradorModel.getId());
        colaboradorDto.setNome(colaboradorModel.getNome());
        colaboradorDto.setSenha(colaboradorModel.getSenha());
        colaboradorDto.setScore(colaboradorModel.getScore());
        if (colaboradorModel.getColaboradorPai() != null) {
            colaboradorDto.setColaboradorPai(colaboradorModel.getColaboradorPai().getId());
        }
        colaboradorDto.setCor(definirCor(colaboradorDto.getScore().intValue()));
        colaboradorDto.setSituacao(definirSituacao(colaboradorDto.getScore().intValue()));

        return colaboradorDto;
    }

    private String definirSituacao(int score) {
        if (score < 50) return "Ruim";
        else if (score > 50 && score < 70) return "Mediana";
        else if (score > 70 && score < 90) return "Boa";
        else if (score > 90) return "Forte";
        return "Fraca";
    }

    private String definirCor(int score) {
        if (score < 50) return "orange";
        else if (score > 50 && score < 70) return "yellow";
        else if (score > 70 && score < 90) return "green";
        else if (score > 90) return "blue";
        return "white";
    }



}
