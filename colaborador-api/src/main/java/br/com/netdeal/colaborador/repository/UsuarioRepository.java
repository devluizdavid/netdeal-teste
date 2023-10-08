package br.com.netdeal.colaborador.repository;

import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByLogin(String login);
}
