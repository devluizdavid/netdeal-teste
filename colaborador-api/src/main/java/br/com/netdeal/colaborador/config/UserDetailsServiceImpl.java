package br.com.netdeal.colaborador.config;

import br.com.netdeal.colaborador.exceptions.BusinessException;
import br.com.netdeal.colaborador.exceptions.MessageCode;
import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login)  {
        UsuarioModel usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));

        if (usuario==null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            User user = new User(usuario.getUsername(),
                    usuario.getPassword(),
                    usuario.isEnabled(),
                    usuario.isAccountNonExpired(),
                    usuario.isCredentialsNonExpired(),
                    usuario.isAccountNonLocked(),
                    usuario.getAuthorities());
            return user;
        }
    }



}

