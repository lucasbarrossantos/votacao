package br.com.votacao.api.v1.controller;

import br.com.votacao.api.v1.model.ResultadoDTO;
import br.com.votacao.domain.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/pautas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "PautaResultadoController")
public class PautaResultadoController {

    @Autowired
    private PautaService pautaService;

    @ApiOperation(value = "Visualizar resultado da votação por Pauta")
    @GetMapping("/contabilizar")
    public ResultadoDTO resultado(@PathVariable("id") Long id) {
        return pautaService.resultadoPauta(id);
    }

}
