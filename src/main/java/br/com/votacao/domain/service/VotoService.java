package br.com.votacao.domain.service;

import br.com.votacao.domain.model.Usuario;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class VotoService {

    @Autowired
    private RegrasService regrasService;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Voto salvar(Voto voto) {
        log.info("Computando voto");
        regrasService.validarSessao(voto.getPauta());
        Usuario usuario = usuarioService.buscarOuFalhar(voto.getUsuario().getId());
        regrasService.validarAssociado(usuario.getCpf());
        regrasService.validarVotoDuplicado(voto);
        log.info("Voto computado com sucesso!");
        return votoRepository.save(voto);
    }

}
