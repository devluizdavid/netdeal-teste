package br.com.netdeal.colaborador.controller;

import br.com.netdeal.colaborador.dto.LoginRequest;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import br.com.netdeal.colaborador.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class LoginController {


    @Autowired
    private LoginService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> login(
            @RequestBody @Valid LoginRequest request,
            @AuthenticationPrincipal UserDetails userDetails) throws ValidationException {
        UsuarioResponse response = usuarioService.findByLogin(userDetails.getUsername());
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
