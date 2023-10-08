package br.com.netdeal.colaborador.service;

import br.com.netdeal.colaborador.adapter.UsuarioAdapter;
import br.com.netdeal.colaborador.exceptions.BusinessException;
import br.com.netdeal.colaborador.exceptions.MessageCode;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAdapter usuarioAdapter;

    public UsuarioResponse findByLogin(String username) {
        UsuarioModel usuario = usuarioRepository.findByLogin(
                username).orElseThrow(() ->
                new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
        return usuarioAdapter.getUsuarioResponse(usuario);
    }
}
