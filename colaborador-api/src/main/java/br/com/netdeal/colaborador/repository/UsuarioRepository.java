package br.com.netdeal.colaborador.repository;

import br.com.netdeal.colaborador.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByLogin(String login);

    List<UsuarioModel> findByUsuarioPaiId(Long usuarioId);

    List<UsuarioModel> findByUsuarioPaiIdOrderByHierarquia(Long usuarioId);

    List<UsuarioModel> findByHierarquiaStartingWithOrderByHierarquia(String hieraquia);
}
