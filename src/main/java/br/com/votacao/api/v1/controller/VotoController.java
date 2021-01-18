package br.com.votacao.api.v1.controller;

import br.com.votacao.api.v1.mapper.VotoMapper;
import br.com.votacao.api.v1.model.VotoDTO;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/votos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VotoController {

    @Autowired
    private VotoService votoService;

    @Autowired
    private VotoMapper votoMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VotoDTO salvar(@RequestBody @Valid VotoDTO votoDTO) {
        Voto votoSalvo = votoService.salvar(votoMapper.toVoto(votoDTO));
        return votoMapper.toVotoDTO(votoSalvo);
    }

}
