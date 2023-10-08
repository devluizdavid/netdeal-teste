package br.com.netdeal.colaborador.controller;

import br.com.netdeal.colaborador.dto.ColaboradorDto;
import br.com.netdeal.colaborador.exceptions.MessageResponse;
import br.com.netdeal.colaborador.model.ColaboradorModel;
import br.com.netdeal.colaborador.service.ColaboradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/colaborador")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<List<ColaboradorDto>> getColaboradores() {
        List<ColaboradorDto> colaboradores = colaboradorService.getColaboradoresDto();
        return ResponseEntity.status(HttpStatus.OK).body(colaboradores);
    }

    @GetMapping(value = "/{colaboradorId}")
    public ResponseEntity<ColaboradorDto> getColaboradores(@PathVariable Long colaboradorId) {
        ColaboradorDto colaboradorDto = colaboradorService.getColaboradorDto(colaboradorId);
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorDto);
    }

    @PostMapping
    public ResponseEntity<ColaboradorModel> create(@ApiParam(value = "Informações do colaborador", required = true) @RequestBody @Valid ColaboradorDto colaboradorDto) throws ValidationException {

        ColaboradorModel colaboradorModel = colaboradorService.create(colaboradorDto);
        if (colaboradorModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(colaboradorModel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{colaboradorId}")
    public ResponseEntity<@Valid ColaboradorModel> update(
            @PathVariable Long colaboradorId,
            @RequestBody @Valid ColaboradorDto colaboradorDto)  {
        ColaboradorModel colaboradorModel = colaboradorService.update(colaboradorId, colaboradorDto);
        if (colaboradorModel == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(colaboradorModel, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{colaboradorId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long colaboradorId)  {
        try {
            colaboradorService.deleteById(colaboradorId);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }

    }

}
