package br.com.votacao.api.v1.controller;

import br.com.votacao.api.v1.mapper.UsuarioMapper;
import br.com.votacao.api.v1.model.UsuarioDTO;
import br.com.votacao.domain.model.Usuario;
import br.com.votacao.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "UsuarioController")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @ApiOperation(value = "Criar um novo usuário")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuarioSalvo = usuarioService.salvar(usuarioMapper.toUsuario(usuarioDTO));
        return usuarioMapper.toUsuarioDTO(usuarioSalvo);
    }

}
