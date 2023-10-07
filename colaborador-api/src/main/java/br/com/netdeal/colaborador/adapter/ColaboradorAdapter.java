package br.com.netdeal.colaborador.adapter;

import br.com.netdeal.colaborador.dto.ColaboradorDto;
import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        colaboradorDto.setColaboradorPai(colaboradorModel.getColaboradorPai().getId());

        return colaboradorDto;
    }
}
