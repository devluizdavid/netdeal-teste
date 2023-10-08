package br.com.netdeal.colaborador.adapter;

import br.com.netdeal.colaborador.dto.ColaboradorDto;
import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAdapter {

    public UsuarioResponse getUsuarioResponse(UsuarioModel usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setScore(usuario.getScore());
        return usuarioResponse;
    }
}
