package br.com.netdeal.colaborador.service;

import br.com.netdeal.colaborador.adapter.UsuarioAdapter;
import br.com.netdeal.colaborador.exceptions.BusinessException;
import br.com.netdeal.colaborador.exceptions.MessageCode;
import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.model.UsuarioRequest;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import br.com.netdeal.colaborador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAdapter usuarioAdapter;


    @Autowired
    private ScoreService scoreService;

    public List<UsuarioResponse> getUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = usuarios.stream().map(usuarioModel -> usuarioAdapter.getUsuarioResponse(usuarioModel)).collect(Collectors.toList());
        return usuarioResponses;
    }

    public UsuarioResponse update(Long usuarioId, UsuarioRequest usuarioRequest) {
        usuarioRepository.findById(usuarioId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        usuarioRequest.setId(usuarioId);
        UsuarioModel usuarioModel = usuarioAdapter.getUsuarioModel(usuarioRequest);
        usuarioRepository.save(usuarioModel);
        return usuarioAdapter.getUsuarioResponse(usuarioModel);
    }

    public void deleteById(Long usuarioId) {
        usuarioRepository.findById(usuarioId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        usuarioRepository.deleteById(usuarioId);
    }

    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        UsuarioModel usuarioModel = usuarioAdapter.getUsuarioModel(usuarioRequest);
        Integer scoreSenhaCalculado = scoreService.calcularScoreSenhaTotal(usuarioRequest.getSenha().trim());
        usuarioModel.setScore(scoreSenhaCalculado);
        String senhaCriptografada =new BCryptPasswordEncoder().encode(usuarioRequest.getSenha().trim());
        usuarioModel.setSenha(senhaCriptografada);
        usuarioRepository.save(usuarioModel);
        return usuarioAdapter.getUsuarioResponse(usuarioModel);
    }

    public UsuarioResponse getUsuario(Long usuarioId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        return usuarioAdapter.getUsuarioResponse(usuario);
    }
}
