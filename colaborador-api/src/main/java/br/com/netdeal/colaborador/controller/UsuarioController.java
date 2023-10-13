package br.com.netdeal.colaborador.controller;

import br.com.netdeal.colaborador.model.UsuarioRequest;
import br.com.netdeal.colaborador.model.UsuarioResponse;
import br.com.netdeal.colaborador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UsuarioResponse>> getAll() {
        List<UsuarioResponse> usuarios = usuarioService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


    @GetMapping(value = "/colaboradores/{usuarioId}")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios(@PathVariable Long usuarioId) {
        List<UsuarioResponse> usuarios = usuarioService.getUsuariosFilhos(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable Long usuarioId) {
        UsuarioResponse response = usuarioService.getUsuarioById(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.create(request);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioResponse> update(
            @PathVariable Long usuarioId,
            @RequestBody  UsuarioRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.update(usuarioId, request);
        if (usuarioResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{usuarioId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long usuarioId) {
        try {
            usuarioService.deleteById(usuarioId);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }

    }

}
