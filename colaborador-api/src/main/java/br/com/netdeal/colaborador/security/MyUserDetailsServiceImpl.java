package br.com.netdeal.colaborador.security;

import br.com.netdeal.colaborador.exceptions.BusinessException;
import br.com.netdeal.colaborador.exceptions.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.repository.UsuarioRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsuarioModel usuario = repository.findByLogin(username).orElseThrow(() -> new BusinessException(MessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO));
		if (usuario==null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		} else {
			User user = new User(usuario.getLogin(),
					usuario.getSenha(),
					usuario.isEnabled(),
					usuario.isAccountNonExpired(),
					usuario.isCredentialsNonExpired(),
					usuario.isAccountNonLocked(),
					usuario.getAuthorities());
			return user;
		}


	}

}
