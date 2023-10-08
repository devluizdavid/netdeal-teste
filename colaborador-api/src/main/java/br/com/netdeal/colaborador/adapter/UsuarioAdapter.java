package br.com.netdeal.colaborador.adapter;

import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.model.UsuarioRequest;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAdapter {

    public UsuarioResponse getUsuarioResponse(UsuarioModel usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setScore(usuario.getScore());
        usuarioResponse.setCor(retornarCorDaSenha(usuario.getScore()));
        usuarioResponse.setComplexidade(retornaComplexidade(usuario.getScore()));
        if (usuario.getColaboradorPai() != null) {
            usuarioResponse.setColaboradorPai(usuario.getColaboradorPai().getId());
        }

        return usuarioResponse;
    }

    public UsuarioModel getUsuarioModel(UsuarioRequest usuarioRequest) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuarioRequest.getId());
        usuarioModel.setNome(usuarioRequest.getNome());
        usuarioModel.setScore(usuarioRequest.getScore());
        usuarioModel.setLogin(usuarioRequest.getLogin());
        return usuarioModel;
    }

    private String retornarCorDaSenha(Integer score) {
        if (score < 50 ) return "red";
        if (score > 50 && score < 70 ) return "yellow";
        if (score > 70 && score < 90 ) return "green";
        if (score > 9) return "blue";
        return "white";

    }

    private String retornaComplexidade(Integer score) {
        if (score < 50 ) return "Ruim";
        if (score > 50 && score < 70 ) return "Mediana";
        if (score > 70 && score < 90 ) return "Boa";
        if (score > 9) return "Forte";
        return "Fraca";
    }
}
