package br.com.netdeal.colaborador.service;

import br.com.netdeal.colaborador.adapter.ColaboradorAdapter;
import br.com.netdeal.colaborador.dto.ColaboradorDto;
import br.com.netdeal.colaborador.exceptions.BusinessException;
import br.com.netdeal.colaborador.exceptions.MessageCode;
import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {


    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorAdapter colaboradorAdapter;

    @Autowired
    private ScoreService scoreService;

    public List<ColaboradorDto> getColaboradoresDto() {
        List<ColaboradorModel> colaboradores = colaboradorRepository.findAll();
        List<ColaboradorDto> colaboradorDtos = colaboradores.stream().map(colaboradorModel -> colaboradorAdapter.getColaboradorDto(colaboradorModel)).collect(Collectors.toList());
        return colaboradorDtos;
    }

    public ColaboradorModel getColaborador(Long colaboradorId) {
        Optional<ColaboradorModel> colaborador = colaboradorRepository.findById(colaboradorId);
        return colaborador.get();
    }

    public ColaboradorModel create(ColaboradorDto colaboradorDto) {
        ColaboradorModel colaboradorModel = colaboradorAdapter.getColaboradorModel(colaboradorDto);
        Integer scoreSenhaCalculado = scoreService.calcularScoreSenhaTotal(colaboradorDto.getSenha().trim());
        colaboradorModel.setScore(new BigDecimal(scoreSenhaCalculado));


        return colaboradorRepository.save(colaboradorModel);
    }

    public ColaboradorModel update(Long colaboradorId, ColaboradorDto colaboradorDto) {
        colaboradorRepository.findById(colaboradorId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        colaboradorDto.setId(colaboradorId);
        ColaboradorModel colaboradorModel = colaboradorAdapter.getColaboradorModel(colaboradorDto);
        return colaboradorRepository.save(colaboradorModel);
    }

    public void deleteById(Long colaboradorId) {
        colaboradorRepository.findById(colaboradorId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        colaboradorRepository.deleteById(colaboradorId);

    }

    public ColaboradorDto getColaboradorDto(Long colaboradorId) {
        ColaboradorModel colaboradorModel = colaboradorRepository.findById(colaboradorId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        return colaboradorAdapter.getColaboradorDto(colaboradorModel);
    }
}