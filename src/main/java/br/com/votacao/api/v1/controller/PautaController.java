package br.com.votacao.api.v1.controller;

import br.com.votacao.api.v1.mapper.PautaMapper;
import br.com.votacao.api.v1.model.PautaDTO;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/pautas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PautaController {

    @Autowired
    private PautaMapper pautaMapper;

    @Autowired
    private PautaService pautaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PautaDTO salvar(@RequestBody @Valid PautaDTO pautaDTO) {
        Pauta pautaSsalva = pautaService.salvar(pautaMapper.toPauta(pautaDTO));
        return pautaMapper.toPautaDTO(pautaSsalva);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{pautaId}/abrir-sessao")
    public void abrirSessao(@PathVariable("pautaId") Long pautaId) {
        pautaService.abrirSessao(pautaId);
    }

}
