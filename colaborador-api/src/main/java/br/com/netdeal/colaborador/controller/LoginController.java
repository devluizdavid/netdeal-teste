package br.com.netdeal.colaborador.controller;


import br.com.netdeal.colaborador.dto.LoginRequest;
import br.com.netdeal.colaborador.dto.LoginResponse;
import br.com.netdeal.colaborador.model.UsuarioModel;
import br.com.netdeal.colaborador.service.LoginService;
import br.com.netdeal.colaborador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "/token")
    public ResponseEntity<?> getToken(@RequestBody LoginRequest request)  {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = service.generateToken(request.getLogin());

        UsuarioModel usuario = usuarioService.findByLogin(request.getLogin());
        return ResponseEntity.ok(new LoginResponse(usuario.getId(), token, usuario.getNome()));
    }

}